package adiitya.mojang4j.http.response;

import adiitya.mojang4j.http.ResponseHandler;
import adiitya.mojang4j.status.MojangService;
import adiitya.mojang4j.status.MojangServices;
import adiitya.mojang4j.status.ServiceStatus;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;
import java.util.stream.StreamSupport;

public class ServiceHandler implements ResponseHandler<MojangServices> {

	@Override
	public Optional<MojangServices> handle(int code, JsonElement json) {

		List<MojangService> services = new ArrayList<>();
		JsonArray serviceResponse = json.getAsJsonArray();

		if (code != 200)
			return Optional.empty();

		StreamSupport.stream(serviceResponse.spliterator(), false)
				.map(JsonElement::getAsJsonObject)
				.map(JsonObject::entrySet)
				.forEach(set ->
						set.forEach(e ->
								services.add(new MojangService(e.getKey(), getStatus(e.getValue())))));

		/*for (JsonElement service : serviceResponse) {

			Set<Map.Entry<String, JsonElement>> entries = service.getAsJsonObject().entrySet();

			for (Map.Entry<String, JsonElement> entry : entries) {
				services.add(new MojangService(entry.getKey(), getStatus(entry.getValue())));
			}
		}*/

		return Optional.of(new MojangServices(services));
	}

	private ServiceStatus getStatus(JsonElement e) {
		return ServiceStatus.get(e.getAsString());
	}
}
