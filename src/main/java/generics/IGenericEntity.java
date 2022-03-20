package generics;

import java.io.Serializable;

public interface IGenericEntity<T, S extends Serializable> {
	S getId();
}
