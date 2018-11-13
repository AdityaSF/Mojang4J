package adiitya.mojang4j;

import lombok.Getter;

import java.util.UUID;

public final class UserProfile {

	@Getter private final UUID uuid;
	@Getter private final String username;

	public UserProfile(UUID uuid, String username) {
		this.uuid = uuid;
		this.username = username;
	}
}
