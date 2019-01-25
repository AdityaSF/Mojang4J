package adiitya.mojang4j;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

	private static final UserProfile ADIITYA = new UserProfile(UUID.fromString("00000000-0000-0000-0000-000000000000"), "Adiitya");

	@Test
	void checkEquality() {

		UserProfile equal = new UserProfile(UUID.fromString("00000000-0000-0000-0000-000000000000"), "Adiitya");
		UserProfile unequalUUID = new UserProfile(UUID.fromString("00000010-0000-0000-0000-000000000000"), "Adiitya");
		UserProfile unequalName = new UserProfile(UUID.fromString("00000000-0000-0000-0000-000000000000"), "Aditya");

		assertAll(
				() -> assertEquals(ADIITYA, equal),
				() -> assertNotEquals(ADIITYA, unequalUUID),
				() -> assertNotEquals(ADIITYA, unequalName)
		);
	}
}