package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

	private final static String VERY_LONG_STRING = "Et eos laboriosam atque labore aut consequatur. Dolor mollitia " +
		"laboriosam facere animi est odio aut. Fugit mollitia magni consequatur harum dignissimos ex quia. Velit et aut " +
		"ut qui. Asperiores enim corrupti quos iste. Veritatis fuga atque ut placeat dignissimos omnis nulla.";

	@Test
	void toList() {
		List<String> stringList = new ArrayList<>();
		stringList.add("Hello");
		stringList.add("World");
		assertEquals(stringList, StringUtils.toList("Hello", "World"));
	}

	@Test
	void veryLongStringToList() {
		List<String> stringList = new ArrayList<>();
		stringList.add("Hello");
		stringList.add("World");
		stringList.add("My");
		stringList.add("Name");
		stringList.add("Is");
		stringList.add("Slim");
		stringList.add("Shady");
		stringList.add("Hello");
		stringList.add("World");
		stringList.add("My");
		stringList.add("Name");
		stringList.add("Is");
		stringList.add("Slim");
		stringList.add("Shady");
		assertEquals(stringList, StringUtils.toList("Hello", "World", "My", "Name", "Is", "Slim", "Shady", "Hello",
			"World", "My", "Name", "Is", "Slim", "Shady"));
	}

	@Test
	void isNullOrBlank() {
		assertTrue(StringUtils.isNullOrBlank(null));
		assertTrue(StringUtils.isNullOrBlank(""));
		assertFalse(StringUtils.isNullOrBlank("Hello!"));
		assertFalse(StringUtils.isNullOrBlank(VERY_LONG_STRING));
	}

	@Test
	void isNonNullOrBlank() {
		assertFalse(StringUtils.isNonNullOrBlank(null));
		assertFalse(StringUtils.isNonNullOrBlank(""));
		assertTrue(StringUtils.isNonNullOrBlank("Hello!"));
		assertTrue(StringUtils.isNonNullOrBlank(VERY_LONG_STRING));
	}
}