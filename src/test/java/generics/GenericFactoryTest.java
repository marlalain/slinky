package generics;

import entities.DummyEntity;
import exceptions.GenericException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericFactoryTest {
	@Test
	void smokeTest() {
		DummyEntity dummyEntity1 = new DummyEntity(1L);
		try {
			Field[] fieldList = DummyEntity.class.getDeclaredFields();
			Class<?>[] classes = Arrays.stream(fieldList)
				.map(Field::getType)
				.toArray(Class<?>[]::new);

			Constructor<? extends DummyEntity> constructor = dummyEntity1.getClass().getConstructor(classes);
			@SuppressWarnings({"unused"}) DummyEntity dummyEntity2 = constructor.newInstance(1L);
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void shouldCreateNewInstanceOfClass() throws GenericException {
		DummyEntity dummyEntity1 = new DummyEntity(1L);
		DummyEntity dummyEntity2 = GenericFactory.getInstance(dummyEntity1, DummyEntity.class);
		assertEquals(1L, dummyEntity2.getId());
	}
}