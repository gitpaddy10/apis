package com.test.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AesGcm {

	public static void main(String[] args) {
		try {
			
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] iv = new byte[12];
			random.nextBytes(iv);
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			SecretKey secretKey = keyGenerator.generateKey();
			System.out.println("Secret Key algorithm: " + secretKey.getAlgorithm());
			System.out.println("Secret Key data: " + new String(secretKey.getEncoded()));

			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
			GCMParameterSpec paramSpec = new GCMParameterSpec(128, iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			byte[] input = "Hello World!".getBytes();
			byte[] cipherText = cipher.doFinal(input);
			System.out.println("plain length:" + input.length);
			System.out.println("cipher lenght:" + cipherText.length);
			System.out.println("Encrypted value:" + new String(cipherText));;
			
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			byte[] plainText = cipher.doFinal(cipherText);
			System.out.println("Value:" + new String(plainText));;
			



		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
