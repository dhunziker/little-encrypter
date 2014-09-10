package littleencrypter.menu;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.decrypt.DecryptView;
import littleencrypter.encrypt.EncryptView;
import littleencrypter.settings.SettingsView;

import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;

public class MenuPresenter {

	@Inject
	private EncryptView encryptView;

	@Inject
	private DecryptView decryptView;

	@Inject
	private SettingsView settingsView;

	// I18N messages
	@FXML
	private ResourceBundle resources;

	@FXML
	private ToggleGroup viewGrp;

	@FXML
	private RadioMenuItem encryptItem;

	@FXML
	private RadioMenuItem decryptItem;

	@FXML
	private RadioMenuItem settingsItem;

	@FXML
	private Button encryptBtn;

	@FXML
	private Button decryptBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private AnchorPane contentPane;

	public void initialize() {
		showEncrypt();
	}

	@FXML
	public void showEncrypt() {
		AppUtils.setView(contentPane, encryptView);
		toggleMenuItems(encryptItem);
	}

	@FXML
	public void showDecrypt() {
		AppUtils.setView(contentPane, decryptView);
		toggleMenuItems(decryptItem);
	}

	@FXML
	public void showSettings() {
		AppUtils.setView(contentPane, settingsView);
		toggleMenuItems(settingsItem);
	}

	@FXML
	public void showAbout() {
		Dialogs.create().title(resources.getString("dialog.about.title"))
				.message(resources.getString("dialog.about.message")).style(DialogStyle.NATIVE)
				.showInformation();
	}

	private void toggleMenuItems(RadioMenuItem activeItem) {
		activeItem.setSelected(true);
	}

	@FXML
	public void close() {
		Platform.exit();
	}

}
