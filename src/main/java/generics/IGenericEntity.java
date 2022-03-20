package generics;

import java.io.Serializable;

@SuppressWarnings({"unused"})
public interface IGenericEntity<T, S extends Serializable> {
	S getId();
}
