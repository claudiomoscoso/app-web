package cl.buildersoft.lib.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cl.buildersoft.lib.exception.BSProgrammerException;
import cl.buildersoft.lib.exception.BSSystemException;

public class BSSecurity {
	private static final String UNICODE_FORMAT = "UTF8";
	private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec myKeySpec;
	private SecretKeyFactory mySecretKeyFactory;
	private Cipher cipher;
	private byte[] keyAsBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	private SecretKey key;

	public BSSecurity() {
		myEncryptionKey = "BuilderSoftEncryptionKey_";
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;

		try {
			keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
			myKeySpec = new DESedeKeySpec(keyAsBytes);
			mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
			cipher = Cipher.getInstance(myEncryptionScheme);
			key = mySecretKeyFactory.generateSecret(myKeySpec);
		} catch (Exception e) {
			throw new BSProgrammerException(e);
		}
	}

	public String encript3des(String clearString) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = clearString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			BASE64Encoder base64encoder = new BASE64Encoder();
			encryptedString = base64encoder.encode(encryptedText);
		} catch (Exception e) {
			throw new BSProgrammerException(e);
		}
		return encryptedString;
	}

	public String decript3des(String encryptedString) {
		String clearString = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			clearString = bytes2String(plainText);
		} catch (Exception e) {
			throw new BSProgrammerException(e);
		}
		return clearString;
	}

	private String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (byte b : bytes) {
			stringBuffer.append((char) b);
		}
		return stringBuffer.toString();
	}

	public String md5(String str) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new BSSystemException(e);
		}
		char[] charArray = str.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		int val = 0;
		for (int i = 0; i < md5Bytes.length; i++) {
			val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
}
