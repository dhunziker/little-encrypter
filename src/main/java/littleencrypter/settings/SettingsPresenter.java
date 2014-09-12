package littleencrypter.settings;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.Constants;
import littleencrypter.business.Config;
import littleencrypter.business.CryptoService;

public class SettingsPresenter {

	@Inject
	private Config config;

	@Inject
	private CryptoService cryptoService;

	@FXML
	private ComboBox<String> algorithmCmb;

	@FXML
	private ComboBox<String> cipherCmb;

	@FXML
	private TextField keyVariableTxt;

	@FXML
	private TextArea currentKeyTxt;

	@FXML
	private TextArea newKeyTxt;

	public void initialize() {
		algorithmCmb.setItems(Constants.ALGORITHMS);
		algorithmCmb.setValue(config.getAlgorithm());

		cipherCmb.setItems(Constants.CIPHERS);
		cipherCmb.setValue(config.getCipher());

		String keyVariable = config.getKeyVariable();
		keyVariableTxt.setText(keyVariable);
		currentKeyTxt.setText(System.getenv(keyVariable));
	}

	@FXML
	public void copyCurrentKey() {
		AppUtils.toClipboard(currentKeyTxt.getText());
	}

	@FXML
	public void copyNewKey() {
		AppUtils.toClipboard(newKeyTxt.getText());
	}

	@FXML
	public void generate() {
		newKeyTxt.setText(cryptoService.generateKeyString());
	}

	@FXML
	public void save() {
		config.setAlgorithm(algorithmCmb.getValue());
		config.setCipher(cipherCmb.getValue());
		config.setKeyVariable(keyVariableTxt.getText());
	}

}
