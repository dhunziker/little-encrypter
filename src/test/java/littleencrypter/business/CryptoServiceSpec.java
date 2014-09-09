package littleencrypter.business;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.generators.Generator.asciiStrings;
import static org.hamcrest.Matchers.not;
import littleencrypter.AppUtils;
import littleencrypter.business.CryptoService;
import littleencrypter.business.CryptoService.Result;

import org.junit.runner.RunWith;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

@RunWith(JunitSuiteRunner.class)
public class CryptoServiceSpec {{

	CryptoService cryptoService = new CryptoService();

	describe("a crypto service", it -> {

		it.should("generate a key", expect -> {
			expect.that(cryptoService.generateKeyString()).isNotNull();
		});

		it.should("generate unique keys only", expect -> {
			String key1 = cryptoService.generateKeyString();
			String key2 = cryptoService.generateKeyString();
			expect.that(key1).is(not(equals(key2)));
		});

		it.requires(5)
			.example(asciiStrings())
			.toShow("decrypting the result of encrypt returns the original input", (expect, str) -> {
				byte[] key = cryptoService.generateKey();
				Result result = cryptoService.encrypt(key, AppUtils.getBytes(str));
				expect.that(str).isEqualTo(new String(cryptoService.decrypt(key,
						result.getIv(), result.getCipherText())));
		});	
	});

}}
