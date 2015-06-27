package com.negocio;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * SHA-Hasher class that helps on generating message digest
 * 
 * 
 * CLASIFICACION: PROGRAMA SENSIBLE
 * 
 * @version 1.0 $ $Date: 2014/03/16
 * @author Aravind Viswanathan
 * 
 */
public class SHAHelper

{
	protected String hashingAlgorithm = null;

	/**
	 * Constructor that assigns default values
	 */
	public SHAHelper() {

		hashingAlgorithm = InutilPropertiesManager
				.getPropiedad("DEFAULT_HASHING_ALGORITHM");

	}

	/**
	 * Constructor that assigns hashing parameters using constructor argument
	 * values
	 * 
	 * @param hashAlgorithm
	 */

	public SHAHelper(String hashAlgorithm) {

		hashingAlgorithm = hashAlgorithm;

	}

	/**
	 * Generates message digest for a given String message
	 * 
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException
	 *             \
	 * @throws GeneralSecurityException
	 * @throws Exception
	 */
	public String hashString(String message) throws NoSuchAlgorithmException,
			GeneralSecurityException, Exception {
		try {
			MessageDigest messagedDigest = MessageDigest
					.getInstance(hashingAlgorithm);

			messagedDigest.update(message.getBytes("utf-8"));

			byte byteData[] = messagedDigest.digest();

			return Base64.encodeBase64String(byteData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// nothing to clear as of now.
		}
	}

}