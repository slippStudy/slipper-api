package net.slipp.www.api.controller.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.support.ResponseWrapper;

@RestController
@RequestMapping("/v1/boards")
public class BoardFindController {

	@ApiOperation(value = "게시글 조회", responseReference = "ResponseWrapper<BoardDto>")
	@GetMapping("")
	public ResponseWrapper<Board> finds() {
		return ResponseWrapper.success(new Board());
	}
}
