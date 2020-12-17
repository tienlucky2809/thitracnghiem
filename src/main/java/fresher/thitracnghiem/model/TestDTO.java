package fresher.thitracnghiem.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Long id;
    private int level;
    private String name;
    private int number_question;
    private int point;
    private Long passing_point;
    private String time;
    private Long category_id;
    private String createDate;
    private String lastModifiedDate;
    private Boolean enabled;
    private List<QuestionDTO> questionDTOS;
    private Long [] listQuestionId; // chua danh sach id cua bai thi






}
