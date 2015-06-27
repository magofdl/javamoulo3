package com.negocio;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES -Symmetric Cryptographic class that helps on encrypting and decrypting
 * string/bytes
 * 
 * 
 * CLASIFICACION: PROGRAMA SENSIBLE
 * 
 * @version 1.0 $ $Date: 2014/03/16
 * @author Aravind Viswanathan
 * 
 */
public class AESManagerExternal {
	/**
	 * Encrypts/decrypts a sample message using Symmetric encryption .
	 * 
	 * @param args
	 *            not used
	 * @throws Exception
	 *             if the algorithm, key, iv or any other parameter is invalid.
	 */

	protected int keySize;
	protected String algorithmName = null;
	protected String algorithmParams = null;
	protected String ivString = null;
	protected String keyString = null;
	protected Cipher cipher = null;
	protected IvParameterSpec ivspec = null;
	protected SecretKeySpec skeySpec = null;

	/**
	 * Default constructor without any arguments and initialises the parameters
	 * to default values
	 */

	public AESManagerExternal() {

		// Initialise with default parameters

		algorithmParams = InutilPropertiesManager
				.getPropiedad("DEFAULT_ALGORITHM_PARAMS");
		algorithmName = InutilPropertiesManager
				.getPropiedad("DEFAULT_ALGORITHM");

	}

	/**
	 * Constructor that initialises algorithmic parameters as provided in the
	 * arguments
	 * 
	 * @param algoParams
	 *            Algorithm parameters
	 * @param algoName
	 *            Name of the algorithm
	 */
	public AESManagerExternal(String algoName, String algoParams) {

		algorithmParams = algoParams;
		algorithmName = algoName;

	}

	/**
	 * Initialises the cipher object with default parameters
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws Exception
	 */
	public void initialiseCipher() throws NoSuchAlgorithmException,
			NoSuchPaddingException, Exception {
		try {
			keyString = InutilPropertiesManager.getPropiedad("AES_SECRET_KEY");
			ivString = InutilPropertiesManager.getPropiedad("AES_IV_STRING");
			ivspec = new IvParameterSpec(Base64.decodeBase64(ivString));
			skeySpec = new SecretKeySpec(Base64.decodeBase64(keyString),
					algorithmName);

			cipher = Cipher.getInstance(algorithmParams);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();

			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Initialises the cipher object with parameters that are passed on as
	 * arguments
	 * 
	 * @param keyStringParam
	 * @param ivStringParam
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws Exception
	 */
	public void initialiseCipher(String keyStringParam, String ivStringParam)
			throws NoSuchAlgorithmException, NoSuchPaddingException, Exception {
		try {

			ivspec = new IvParameterSpec(Base64.decodeBase64(ivStringParam));
			skeySpec = new SecretKeySpec(Base64.decodeBase64(keyStringParam),
					algorithmName);
			cipher = Cipher.getInstance(algorithmParams);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw e;
		}

		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Encrypts the given byte array using cipher object
	 * 
	 * @param byteToEncrypt
	 * @return Encrypted bytearray
	 * @throws GeneralSecurityException
	 * @throws BadPaddingException
	 * @throws Exception
	 */

	public byte[] encryptBytes(byte[] byteToEncrypt)
			throws GeneralSecurityException, BadPaddingException, Exception {
		byte[] encryptedBytes = null;
		try {

			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
			// encrypt the message
			encryptedBytes = cipher.doFinal(byteToEncrypt);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw e;

		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw e;

		}

		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return encryptedBytes;

	}

	/**
	 * Encrypts a given String and returns back the cipher string
	 * 
	 * @param stringToEncrypt
	 * @return encrypted string
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 * @throws Exception
	 */
	public String encryptText(String stringToEncrypt)
			throws UnsupportedEncodingException, GeneralSecurityException,
			Exception {
		byte[] encryptedBytes = null;
		try {
			encryptedBytes = this.encryptBytes(stringToEncrypt
					.getBytes("utf-8"));
		}

		catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return Base64.encodeBase64String(encryptedBytes);
	}

	/**
	 * Decrypts a given bytearray and returns back result as bytearray
	 * 
	 * @param byteToDecrypt
	 * @return decrypted byte array
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws Exception
	 */
	public byte[] decryptBytes(byte[] byteToDecrypt)
			throws InvalidKeyException, InvalidAlgorithmParameterException,
			Exception {
		try {

			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
			// encrypt the message
			return cipher.doFinal(byteToDecrypt);
		}

		catch (InvalidKeyException e) {
			e.printStackTrace();
			throw e;

		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Decrypts the cipher text and return back plaintext
	 * 
	 * @param stringToDecrypt
	 * @return decrypted plain text
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws Exception
	 */

	public String decryptText(String stringToDecrypt)
			throws InvalidKeyException, InvalidAlgorithmParameterException,
			Exception {
		try {
			byte[] decryptedBytes = this.decryptBytes(Base64
					.decodeBase64(stringToDecrypt));
			return new String(decryptedBytes, "utf-8");
		}

		catch (InvalidKeyException e) {
			e.printStackTrace();
			throw e;

		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}