package net.slipp.www.api.service.board;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.slipp.www.api.domain.board.BoardComment;
import net.slipp.www.api.dto.board.BoardCommentDto;
import net.slipp.www.api.repository.board.BoardCommentRepository;

@Service
public class BoardCommentFindService {

	@Autowired
	private BoardCommentRepository boardCommentRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<BoardCommentDto> findByBoardId(Long boardId) {
		List<BoardComment> boardComments = boardCommentRepository.findByBoardId(boardId);
		List<BoardCommentDto> boardCommentDtos = modelMapper.map(boardComments, new TypeToken<List<BoardCommentDto>>() {}.getType());
		return boardCommentDtos;
	}
}
