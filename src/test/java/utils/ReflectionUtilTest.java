package utils;

import entities.DummyEntity;
import exceptions.GenericException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import utils.readonly.EmbeddableObject;
import utils.readonly.ObjectWithSerialVersionField;
import utils.readonly.ObjectWithTransientField;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class ReflectionUtilTest {

	private Field getFirstField(List<Field> fields) {
		return fields.get(0);
	}

	private Field getFirstFieldFromObject(Object object) {
		return getFirstField(List.of(object.getClass().getDeclaredFields()));
	}

	@Test
	void getIdField() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		Field idField = getFirstField(FieldUtils.getFieldsListWithAnnotation(DummyEntity.class, Id.class));
		assertEquals(idField, ReflectionUtil.getIdField(dummyEntity));
	}

	@Test
	void getValueByField() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		Field idField = ReflectionUtil.getIdField(dummyEntity);
		try {
			assertEquals(dummyEntity.getId(), ReflectionUtil.getValueByField(idField, dummyEntity));
		} catch (GenericException e) {
			fail();
		}
	}

	@Test
	void isSerialVersionField() {
		Field field = getFirstField(FieldUtils.getAllFieldsList(ObjectWithSerialVersionField.class));
		assertTrue(ReflectionUtil.isSerialVersionField(field));
	}

	@Test
	void isFieldNotNull() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		Field field = getFirstField(FieldUtils.getAllFieldsList(DummyEntity.class));
		assertTrue(ReflectionUtil.isFieldNotNull(field, dummyEntity));
	}

	@Test
	void isUpdatable() {
	}

	@Test
	void isSubclassIterable() {
	}

	@Test
	void isMappedByField() {
	}

	@Test
	void isFieldUpdatable() {
	}

	@Test
	void isFieldNull() {
		DummyEntity dummyEntityWithNullId = new DummyEntity(null);
		Field fieldWithNullId = getFirstFieldFromObject(dummyEntityWithNullId);
		assertTrue(ReflectionUtil.isFieldNull(fieldWithNullId, dummyEntityWithNullId));

		DummyEntity dummyEntity = new DummyEntity(1L);
		Field field = getFirstFieldFromObject(dummyEntity);
		assertFalse(ReflectionUtil.isFieldNull(field, dummyEntity));
	}

	@Test
	void isIdField() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		Field field = getFirstFieldFromObject(dummyEntity);
		assertFalse(ReflectionUtil.isIdField(field));
	}

	@Test
	void isTransient() {
		ObjectWithTransientField object = new ObjectWithTransientField();
		Field field = getFirstFieldFromObject(object);
		assertTrue(ReflectionUtil.isTransient(field));
	}

	@Test
	void isEntity() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		Field entityField = getFirstFieldFromObject(dummyEntity);
		assertTrue(ReflectionUtil.isEntity(entityField));
		assertTrue(ReflectionUtil.isEntity(dummyEntity));
	}

	@Test
	void isEmbeddable() {
		EmbeddableObject embeddableObject = new EmbeddableObject();
		Field field = getFirstFieldFromObject(embeddableObject);
		assertTrue(ReflectionUtil.isEmbeddable(field));
		assertTrue(ReflectionUtil.isEmbeddable(embeddableObject));
	}
}