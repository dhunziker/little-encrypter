package littleencrypter.business;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {

	private String algorithm = "AES";

	private String cipher = "AES/ECB/PKCS5Padding";

	private String keyVariable = "ENC_KEY";

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public String getKeyVariable() {
		return keyVariable;
	}

	public void setKeyVariable(String keyVariable) {
		this.keyVariable = keyVariable;
	}

	@Override
	public String toString() {
		return "Config [algorithm=" + algorithm + ", cipher=" + cipher + ", keyVariable="
				+ keyVariable + "]";
	}

}
