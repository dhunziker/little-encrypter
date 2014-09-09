package littleencrypter.business;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigLoader {

	public static final ConfigLoader INSTANCE = new ConfigLoader();

	private static final String FILENAME = "config.xml";

	private Config config;

	private ConfigLoader() {
		load();
	}

	public Config getConfig() {
		return config;
	}

	private void load() {
		try {
			File f = Paths.get(FILENAME).toFile();
			if (f.exists()) {
				JAXBContext ctx = JAXBContext.newInstance(Config.class);
				Unmarshaller u = ctx.createUnmarshaller();
				config = (Config) u.unmarshal(Paths.get(FILENAME).toFile());
			} else {
				config = new Config();
			}
		} catch (JAXBException e) {
			throw new RuntimeException("Failed to load configuration", e);
		}
	}

	public void write() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Config.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(config, Paths.get(FILENAME).toFile());
		} catch (JAXBException e) {
			throw new RuntimeException("Failed to write configuration", e);
		}
	}

}
