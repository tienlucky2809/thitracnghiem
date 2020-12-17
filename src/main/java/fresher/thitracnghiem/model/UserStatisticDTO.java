package fresher.thitracnghiem.model;

public class UserStatisticDTO {
    private int numberPerson;
    private int enabled ;

    public UserStatisticDTO(int numberPerson, int enabled) {
        this.numberPerson = numberPerson;
        this.enabled = enabled;
    }

    public UserStatisticDTO() {
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
