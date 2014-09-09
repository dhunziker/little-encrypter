package littleencrypter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import littleencrypter.business.ConfigLoader;
import littleencrypter.menu.MenuView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airhacks.afterburner.injection.Injector;

public class App extends Application {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	@Override
	public void start(Stage stage) throws Exception {
		LOG.info(ConfigLoader.INSTANCE.getConfig().toString());

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
		ConfigLoader.INSTANCE.write();
		LOG.info("Settings have been saved");
		Injector.forgetAll();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
