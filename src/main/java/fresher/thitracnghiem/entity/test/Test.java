package fresher.thitracnghiem.entity.test;

import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.entity.CreateAuditable;
import fresher.thitracnghiem.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "test")
@NoArgsConstructor
public class Test extends CreateAuditable {

    public Test(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(100)")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // tham chieu truc tiep den id của category , tu dinh nghia t
    private Category category;

    @Column(name = "level")
    private int level;

    @Column(name = "time")
    private String time;

    @Column(name = "number_question")
    private int number_question;

    @Column(name = "point")
    private int point;

    @Column(name = "passing_point")
    private Long passing_point;

    @Column(name = "enabled")
    private Boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id")
    )
    private List<Question> listQuestion = new ArrayList<>();


}
