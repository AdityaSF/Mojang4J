package adiitya.mojang4j.http.response;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UUIDHandlerTests extends HandlerTest<UUID, JsonObject> {

	@Override
	@BeforeEach
	public void beforeEach() {
		json = new JsonObject();
		handler = new UUIDHandler();
	}

	@Override
	protected JsonObject getValidJson() {
		json.addProperty("id", "00000000-0000-0000-0000-000000000000");
		return json;
	}

	@Override
	protected JsonObject getInvalidJson() {
		json.addProperty("uuid", "this is not a valid uuid");
		return json;
	}

	@Override
	@Test
	public void shouldFailWithInvalidJson() {
		assertThrows(NullPointerException.class, () -> handler.handle(200, getInvalidJson()));
	}

	@Override
	@Test
	public void shouldPassWithValidJson() {
		assertDoesNotThrow(() -> handler.handle(200, getValidJson()));
	}
}