package fresher.thitracnghiem.model;

public class CheckStatusTestDTO {
    private Integer numberTest;
    private String status;

    public CheckStatusTestDTO() {
    }


    public CheckStatusTestDTO(Integer numberTest, String status) {
        this.numberTest = numberTest;
        this.status = status;
    }

    public Integer getNumberTest() {
        return numberTest;
    }

    public void setNumberTest(Integer numberTest) {
        this.numberTest = numberTest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
