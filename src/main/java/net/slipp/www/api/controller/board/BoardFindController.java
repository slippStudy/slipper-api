package net.slipp.www.api.controller.board;

import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.service.board.BoardFindService;
import net.slipp.www.api.exception.BoardNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.support.ResponseWrapper;

@RestController
@RequestMapping("/v1/boards")
public class BoardFindController {

	private final BoardFindService boardFindService;

	private final ModelMapper modelMapper;

	@Autowired
	public BoardFindController(BoardFindService boardFindService, ModelMapper modelMapper) {
		this.boardFindService = boardFindService;
		this.modelMapper = modelMapper;
	}

	@ApiOperation(value = "게시글 조회", responseReference = "ResponseWrapper<BoardDto.Response>")
	@GetMapping("/{id}")
	public ResponseWrapper<BoardDto.Response> find(@PathVariable Long id) {
		Board board = boardFindService.find(id);
		return ResponseWrapper.success(modelMapper.map(board, BoardDto.Response.class));
	}

	@ApiOperation(value = "게시글 목록 조회", responseReference = "ResponseWrapper<Page<BoardDto.Response>>")
	@GetMapping("")
	public ResponseWrapper<Page<BoardDto.Response>> finds(@RequestParam(name = "page", defaultValue = "0") int page,
														  @RequestParam(name = "size",defaultValue = "10") int size) {
		Page<Board> boards = boardFindService.finds(PageRequest.of(page, size));
		return ResponseWrapper.success(modelMapper.map(boards, new TypeToken<Page<BoardDto.Response>>() {}.getType()));
	}
}
