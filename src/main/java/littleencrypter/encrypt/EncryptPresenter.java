package littleencrypter.encrypt;

import java.util.Base64;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.business.Config;
import littleencrypter.business.CryptoService;
import littleencrypter.business.CryptoService.Result;

public class EncryptPresenter {

	@Inject
	private Config config;

	@Inject
	private CryptoService cryptoService;

	@FXML
	private PasswordField passwordTxt;

	@FXML
	private TextArea encryptedTxt;

	@FXML
	private TextArea ivTxt;

	public void initialize() {
		ivTxt.setDisable(!AppUtils.requiresIv(config.getCipher()));
	}

	@FXML
	public void copyEncrypted() {
		AppUtils.toClipboard(encryptedTxt.getText());
	}

	@FXML
	public void copyIv() {
		AppUtils.toClipboard(ivTxt.getText());
	}

	@FXML
	public void encrypt() {
		byte[] key = Base64.getDecoder().decode(System.getenv(config.getKeyVariable()));
		byte[] password = AppUtils.getBytes(passwordTxt.getText());
		Result result = cryptoService.encrypt(key, password);
		encryptedTxt.setText(result.getCipherText());
		ivTxt.setText(result.getIv());
	}

}
