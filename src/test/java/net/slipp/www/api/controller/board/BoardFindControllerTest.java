package net.slipp.www.api.controller.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.www.api.ActiveProfileResolver;
import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.exception.BoardCategoryNotFoundException;
import net.slipp.www.api.service.board.BoardCreateService;
import net.slipp.www.api.service.board.BoardFindService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(inheritProfiles = false, resolver = ActiveProfileResolver.class)
@AutoConfigureMockMvc
public class BoardFindControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardFindService boardFindService;

    @Autowired
    private BoardCreateService boardCreateService;

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