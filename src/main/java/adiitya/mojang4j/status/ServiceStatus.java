package adiitya.mojang4j.status;

public enum ServiceStatus {

	/** The service has no issues */
	GREEN,

	/** The services has some issues */
	YELLOW,

	/** The service is unavailable */
	RED;

	public static ServiceStatus get(String name) {
		return valueOf(name.toUpperCase());
	}
}
