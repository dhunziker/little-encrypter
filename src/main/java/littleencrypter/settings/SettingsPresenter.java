package littleencrypter.settings;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.Constants;
import littleencrypter.business.Config;
import littleencrypter.business.ConfigLoader;
import littleencrypter.business.CryptoService;

public class SettingsPresenter {

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
		Config cfg = ConfigLoader.INSTANCE.getConfig();

		algorithmCmb.setItems(Constants.ALGORITHMS);
		algorithmCmb.setValue(cfg.getAlgorithm());

		cipherCmb.setItems(Constants.CIPHERS);
		cipherCmb.setValue(cfg.getCipher());

		String keyVariable = cfg.getKeyVariable();
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
		Config cfg = ConfigLoader.INSTANCE.getConfig();
		cfg.setAlgorithm(algorithmCmb.getValue());
		cfg.setCipher(cipherCmb.getValue());
		cfg.setKeyVariable(keyVariableTxt.getText());
	}

}
