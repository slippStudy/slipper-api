package net.slipp.www.api.controller.board;

import io.swagger.annotations.ApiOperation;
import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.service.board.BoardCreateService;
import net.slipp.www.api.support.ResponseWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/boards")
public class BoardCreateController {

	private final BoardCreateService boardCreateService;

	private final ModelMapper modelMapper;

	@Autowired
	public BoardCreateController(BoardCreateService boardCreateService, ModelMapper modelMapper) {
		this.boardCreateService = boardCreateService;
		this.modelMapper = modelMapper;
	}

	@ApiOperation(value = "게시글 등록", responseReference = "ResponseWrapper<BoardDto.Response>")
	@PostMapping("")
	public ResponseWrapper<BoardDto.Response> create(@RequestBody @Valid BoardDto.Create create) {
		Board newBoard = boardCreateService.create(create);
		return ResponseWrapper.success(modelMapper.map(newBoard, BoardDto.Response.class));
	}

	@ApiOperation(value = "게시글 수정", responseReference = "ResponseWrapper<BoardDto.Response>")
	@PutMapping("/{id}")
	public ResponseWrapper<BoardDto.Response> modify(@PathVariable Long id, @RequestBody @Valid BoardDto.Modify modify) {
		Board modifiedBoard = boardCreateService.modify(id, modify);
		return ResponseWrapper.success(modelMapper.map(modifiedBoard, BoardDto.Response.class));
	}

	@ApiOperation(value = "게시글 삭제", responseReference = "ResponseWrapper<Boolean>")
	@DeleteMapping("/{id}")
	public ResponseWrapper<Boolean> delete(@PathVariable Long id) {
		Boolean isDeleted = boardCreateService.delete(id);
		return ResponseWrapper.success(isDeleted);
	}
}
