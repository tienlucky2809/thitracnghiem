package fresher.thitracnghiem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fresher.thitracnghiem.entity.test.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
public class Question extends CreateAuditable {

    private static final long serialVersionUID = 1L;

    public Question(Long id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "NVARCHAR(225)")
    private String content;

    @Column(name = "level_id")
    private int level;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "point")
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "value")
    private QuestionType questionType;


    @ManyToMany(mappedBy = "listQuestion", fetch = FetchType.EAGER
    )
    private List<Test> testQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Answer> answers;


}
