package fresher.thitracnghiem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "question_type")
@Getter
@Setter
@NoArgsConstructor
public class QuestionType extends CreateAuditable {

	private static final long serialVersionUID = 1L;

	public QuestionType(int value) {
		super();
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true, columnDefinition = "NVARCHAR(225)")
	private String name;

	@NaturalId
	@Column(name = "value", unique = true)
	private int value;

	@Column(name = "enabled")
	private Boolean enabled;

}
