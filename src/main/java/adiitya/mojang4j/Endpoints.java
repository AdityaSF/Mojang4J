package adiitya.mojang4j;

import lombok.experimental.UtilityClass;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h1>Endpoints</h1>
 *
 * This class is responsible for preparing a {@link URI} for the requested endpoint.
 * All methods will return {@code null} should an error occur.
 */
@UtilityClass
public class Endpoints {

	private static final String STATUS_HOST =  "status.mojang.com";
	private static final String API_HOST =     "api.mojang.com";
	private static final String SESSION_HOST = "sessionserver.mojang.com";

	private static final String STATUS_PATH = "check";
	private static final String UUID_PATH = "users/profiles/minecraft/%s";
	private static final String PROFILE_PATH = "session/minecraft/profile/%s";

	public URI getStatus() {
		return getURI(STATUS_HOST, STATUS_PATH);
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
		return getURI(API_HOST, UUID_PATH, new Object[] {"at", at}, username);
	}

	public URI getProfile(UUID uuid) {
		return getURI(SESSION_HOST, PROFILE_PATH, UUIDUtils.removeHyphens(uuid));
	}

	private URI getURI(String host, String path, Object... params) {
		return getURI(host, path, new Object[0], params);
	}

	private URI getURI(String host, String path, Object[] uriParams, Object... params) {
		try {

			return new URIBuilder()
					.setScheme("https")
					.setHost(host)
					.setPath(String.format(path, params))
					.addParameters(generatePairs(uriParams))
					.build();
		} catch(Exception ignored) {
			return null;
		}
	}

	private List<NameValuePair> generatePairs(Object[] pairs) throws Exception {

		if (pairs.length % 2 != 0)
			throw new Exception("Uneven key value pairs");

		List<NameValuePair> pairList = new ArrayList<>();

		for (int i = 0; i < pairs.length / 2; i += 2) {

			Object key = pairs[i];
			Object value = pairs[i + 1];

			pairList.add(new BasicNameValuePair(key.toString(), value.toString()));
		}

		return pairList;
	}
}
