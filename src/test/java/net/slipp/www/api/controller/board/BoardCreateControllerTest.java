package net.slipp.www.api.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.exception.BoardCategoryNotFoundException;
import net.slipp.www.api.service.board.BoardCreateService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardCreateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardCreateService boardCreateService;

    @Autowired
    private ModelMapper modelMapper;

    private Long createdBoardId;

    @Before
    public void before() throws BoardCategoryNotFoundException {
        this.mvc = MockMvcBuilders
                .standaloneSetup(new BoardCreateController(boardCreateService, modelMapper))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        BoardDto.Create create = new BoardDto.Create(1L, "테스트", "테스트입니다.", "http://");
        Board board = boardCreateService.create(create);
        createdBoardId = board.getId();
    }

    @Test
    @Transactional
    public void 게시물_등록() throws Exception {

        BoardDto.Create create = new BoardDto.Create(1L, "테스트", "테스트입니다.", "http://");

        this.mvc.perform(post("/v1/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(create)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.category.no").value(1L))
                .andExpect(jsonPath("$.data.title").value(create.getTitle()))
                .andExpect(jsonPath("$.data.content").value(create.getContent()))
                .andExpect(jsonPath("$.data.imageUrl").value(create.getImageUrl()));
    }

    @Test
    @Transactional
    public void 게시물_수정() throws Exception {
        BoardDto.Modify modify = new BoardDto.Modify(1L, "테스트 수정", "수정 테스트입니다.", "https://");

        this.mvc.perform(put("/v1/boards/" + createdBoardId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(modify)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.category.no").value(1L))
                .andExpect(jsonPath("$.data.title").value(modify.getTitle()))
                .andExpect(jsonPath("$.data.content").value(modify.getContent()))
                .andExpect(jsonPath("$.data.imageUrl").value(modify.getImageUrl()));
    }

    @Test
    @Transactional
    public void 게시물_삭제() throws Exception {

        this.mvc.perform(delete("/v1/boards/{id}", createdBoardId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isBoolean());
    }
}