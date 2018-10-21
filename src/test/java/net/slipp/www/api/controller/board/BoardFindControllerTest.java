package net.slipp.www.api.controller.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.service.board.BoardCategoryNotFoundException;
import net.slipp.www.api.service.board.BoardCreateService;
import net.slipp.www.api.service.board.BoardFindService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardFindControllerTest {

    @Autowired
    private BoardCreateService boardCreateService;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardFindService boardFindService;

    @Autowired
    private ModelMapper modelMapper;

    @Before
    public void before() throws BoardCategoryNotFoundException {
        this.mvc = MockMvcBuilders
                .standaloneSetup(new BoardFindController(boardFindService, modelMapper))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        BoardDto.Create create = new BoardDto.Create(1L, "테스트", "테스트입니다.", "http://");
        boardCreateService.create(create);
        boardCreateService.create(create);
        boardCreateService.create(create);
        boardCreateService.create(create);
    }


    @Test
    @Transactional
    public void 게시글_조회() throws Exception {
        this.mvc.perform(get("/v1/boards/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(2));
    }

    @Test
    @Transactional
    public void 게시글_목록_조회() throws Exception {
        this.mvc.perform(get("/v1/boards")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }
}