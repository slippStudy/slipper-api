package net.slipp.www.api.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.slipp.www.api.domain.support.AuditingEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "slipp_board_comment")
public class BoardComment extends AuditingEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@Column(name = "content")
	private String content;

	@Column(name = "is_deleted", columnDefinition = "bit(1) default 1")
	private Boolean isDeleted = false;

}
