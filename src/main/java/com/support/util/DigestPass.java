/*
 * @(#)DigestPass.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.support.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要类
 * 
 * @author liuan
 * @version 1.0,2008-8-25
 */
public class DigestPass {
	/**
	 * 
	 * @param alg
	 *            String 算法，通常为MD5,SHA
	 * @param plainByte
	 *            byte[] 明文的二进制
	 * @return byte[] 二进制的消息摘要
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] digest(String alg, byte[] plainByte)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(alg);
		md.update(plainByte);
		return md.digest();
	}

	public static byte[] digest(String alg, byte[] plainByte, byte[] key)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(alg);
		md.update(plainByte);
		return md.digest(key);
	}
}
