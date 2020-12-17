package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {

	private Long id;
	private String content;
	private int level;
	private Boolean enabled;
	private int point;
	private Long categoryId;
	private int questionTypeValue;
	private String questionTypeName;
	private List<AnswerDTO> answerDTOS;
	private String createdDate;
	private String lastModifiedDate;
}
