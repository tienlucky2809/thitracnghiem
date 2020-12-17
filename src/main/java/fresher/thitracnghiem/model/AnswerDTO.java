package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Long id;
	private String content;
	private Boolean correct;
	private Boolean enabled;
	private Long questionId;
	private String createdDate;
	private String lastModifiedDate;
}
