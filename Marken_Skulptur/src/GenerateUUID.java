import java.util.UUID;

public class GenerateUUID {

	public static UUID id;

	public GenerateUUID() {

		id = UUID.randomUUID();
		log("UUID One: " + id);

	}

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}
}