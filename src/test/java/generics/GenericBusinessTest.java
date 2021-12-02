package generics;

import entities.DummyEntity;
import entities.DummyEntityWithMultipleFields;
import exceptions.GenericException;
import generics.readonly.Application;
import generics.readonly.business.Business;
import generics.readonly.business.MultipleFieldsEntityBusiness;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
class GenericBusinessTest {

	@MockBean
	private Business business;

	@MockBean
	private MultipleFieldsEntityBusiness multipleFieldsEntityBusiness;

	private void finish() {
		assertTrue(true);
	}

	@BeforeEach
	void cleanEntities() {
		this.business.deleteAll();
	}

	@Test
	void persist() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		DummyEntityWithMultipleFields dummyEntityWithMultipleFields =
			new DummyEntityWithMultipleFields(1L, "Entity", "Something");

		business.persist(dummyEntity);
		multipleFieldsEntityBusiness.persist(dummyEntityWithMultipleFields);

		finish();
	}

	@Test
	void update() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		DummyEntityWithMultipleFields dummyEntityWithMultipleFields =
			new DummyEntityWithMultipleFields(1L, "Entity", "Something");

		business.persist(dummyEntity);
		multipleFieldsEntityBusiness.persist(dummyEntityWithMultipleFields);

		dummyEntity.setId(2L);
		dummyEntityWithMultipleFields.setId(2L);

		business.update(dummyEntity);
		multipleFieldsEntityBusiness.update(dummyEntityWithMultipleFields);

		finish();
	}

	@Test
	void updateOnlyCertainFields() {
		DummyEntityWithMultipleFields dummyEntityWithMultipleFields =
			new DummyEntityWithMultipleFields(1L, "Entity", "Something");

		dummyEntityWithMultipleFields.setName("Dummy");
		dummyEntityWithMultipleFields.setOccupation("Dummy");

		try {
			multipleFieldsEntityBusiness.update(dummyEntityWithMultipleFields, "name", "occupation");
			finish();
		} catch (NoSuchFieldException | GenericException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void updateWithListOfFields() {
	}

	@Test
	void updateNonNullFields() {
	}

	@Test
	void deleteById() {
	}

	@Test
	void delete() {
	}

	@Test
	void deleteAllById() {
	}

	@Test
	void deleteAll() {
	}

	@Test
	void testDeleteAll() {
	}

	@Test
	void persistOrUpdate() {
	}

	@Test
	void testPersistOrUpdate() {
	}

	@Test
	void persisOrUpdateNonNullFields() {
	}

	@Test
	void persistOrUpdateNonNullFields() {
	}

	@Test
	void findAll() {
		DummyEntity dummyEntity = new DummyEntity(1L);
		business.persist(dummyEntity);
		List<DummyEntity> dummyEntityList = business.findAll();
		assertTrue(dummyEntityList.size() > 0);
	}

	@Test
	void testFindAll() {
	}

	@Test
	void testFindAll1() {
	}

	@Test
	void findAllById() {
	}

	@Test
	void count() {
	}

	@Test
	void save() {
	}

	@Test
	void saveAll() {
	}

	@Test
	void findById() {
	}

	@Test
	void existsById() {
	}

	@Test
	void flush() {
	}

	@Test
	void saveAndFlush() {
	}

	@Test
	void saveAllAndFlush() {
	}

	@Test
	void deleteAllInBatch() {
	}

	@Test
	void deleteAllByIdInBatch() {
	}

	@Test
	void testDeleteAllInBatch() {
	}

	@Test
	void getOne() {
	}

	@Test
	void getById() {
	}

	@Test
	void findOne() {
	}

	@Test
	void testFindAll2() {
	}

	@Test
	void testFindAll3() {
	}

	@Test
	void testFindAll4() {
	}

	@Test
	void testCount() {
	}

	@Test
	void exists() {
	}

	@Test
	void findBy() {
	}
}