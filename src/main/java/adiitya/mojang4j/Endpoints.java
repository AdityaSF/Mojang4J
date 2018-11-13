package adiitya.mojang4j;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * <h1>Endpoints</h1>
 *
 * This class is responsible for preparing a {@link URI} for the requested endpoint.
 * All methods will return {@code null} should an error occur.
 */
@UtilityClass
public class Endpoints {

	private static final String STATUS = "https://status.mojang.com/check";
	private static final String UUID_FROM_USERNAME = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";
	private static final String PROFILE = "https://sessionserver.mojang.com/session/minecraft/profile/%s";


	public URI getStatus() {
		return getURI(STATUS);
	}

	/**
	 * This method builds a {@link URI} that retrieves a {@link UUID} from a username. This method
	 * returns the UUID of the current user with that username.
	 *
	 * @param username The username of the target user
	 * @return The prepared URI
	 *
	 * @see #getUUID(String, long)
	 */
	public URI getUUID(String username) {
		return getUUID(username, System.currentTimeMillis() / 1000L);
	}

	/**
	 * This method builds a {@link URI} that retreives the {@link UUID} of the user
	 * at the specified time.
	 *
	 * @param username The username of the target user
	 * @param at The point in time to check
	 * @return The prepared URI
	 *
	 * @see #getUUID(String)
	 */
	public URI getUUID(String username, long at) {
		return getURI(UUID_FROM_USERNAME, username, at);
	}

	public URI getProfile(UUID uuid) {
		return getURI(PROFILE, UUIDUtils.removeHyphens(uuid));
	}

	private URI getURI(String format, Object... args) {
		try {
			return new URI(String.format(format, args));
		} catch(URISyntaxException ignored) {
			return null;
		}
	}
}
