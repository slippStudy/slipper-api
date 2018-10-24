package net.slipp.www.api.repository;

import net.slipp.www.api.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndIsDeletedFalse(Long id);
    Page<Board> findAllByIsDeletedFalse(Pageable pageable);
}
