package fresher.thitracnghiem.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestQuestionDTO {

    private Long id;
    private Long test_id;
    private Long question_id;

    @Override
    public String toString() {
        return "TestQuestionDTO{" +
                "id=" + id +
                ", test_id=" + test_id +
                ", question_id=" + question_id +
                '}';
    }
    public void xuat(){
        System.out.printf(toString());
    }
}
