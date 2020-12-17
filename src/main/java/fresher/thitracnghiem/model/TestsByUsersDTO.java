package fresher.thitracnghiem.model;

public class TestsByUsersDTO {
    private String testName ;
    private Integer totalNumberTest ;

    public TestsByUsersDTO() {
    }

    public TestsByUsersDTO(String testName, Integer totalNumberTest) {
        this.testName = testName;
        this.totalNumberTest = totalNumberTest;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTotalNumberTest() {
        return totalNumberTest;
    }

    public void setTotalNumberTest(Integer totalNumberTest) {
        this.totalNumberTest = totalNumberTest;
    }
}
