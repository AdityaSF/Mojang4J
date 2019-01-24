package adiitya.mojang4j.http.response;

import adiitya.mojang4j.status.MojangServices;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServiceHandlerTest extends HandlerTest<MojangServices, JsonArray> {

	@Override
	@BeforeEach
	public void beforeEach() {
		json = new JsonArray();
		handler = new ServiceHandler();
	}

	@Override
	protected JsonArray getValidJson() {
		json = new Gson().fromJson("[{\"minecraft.net\":\"green\"},{\"session.minecraft.net\":\"green\"},{\"account.mojang.com\":\"green\"},{\"authserver.mojang.com\":\"green\"},{\"sessionserver.mojang.com\":\"green\"},{\"api.mojang.com\":\"green\"},{\"textures.minecraft.net\":\"green\"},{\"mojang.com\":\"green\"}]", JsonArray.class);
		return json;
	}

	@Override
	protected JsonArray getInvalidJson() {

		json = new Gson().fromJson("[{\"service\": \"invalid\"}]", JsonArray.class);

		return json;
	}

	@Override
	@Test
	protected void shouldPassWithValidJson() {

		Optional<MojangServices> services = handler.handle(200, getValidJson());

		assertAll(
				() -> assertTrue(services.isPresent()),
				() -> assertTrue(services.get().iterator().hasNext())
		);
	}

	@Test
	@Override
	protected void shouldFailWithInvalidJson() {

		assertThrows(IllegalArgumentException.class, () -> handler.handle(200, getInvalidJson()));
	}
}