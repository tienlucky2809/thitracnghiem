package fresher.thitracnghiem.model;

public class QuestionStatisticDTO {
    private String name;
    private Integer totalQuestions;

    public QuestionStatisticDTO() {
    }

    public QuestionStatisticDTO(String name, Integer totalQuestions) {
        this.name = name;
        this.totalQuestions = totalQuestions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
