package net.slipp.www.api.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.slipp.www.api.domain.board.BoardComment;

public interface BoardCommentRepository extends CrudRepository<BoardComment, Long> {

	@Query("select o from BoardComment o where o.board.id = :boardId")
	List<BoardComment> findByBoardId(@Param("boardId") Long boardId);
}
