package net.slipp.www.api.service.board;

import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.domain.board.BoardCategory;
import net.slipp.www.api.dto.board.BoardDto;
import net.slipp.www.api.repository.BoardCategoryRepository;
import net.slipp.www.api.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BoardCreateService {
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BoardCreateService(BoardRepository boardRepository, BoardCategoryRepository boardCategoryRepository, ModelMapper modelMapper) {
        this.boardRepository = boardRepository;
        this.boardCategoryRepository = boardCategoryRepository;
        this.modelMapper = modelMapper;
    }


    public Board create(BoardDto.Create create) throws BoardCategoryNotFoundException {
        Board board = modelMapper.map(create, Board.class);
        BoardCategory category = boardCategoryRepository.findById(create.getCategoryNo()).orElseThrow(BoardCategoryNotFoundException::new);
        board.setCategory(category);
        return boardRepository.save(board);
    }

    public Board modify(Long id, BoardDto.Modify modify) throws BoardNotFoundException, BoardCategoryNotFoundException {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        BoardCategory category = boardCategoryRepository.findById(modify.getCategoryNo()).orElseThrow(BoardCategoryNotFoundException::new);

        board.setCategory(category);
        board.setTitle(modify.getTitle());
        board.setContent(modify.getContent());
        board.setImageUrl(modify.getImageUrl());

        return boardRepository.save(board);
    }

    public Boolean delete(Long id) throws BoardNotFoundException {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.setIsDeleted(true);

        Board deletedBoard = boardRepository.save(board);

        return deletedBoard.getIsDeleted();
    }
}
