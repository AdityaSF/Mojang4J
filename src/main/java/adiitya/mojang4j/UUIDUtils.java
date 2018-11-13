package adiitya.mojang4j;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtils {

	private static final String UUID_REGEX = "^((\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12}))$";

	public UUID addHyphens(String id) {

		String uuid = id.replaceAll(UUID_REGEX, "$2-$3-$4-$5-$6");
		return UUID.fromString(uuid);
	}

	public String removeHyphens(UUID uuid) {
		return uuid.toString().replace("-", "");
	}
}
