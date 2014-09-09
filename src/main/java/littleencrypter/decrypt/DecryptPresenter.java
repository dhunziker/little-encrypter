package littleencrypter.decrypt;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.business.Config;
import littleencrypter.business.ConfigLoader;
import littleencrypter.business.CryptoService;

public class DecryptPresenter {

	private static final Config CONFIG = ConfigLoader.INSTANCE.getConfig();

	@Inject
	private CryptoService cryptoService;

	@FXML
	private TextArea encryptedTxt;

	@FXML
	private TextArea ivTxt;

	@FXML
	private TextField passwordTxt;

	public void initialize() {
		ivTxt.setDisable(!AppUtils.requiresIv(CONFIG.getCipher()));
	}

	@FXML
	public void copyPassword() {
		AppUtils.toClipboard(passwordTxt.getText());
	}

	@FXML
	public void decrypt() {
		byte[] key = AppUtils.getKey(CONFIG.getKeyVariable());
		byte[] result = cryptoService.decrypt(key, ivTxt.getText(), encryptedTxt.getText());
		passwordTxt.setText(new String(result));
	}

}
