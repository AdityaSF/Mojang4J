package adiitya.mojang4j;

import lombok.Data;

import java.util.UUID;

@Data
public final class UserProfile {

	private final UUID uuid;
	private final String username;
}
