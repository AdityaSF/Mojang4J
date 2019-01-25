package adiitya.mojang4j;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EndpointsTest {

	private static final UUID ADIITYA = UUID.fromString("06882ed8-a374-4198-899f-f604c989b377");

	private static final String STATUS = "https://status.mojang.com/check";
	private static final String UUID_AT = "https://api.mojang.com/users/profiles/minecraft/Adiitya?at=%d";
	private static final String PROFILE = "https://sessionserver.mojang.com/session/minecraft/profile/" + UUIDUtils.removeHyphens(ADIITYA);

	@Test
	void getStatus() {

		assertEquals(STATUS, Endpoints.getStatus().toString());
	}

	@Test
	void getUUID() {

		assertEquals(String.format(UUID_AT, System.currentTimeMillis() / 1000L), Endpoints.getUUID("Adiitya").toString());
	}

	@Test
	void getUUIDAt() {

		long time = (System.currentTimeMillis() - 1000L) / 1000L;

		assertEquals(String.format(UUID_AT, time), Endpoints.getUUID("Adiitya", time).toString());
	}

	@Test
	void getProfile() {

		assertEquals(PROFILE, Endpoints.getProfile(ADIITYA).toString());
	}
}