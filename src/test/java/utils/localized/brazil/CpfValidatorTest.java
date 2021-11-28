package utils.localized.brazil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CpfValidatorTest {

	@Test
	void validateFormattedCpf() {
		String cpf = "711.667.680-58";
		CpfValidator cpfValidator = new CpfValidator(cpf);
		assertTrue(cpfValidator.isValid());
	}

	@Test
	void validateUnformattedCpf() {
		String cpf = "34112138091";
		CpfValidator cpfValidator = new CpfValidator(cpf).isUnformatted();
		assertTrue(cpfValidator.isValid());
	}

	@Test
	void canHandleLongCpf() {
		Long cpf = 71166768058L;
		CpfValidator cpfValidator = new CpfValidator(cpf);
		assertTrue(cpfValidator.isValid());
	}

	@Test
	void canHandleNullValues() {
		assertThrows(IllegalArgumentException.class, () -> new CpfValidator((Long) null));
		assertThrows(IllegalArgumentException.class, () -> new CpfValidator((String) null));
		assertThrows(IllegalArgumentException.class, () -> new CpfValidator((String) null).isUnformatted());
	}
}