package adiitya.mojang4j;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;

@UtilityClass
public class Endpoints {

	private final String STATUS = "https://status.mojang.com/check";

	public URI getStatus() {
		return getURI(STATUS);
	}

	private URI getURI(String format, Object... args) {
		try {
			return new URI(String.format(format, args));
		} catch(URISyntaxException ignored) {
			return null;
		}
	}
}
