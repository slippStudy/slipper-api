package net.slipp.www.api.dto.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BoardCommentDto {

	@ApiModelProperty(value = "댓글ID")
	private Long id;

	@ApiModelProperty(value = "게시글ID")
	private Long boardId;

	@ApiModelProperty(value = "댓글")
	private String content;

	@ApiModelProperty(notes = "삭제여부")
	private Boolean isDeleted = false;

	private String createdBy;

	private String modifiedBy;

	private long createdDate;

	private long modifiedDate;
}
