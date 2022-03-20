package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class StringUtils {
	public static List<String> toList(String... strings) {
		return Arrays.stream(strings).collect(Collectors.toList());
	}

	public static boolean isNullOrBlank(String s) {
		return isNull(s) || s.isBlank();
	}

	public static boolean isNonNullOrBlank(String s) {
		return !isNullOrBlank(s);
	}
}
