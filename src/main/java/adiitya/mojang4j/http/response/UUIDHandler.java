package adiitya.mojang4j.http.response;

import adiitya.mojang4j.UUIDUtils;
import adiitya.mojang4j.http.ResponseHandler;
import com.google.gson.JsonElement;

import java.util.Optional;
import java.util.UUID;

public final class UUIDHandler implements ResponseHandler<UUID> {

	@Override
	public Optional<UUID> handle(int code, JsonElement json) {

		JsonElement id = json
				.getAsJsonObject()
				.get("id");

		return Optional.of(UUIDUtils.addHyphens(id.getAsString()));
	}
}
