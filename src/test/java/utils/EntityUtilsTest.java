package utils;

import entities.DummyEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityUtilsTest {
	@SuppressWarnings({"InstantiationOfUtilityClass"})
	@Test
	public void smokeTest() {
		EntityUtils utils = new EntityUtils();
		assertNotNull(utils);
	}

	@Test
	public void shouldSayNullIsNull() {
		assertTrue(EntityUtils.isNull(null));
	}

	@Test
	public void shouldSayNullIdIsNull() {
		DummyEntity entity = new DummyEntity();
		assertTrue(EntityUtils.isNull(entity));
	}

	@Test
	public void shouldSayNonNullIsntNull() {
		DummyEntity entity = new DummyEntity(1L);
		assertFalse(EntityUtils.isNull(entity));
	}

	@Test
	public void shouldSayNonNullIsNonNull() {
		DummyEntity entity = new DummyEntity(1L);
		assertTrue(EntityUtils.nonNull(entity));
	}

	@Test
	public void shouldSayNullIsntNonNull() {
		assertFalse(EntityUtils.nonNull(null));
	}
}
