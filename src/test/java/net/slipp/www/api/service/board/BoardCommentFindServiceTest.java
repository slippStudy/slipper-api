package net.slipp.www.api.service.board;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import net.slipp.www.api.ActiveProfileResolver;
import net.slipp.www.api.ApiApplication;
import net.slipp.www.api.dto.board.BoardCommentDto;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles(inheritProfiles = false, resolver = ActiveProfileResolver.class)
@SpringBootTest(classes = ApiApplication.class)
public class BoardCommentFindServiceTest {

	@Autowired
	private BoardCommentFindService service;

	@Test
	public void testFindByBoardId() throws Exception {
		List<BoardCommentDto> dtos = service.findByBoardId(0L);
		assertTrue(CollectionUtils.isEmpty(dtos));
	}

}
