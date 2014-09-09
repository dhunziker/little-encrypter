package littleencrypter;

import static com.insightfullogic.lambdabehave.Suite.describe;

import org.junit.runner.RunWith;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

@RunWith(JunitSuiteRunner.class)
public class AppUtilsSpec {{

	describe("an app util", it -> {

		it.should("not require an IV", expect -> {
			expect.that(AppUtils.requiresIv("AES/ECB/PKCS5Padding")).is(false);
		});

		it.should("require an IV", expect -> {
			expect.that(AppUtils.requiresIv("AES/CBC/PKCS5Padding")).is(true);
		});

	});

}}
