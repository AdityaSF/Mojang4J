package adiitya.mojang4j;

import adiitya.mojang4j.status.MojangService;
import adiitya.mojang4j.status.MojangServices;
import adiitya.mojang4j.status.ServiceStatus;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MojangAPI {

	private static MojangAPI instance;
	private static CloseableHttpClient client = HttpClients.createDefault();

	public Optional<MojangServices> getServices() {

		HttpGet request = new HttpGet(Endpoints.getStatus());

		try (CloseableHttpResponse response = client.execute(request)) {

			if (response.getStatusLine().getStatusCode() != 200)
				return Optional.empty();

			List<MojangService> services = new ArrayList<>();
			JsonArray serviceResponse = new JsonParser()
					.parse(new InputStreamReader(response.getEntity().getContent()))
					.getAsJsonArray();

			for (JsonElement service : serviceResponse) {
				for (Map.Entry<String, JsonElement> entry : service.getAsJsonObject().entrySet()) {
					services.add(new MojangService(entry.getKey(), ServiceStatus.valueOf(entry.getValue().getAsString().toUpperCase())));
				}
			}

			return Optional.of(new MojangServices(services));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	private MojangAPI() {
		if (instance != null) throw new IllegalStateException("Only one instance of MojangAPI can exist");
	}

	public static synchronized MojangAPI getInstance() {

		if (instance == null) instance = new MojangAPI();

		return instance;
	}
}
