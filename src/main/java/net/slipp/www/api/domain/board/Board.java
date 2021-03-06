package net.slipp.www.api.domain.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.slipp.www.api.domain.support.AuditingEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "slipp_board")
public class Board extends AuditingEntity {

	public Board(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category")
	private BoardCategory category;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "is_deleted", columnDefinition = "bit(1) default 1")
	private Boolean isDeleted = false;

	@Immutable
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
	@OrderBy("id ASC")
	private List<BoardComment> boardComments;

}
