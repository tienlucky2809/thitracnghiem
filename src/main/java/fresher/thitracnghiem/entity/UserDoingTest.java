package fresher.thitracnghiem.entity;

import fresher.thitracnghiem.entity.test.Test;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_doing_test")
@Data
@NoArgsConstructor
public class UserDoingTest extends CreateAuditable {

    public UserDoingTest(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "ispass")
    private Boolean ispass;

    @Column(name = "start_time")
    private Date startDate;

    @Column(name = "end_time")
    private Date endDate;


    @Column(name = "point")
    private Integer point;

}
