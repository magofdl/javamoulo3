package com.negocio;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * KeyGeneration class which serves as a standalone Java application that
 * generates key and IV based upon the commandline parameters passed
 * 
 * CLASIFICACION: PROGRAMA SENSIBLE
 * 
 * @version 1.0 $ $Date: 2014/03/16
 * @author Aravind Viswanathan
 * 
 */
public class KeyGenarator {
	public static void main(String[] args) {
		String algorithm = null;
		int keySize;
		BufferedReader bufferRead = null;
		try {
			System.out
					.println("##########################################################################################################");
			System.out
					.println("###                                                KEY GENERATOR                                  ########");
			System.out
					.println("##########################################################################################################");
			bufferRead = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter Algorithm :" + "\n");
			algorithm = bufferRead.readLine();
			System.out.println("Enter Key Size :");
			keySize = Integer.parseInt(bufferRead.readLine());

			KeyGenerator keygen = KeyGenerator.getInstance(algorithm);

			// To make key generation more complex ,securerandom salt is used
			keygen.init(keySize, new SecureRandom());

			byte[] key = keygen.generateKey().getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
			byte[] iv = new byte[16];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(iv);

			String keyString = Base64.encodeBase64String(key);
			String ivString = Base64.encodeBase64String(iv);
			System.out.println("Key String :" + keyString);
			System.out.println("IV         :" + ivString);
		} catch (IOException e) {
			System.out.println("Exception during processing user input");
			e.printStackTrace();

		} catch (GeneralSecurityException e) {
			System.out
					.println("A Security exception occured during key generation.");
			e.printStackTrace();

		}

		catch (Exception e) {
			System.out
					.println("An Unexpected exception occured. Details below");
			e.printStackTrace();

		} finally {
			try {
				bufferRead.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
