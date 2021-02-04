package client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdGeneratorMD5 implements IdGenerator {

	@Override
	public String generator(String name) throws RuntimeException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(name.getBytes());
			byte[] md5 = md.digest();

			BigInteger numMd5 = new BigInteger(1, md5);
			return String.format("%022x", numMd5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
