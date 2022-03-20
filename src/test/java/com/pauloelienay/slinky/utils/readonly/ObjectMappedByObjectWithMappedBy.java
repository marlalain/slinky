package com.pauloelienay.slinky.utils.readonly;

import javax.persistence.Id;
import javax.persistence.OneToOne;

public class ObjectMappedByObjectWithMappedBy {
	@Id
	Long id;
	@OneToOne()
	private ObjectWithMappedBy object;
}
