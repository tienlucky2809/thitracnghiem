package fresher.thitracnghiem.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "app_user_uk", columnNames = "user_name")})
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    private String userName;

    @Column(name = "encryted_password", length = 128, nullable = false)
    private String encrytedPassword;

    public User(Long userId) {
        this.userId = userId;
    }

    @Column(name = "enabled", length = 1, nullable = false)


    private boolean enabled;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
