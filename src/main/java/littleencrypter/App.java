package littleencrypter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airhacks.afterburner.injection.Injector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import littleencrypter.business.Config;
import littleencrypter.business.ConfigLoader;
import littleencrypter.business.CryptoService;
import littleencrypter.menu.MenuView;

public class App extends Application {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	private ConfigLoader configLoader;

	@Override
	public void start(Stage stage) throws Exception {
		Injector.setLogger(LOG::debug);

		configLoader = new ConfigLoader();
		Config config = configLoader.getConfig();

		LOG.info(config.toString());
		Injector.setModelOrService(Config.class, config);
		Injector.instantiateModelOrService(CryptoService.class);

		setUserAgentStylesheet(STYLESHEET_MODENA);

		MenuView menuView = new MenuView();
		Scene scene = new Scene(menuView.getView());
		String css = getClass().getResource("app.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.setTitle("Little Encrypter");
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		configLoader.write();
		LOG.info("Settings have been saved");
		Injector.forgetAll();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
