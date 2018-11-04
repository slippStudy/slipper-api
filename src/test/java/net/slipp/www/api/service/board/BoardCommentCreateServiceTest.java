package net.slipp.www.api.service.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.slipp.www.api.ActiveProfileResolver;
import net.slipp.www.api.ApiApplication;
import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.domain.board.BoardComment;
import net.slipp.www.api.dto.board.BoardCommentDto;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles(inheritProfiles = false, resolver = ActiveProfileResolver.class)
@SpringBootTest(classes = ApiApplication.class)
@Transactional
public class BoardCommentCreateServiceTest {

	@Autowired
	private BoardCommentCreateService service;

	@Test
	public void testSave() throws Exception {
		BoardCommentDto comment = new BoardCommentDto();
		comment.setContent("TEST");
		comment.setBoardId(1L);
		service.save(comment);
	}

}
