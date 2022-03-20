import org.junit.Assert;
import org.junit.Test;
import utils.EntityUtils;

import java.util.Objects;

public class EntityUtilsTest {
	@SuppressWarnings({"InstantiationOfUtilityClass", "ConstantConditions"})
	@Test
	public void smokeTest() {
		EntityUtils utils = new EntityUtils();
		Assert.assertTrue(Objects.nonNull(utils));
	}

	@Test
	public void shouldSayNullIsNull() {
		Assert.assertTrue(EntityUtils.isNull(null));
	}

	@Test
	public void shouldSayNullIdIsNull() {
		DummyEntity entity = new DummyEntity();
		Assert.assertTrue(EntityUtils.isNull(entity));
	}

	@Test
	public void shouldSayNonNullIsntNull() {
		DummyEntity entity = new DummyEntity();
		entity.setId(1L);
		Assert.assertFalse(EntityUtils.isNull(entity));
	}

	@Test
	public void shouldSayNonNullIsNonNull() {
		DummyEntity entity = new DummyEntity();
		entity.setId(1L);
		Assert.assertTrue(EntityUtils.nonNull(entity));
	}

	@Test
	public void shouldSayNullIsntNonNull() {
		Assert.assertFalse(EntityUtils.nonNull(null));
	}
}
