package cl.buildersoft.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.exception.BSSystemException;

public class BSSecurity {

	/**
	 * public String cript(String s) { return md5fromClass(s); } private String
	 * md5fromClass(String s) throws BSProgrammerException { MD5 x = new MD5(s);
	 * String out = x.compute(); x = null; return out; }
	 */
	public String encript3des(String key, String s) {
		throw new BSProgrammerException("0101", "No se ha implementado el método encript3des()");
	}

	public String decript3des(String key, String s) {
		throw new BSProgrammerException("0102", "No se ha implementado el método decript3des()");
	}

	public String md5(String str) {

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new BSSystemException("0200", e.getMessage());
		}

		// convert input String to a char[]
		// convert that char[] to byte[]
		// get the md5 digest as byte[]
		// bit-wise AND that byte[] with 0xff
		// prepend "0" to the output StringBuffer to make sure that we don't end
		// up with
		// something like "e21ff" instead of "e201ff"

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
