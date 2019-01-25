package adiitya.mojang4j.http.response;

import adiitya.mojang4j.UUIDUtils;
import adiitya.mojang4j.UserProfile;
import adiitya.mojang4j.http.ResponseHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Optional;

public final class ProfileHandler implements ResponseHandler<UserProfile> {

	@Override
	public Optional<UserProfile> handle(int code, JsonElement json) {

		JsonObject profile = json.getAsJsonObject();
		String uuid = profile.get("id").getAsString();
		String username = profile.get("name").getAsString();

		System.out.println(profile.toString());

		return Optional.of(new UserProfile(UUIDUtils.addHyphens(uuid), username));
	}
}
