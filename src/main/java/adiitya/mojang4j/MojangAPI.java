package adiitya.mojang4j;

public final class MojangAPI {

	private static MojangAPI instance;

	private MojangAPI() {
		if (instance != null) throw new IllegalStateException("Only one instance of MojangAPI can exist");
	}

	public static synchronized MojangAPI getInstance() {

		if (instance == null) instance = new MojangAPI();

		return instance;
	}
}
