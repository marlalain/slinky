package com.pauloelienay.slinky.generics;

import java.io.Serializable;

public interface IGenericBusiness<T extends IGenericEntity<T, S>, S extends Serializable> {
}
