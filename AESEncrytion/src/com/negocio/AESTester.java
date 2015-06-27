package com.negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Aplicacion Stand Alone para AES encryotion/decryption y Hashing
 * @author druales
 *
 */
public class AESTester {
	public static void main(String[] args) throws Exception {
		try {

			System.out
					.println("##########################################################################################################");
			System.out
					.println("###                                    AES ENCRYPTION TESTER                                      ########");
			System.out
					.println("##########################################################################################################");

			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			System.out
					.println("INGRESE OPCION 1 for Hashing , 2  Encryption:");
			int choice = Integer.parseInt(bufferRead.readLine());

			if (choice == 1) {
				
				//SHA Hashing sample
				
				
				SHAHelper shaHeper = new SHAHelper();
				System.out
						.println("#############################################################################################################################");
				System.out
						.println("                                                           HASHING                                                            ");
				System.out
						.println("#############################################################################################################################");
				System.out.println("INGRESE CADENA PARA CALCULAR hash  :");
				String messagetoHash = bufferRead.readLine();
                
				System.out.println("El Hashed resultante es "
						+ shaHeper.hashString(messagetoHash));

			}

			else if (choice == 2) {
				
				// AES Encryption sample
				System.out.println("INGRESE TEXTO A ENCRIPTAR  :" + "\n");
				String message = bufferRead.readLine();

				System.out.println("\n\n Texto Plano: " + message + "\n");
				
				// First Step : Create AESManagerExternal with default constructor as shown below

				AESManagerExternal aesManager = new AESManagerExternal();
				
				// Second  Step : call default initialiseCipher method that initialises the cipher objects as per the config file
				
				aesManager.initialiseCipher();
				
				// call encryptText to get the encrypted text
				String encryptedText = aesManager.encryptText(message);
				System.out.println("\n\nTEXTO ENCRIPTADO \n\n"
						+ encryptedText);
				
				//call decryptText to get the decrypted text
				System.out.println("\n\nTEXTO DESENCRIPTADO \n\n"
						+ aesManager.decryptText(encryptedText));

			} else
				System.out.println("OPCION INVALIDA..........");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
