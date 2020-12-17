package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	private Long id;
	private String name;
	private Boolean enabled;
	private Long categoryParentId;
	private String categoryParentName;
	private String createdDate;
	private String lastModifiedDate;

}
