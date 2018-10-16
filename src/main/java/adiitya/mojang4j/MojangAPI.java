package adiitya.mojang4j;

import adiitya.mojang4j.http.RequestLimiter;
import adiitya.mojang4j.http.ResponseHandler;
import adiitya.mojang4j.http.response.*;
import adiitya.mojang4j.status.MojangServices;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <h1>MojangAPI</h1>
 *
 * The MojangAPI class handles all interactions with the API. All of the API endpoints can be accessed through this class.
 * You can retrieve an instance of this class through the {@link #getInstance()} method. This class is rate limited to
 * 600 requests per 10 minutes. More infomation about the Mojang API can be found <a href="https://wiki.vg/Mojang_API">here</a>.
 */
public final class MojangAPI {

	private static MojangAPI instance;
	private static CloseableHttpClient client = HttpClients.createDefault();
	private static RequestLimiter requestLimiter = new RequestLimiter(600, TimeUnit.MINUTES.toMillis(10));

	private static final ResponseHandler<MojangServices> serviceHandler = new ServiceHandler();
	private static final ResponseHandler<UUID>           uuidHandler    = new UUIDHandler();

	/**
	 * This method retreives the status of each Mojang service. This is expected to be
	 * cached as it is unlikely to change.
	 *
	 * @return An {@link Optional} containing the {@link MojangServices}
	 * @see MojangServices
	 * @see Optional
	 */
	public Optional<MojangServices> getServices() {
		return request(Endpoints.getStatus(), serviceHandler);
	}

	public Optional<UUID> getUUID(String username) {
		return request(Endpoints.getUUID(username), uuidHandler);
	}

	private <T> Optional<T> request(URI uri, ResponseHandler<T> handler) {

		if (!requestLimiter.canRequest())
			return Optional.empty();

		HttpGet request = new HttpGet(uri);

		try (CloseableHttpResponse response = client.execute(request)) {

			requestLimiter.request(response);

			int code = response.getStatusLine().getStatusCode();
			InputStream content = response.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(content);

			JsonElement json = new JsonParser().parse(reader);
			reader.close();

			if (code != 200)
				return Optional.empty();

			return handler.handle(code, json);
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	private MojangAPI() {

		if (instance != null) throw new IllegalStateException("Only one instance of MojangAPI can exist");
		Runtime.getRuntime().addShutdownHook(new Thread(MojangAPI::disconnect));
	}

	private static void disconnect() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized MojangAPI getInstance() {

		if (instance == null) instance = new MojangAPI();

		return instance;
	}
}
