package net.slipp.www.api.domain.board;

import javax.persistence.*;

import lombok.Data;
import net.slipp.www.api.domain.support.AuditingEntity;

@Data
@Entity
@Table(name = "slipp_board")
public class Board extends AuditingEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="category")
	private BoardCategory category;

	private String title;

	private String content;

	@Column(name ="image_url")
	private String imageUrl;

    @Column(name ="is_deleted", columnDefinition="bit(1) default 1")
	private Boolean isDeleted = false;
}
