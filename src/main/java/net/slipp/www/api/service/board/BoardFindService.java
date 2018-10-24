package net.slipp.www.api.service.board;

import net.slipp.www.api.domain.board.Board;
import net.slipp.www.api.exception.BoardNotFoundException;
import net.slipp.www.api.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardFindService {
    private final BoardRepository boardRepository;

    public BoardFindService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board find(Long id) throws BoardNotFoundException {
        return boardRepository.findByIdAndIsDeletedFalse(id).orElseThrow(BoardNotFoundException::new);
    }

    public Page<Board> finds(Pageable pageable) {
        return boardRepository.findAllByIsDeletedFalse(pageable);
    }
}
