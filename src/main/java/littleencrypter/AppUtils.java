package littleencrypter;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

import com.airhacks.afterburner.views.FXMLView;

public class AppUtils {

	public static void setView(Pane pane, FXMLView fxmlView) {
		fxmlView.getViewAsync(pane.getChildren()::setAll);
	}

	public static void toClipboard(String value) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(value);
		clipboard.setContent(content);
	}

	public static byte[] getKey(String keyVariable) {
		return Base64.getDecoder().decode(System.getenv(keyVariable));
	}

	public static boolean nullOrEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	public static byte[] getBytes(String string) {
		try {
			return string.getBytes(Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(String.format(
					"Failed to get bytes from String using charset %s", Constants.CHARSET), e);
		}
	}

	public static boolean requiresIv(String cipher) {
		return Constants.REQUIRES_IV.contains(cipher);
	}

}
