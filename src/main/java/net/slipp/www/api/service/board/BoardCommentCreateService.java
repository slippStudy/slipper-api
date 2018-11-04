package net.slipp.www.api.service.board;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.domain.board.BoardComment;
import net.slipp.www.api.dto.board.BoardCommentDto;
import net.slipp.www.api.repository.board.BoardCommentRepository;

@Service
public class BoardCommentCreateService {

	@Autowired
	private BoardCommentRepository boardCommentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional(readOnly = false)
	public void save(BoardCommentDto boardCommentDto) {
		BoardComment boardComment = modelMapper.map(boardCommentDto, BoardComment.class);
		boardComment.setBoard(new Board(boardCommentDto.getBoardId()));
		boardCommentRepository.save(boardComment);
	}
}
