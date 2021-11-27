package generics;

import entities.DummyEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IGenericEntityTest {
	@Test
	void entityWithIdIsntUpdate() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		assertFalse(dummyEntity.isUpdate());
	}

	@Test
	void entityWithoutIdIsUpdate() {
		DummyEntity dummyEntity = new DummyEntity();
		assertTrue(dummyEntity.isUpdate());
	}
}