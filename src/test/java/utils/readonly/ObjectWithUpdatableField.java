package utils.readonly;

import lombok.AllArgsConstructor;

import javax.persistence.Column;

@SuppressWarnings({"unused"})
@AllArgsConstructor
public class ObjectWithUpdatableField {
	@Column
	private String name;
}
