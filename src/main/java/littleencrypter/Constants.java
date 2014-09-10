package littleencrypter;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Constants {

	public static final String CHARSET = "UTF-8";

	public static final ObservableList<String> ALGORITHMS = FXCollections
			.observableArrayList("AES");

	public static final ObservableList<String> CIPHERS = FXCollections.observableArrayList(
			"AES/ECB/PKCS5Padding", "AES/CBC/PKCS5Padding");

	public static final List<String> REQUIRES_IV = Arrays.asList("AES/CBC/PKCS5Padding");

	public static final int KEY_LENGTH = 256;

	private Constants() {
	}

}
