package net.slipp.www.api.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.slipp.www.api.dto.board.BoardCommentDto;
import net.slipp.www.api.service.board.BoardCommentCreateService;
import net.slipp.www.api.service.board.BoardCommentFindService;
import net.slipp.www.api.support.ResponseWrapper;

@RestController
@RequestMapping("/v1/boards")
public class BoardCommentController {

	@Autowired
	private BoardCommentCreateService createService;
	@Autowired
	private BoardCommentFindService findService;

	@PostMapping("/{boardId}/comments")
	public ResponseWrapper<String> create(@PathVariable("boardId") Long boardId, BoardCommentDto boardCommentDto) {
		boardCommentDto.setBoardId(boardId);
		createService.save(boardCommentDto);
		return ResponseWrapper.success("성공");
	}

	@GetMapping("/{boardId}/comments")
	public ResponseWrapper<List<BoardCommentDto>> finds(@PathVariable("boardId") Long boardId) {
		List<BoardCommentDto> boardCommentDtos = findService.findByBoardId(boardId);
		return ResponseWrapper.success(boardCommentDtos);
	}
}
