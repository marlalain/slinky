package utils.localized.brazil;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class CpfValidator {
	public static final Pattern FORMATTED = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");
	public static final Pattern UNFORMATTED = Pattern.compile("(\\d{11})");
	private final String cpf;
	private boolean isFormatted = true;

	public CpfValidator(String cpf) {
		handleNullValue(cpf);

		this.cpf = cpf;
	}

	public CpfValidator(Long cpf) {
		handleNullValue(cpf);

		this.cpf = cpf.toString();
		isUnformatted();
	}

	public CpfValidator(int cpf) {
		this.cpf = cpf + "";
		isUnformatted();
	}

	public CpfValidator isUnformatted() {
		this.isFormatted = false;
		return this;
	}

	private void handleNullValue(Object cpf) {
		if (cpf instanceof String && ((String) cpf).isBlank())
			throw new IllegalArgumentException("Cpf can't be a blank string");
		if (isNull(cpf)) throw new IllegalArgumentException("Cpf should be a non null value");
	}

	public boolean isValid() {
		if (isFormatted)
			return FORMATTED.matcher(this.cpf).matches();
		else
			return UNFORMATTED.matcher(this.cpf).matches();
	}
}
