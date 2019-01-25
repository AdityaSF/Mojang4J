package adiitya.mojang4j.http.response;

import adiitya.mojang4j.UserProfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProfileHandlerTests extends HandlerTest<UserProfile, JsonObject> {

	@Override
	@BeforeEach
	public void beforeEach() {
		json = new JsonObject();
		handler = new ProfileHandler();
	}

	@Override
	protected JsonObject getValidJson() {

		json = new Gson().fromJson("{\"id\":\"00000000000000000000000000000000\",\"name\":\"Username\"}", JsonObject.class);

		return json;
	}

	@Override
	protected JsonObject getInvalidJson() {

		json = new Gson().fromJson("{}", JsonObject.class);

		return json;
	}

	@Test
	@Override
	protected void shouldPassWithValidJson() {

		Optional<UserProfile> profile = handler.handle(200, getValidJson());

		assertTrue(profile.isPresent());
	}

	@Test
	@Override
	protected void shouldFailWithInvalidJson() {

		assertThrows(NullPointerException.class, () -> handler.handle(200, getInvalidJson()));
	}
}
