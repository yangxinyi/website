package com.support.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class MD5Utils {

	private static final String CHARSET = "UTF8";

	private static final String ALG = "MD5";

	private static final String KEY = "sdfsdfercvbcb";

	public static String encrypt(String str) {
		byte[] digest = null;
		try {
			digest = DigestPass.digest(ALG, str.getBytes(CHARSET));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode(digest);
	}

	public static String encryptWithKey(String str) {
		byte[] digest = null;
		try {
			digest = DigestPass.digest(ALG, str.getBytes(CHARSET), KEY
					.getBytes(CHARSET));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode(digest);
	}

	private static String encode(byte[] plain) {
		return new String(Base64.encodeBase64(plain));
	}

}
