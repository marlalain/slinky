package utils.readonly;

import javax.persistence.Transient;

@SuppressWarnings({"unused"})
public class ObjectWithTransientField {
	@Transient
	private String name;
}
