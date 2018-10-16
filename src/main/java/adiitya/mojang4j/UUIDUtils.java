package adiitya.mojang4j;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtils {

	public UUID addHyphens(String id) {

		String uuid = id.replaceAll("^((\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12}))$", "$2-$3-$4-$5-$6");
		return UUID.fromString(uuid);
	}
}
