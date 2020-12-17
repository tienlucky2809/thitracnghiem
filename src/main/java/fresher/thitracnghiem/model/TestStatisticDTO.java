package fresher.thitracnghiem.model;

public class TestStatisticDTO {
    private String name ;
    private Integer totalTests;


    public TestStatisticDTO() {
    }

    public TestStatisticDTO(String name, Integer totalTests) {
        this.name = name;
        this.totalTests = totalTests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Integer totalTests) {
        this.totalTests = totalTests;
    }
}
