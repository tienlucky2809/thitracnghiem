package fresher.thitracnghiem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_doing_question")
@Data
public class UserDoingQuestion extends CreateAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long question_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_doing_test_id")
    private UserDoingTest userDoingTest;

    @Column(name = "content")
    private String content;

}
