package com.pauloelienay.slinky.utils.readonly;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@SuppressWarnings({"unused"})
@AllArgsConstructor
@NoArgsConstructor
public class ObjectWithMappedBy {
	@OneToOne()
	ObjectWithUpdatableField object;
}
