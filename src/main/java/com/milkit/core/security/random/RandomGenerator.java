package com.milkit.core.security.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomGenerator {

	private static SecureRandom rand = null;

	private static void init(String algorithm) throws NoSuchAlgorithmException {
		rand = SecureRandom.getInstance(algorithm);
		rand.setSeed(System.currentTimeMillis());
		rand.setSeed(System.nanoTime());
		rand.setSeed(rand.hashCode());
		rand.setSeed(System.getenv().toString().getBytes());
		rand.setSeed(System.getProperties().toString().getBytes());
		rand.setSeed(rand.generateSeed(20));
	}

	public static void randomBytes(byte[] bytes) throws NoSuchAlgorithmException {
		init("SHA1PRNG");
		rand.nextBytes(bytes);
	}

	public static void randomBytes(String algorithm, byte[] bytes) throws NoSuchAlgorithmException {
		init(algorithm);
		rand.nextBytes(bytes);
	}
	
	public static int randomInt(int codeDigits) {
		return rand.nextInt(codeDigits);
	}

	public static SecureRandom getSecureRand() {
		return rand;
	}
}
