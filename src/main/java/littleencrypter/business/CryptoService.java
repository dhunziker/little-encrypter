package littleencrypter.business;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import littleencrypter.AppUtils;
import littleencrypter.Constants;

public class CryptoService {

	@Inject
	private Config config;

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	public Result encrypt(byte[] key, byte[] plainText) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key, config.getAlgorithm());
			Cipher cipher = Cipher.getInstance(config.getCipher());
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, SECURE_RANDOM);
			return new Result(cipher.getIV(), cipher.doFinal(plainText));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decrypt(byte[] key, String iv, String cipherText) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key, config.getAlgorithm());
			Cipher cipher = Cipher.getInstance(config.getCipher());
			IvParameterSpec ivParam = null;
			if (!AppUtils.nullOrEmpty(iv)) {
				ivParam = new IvParameterSpec(Base64.getDecoder().decode(iv));
			}
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParam);
			return cipher.doFinal(Base64.getDecoder().decode(cipherText));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException
				| InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] generateKey() {
		try {
			KeyGenerator gen = KeyGenerator.getInstance(config.getAlgorithm());
			gen.init(Constants.KEY_LENGTH);
			return gen.generateKey().getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(String.format("%s is not a valid algorithm",
					config.getAlgorithm()), e);
		}
	}

	public String generateKeyString() {
		return Base64.getEncoder().encodeToString(generateKey());
	}

	public static class Result {

		private final String iv;

		private final String cipherText;

		public Result(byte[] iv, byte[] cipherText) {
			this.iv = iv != null ? Base64.getEncoder().encodeToString(iv) : "";
			this.cipherText = Base64.getEncoder().encodeToString(cipherText);
		}

		public String getIv() {
			return iv;
		}

		public String getCipherText() {
			return cipherText;
		}

	}

}
