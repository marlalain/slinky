import org.junit.Test;
import utils.ObjectUtils;

import static org.junit.Assert.*;

public class ObjectUtilsTest {
	private void throwNullPointerException() {
		throw new NullPointerException();
	}

	private boolean booleanThrowNullPointerException() {
		throw new NullPointerException();
	}

	@Test
	public void smokeText() {
		assertThrows(NullPointerException.class, this::throwNullPointerException);
	}

	@Test
	public void shouldntThrowNullPointerException() {
		assertTrue(ObjectUtils.safeNullEval(this::booleanThrowNullPointerException));
	}

	@Test
	public void shouldHandleNull() {
		assertTrue(ObjectUtils.safeNullEval(null));
	}

	@Test
	public void shouldHandleNonNull() {
		assertTrue(ObjectUtils.safeNonNullEval(Object::new));
	}

	@Test
	public void safeEvalShouldReturnValue() {
		DummyEntity entity = new DummyEntity();
		entity.setId(1L);
		assertNotNull(ObjectUtils.safeEval(entity::getId));
	}

	@Test
	public void safeEvalShouldReturnNull() {
		DummyEntity entity = new DummyEntity();
		assertNull(ObjectUtils.safeEval(entity::getId));
	}
}
