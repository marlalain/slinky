package generics;

import java.io.Serializable;

public interface IGenericBusiness<T, S extends Serializable> extends IGenericRepository<T, S> {
}
