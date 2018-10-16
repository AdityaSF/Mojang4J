package adiitya.mojang4j;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;

@UtilityClass
public class Endpoints {

	private static final String STATUS = "https://status.mojang.com/check";
	private static final String UUID_FROM_USERNAME = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";

	public URI getStatus() {
		return getURI(STATUS);
	}

	public URI getUUID(String username) {
		return getUUID(username, System.currentTimeMillis());
	}

	public URI getUUID(String username, long at) {
		return getURI(UUID_FROM_USERNAME, username, at);
	}

	private URI getURI(String format, Object... args) {
		try {
			return new URI(String.format(format, args));
		} catch(URISyntaxException ignored) {
			return null;
		}
	}
}
