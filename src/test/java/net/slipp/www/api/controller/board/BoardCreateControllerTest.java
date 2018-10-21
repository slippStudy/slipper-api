package net.slipp.www.api.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.slipp.www.api.dto.board.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardCreateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
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
    public void 게시물_수정() {
    }

    @Test
    public void 게시물_삭제() {
    }
}