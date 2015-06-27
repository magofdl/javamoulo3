//SIB_CAMBIO_MONY_1 (inicio)
package com.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class InutilPropertiesManager {
	/**
	 * 
	 */
	private static String INUTIL_FILE_PATH = "C:\\AES_EXT\\tcscryptoext.conf" ;
	/**
	 * 
	 */
	private static Properties propSet = null;

	/**
	 * 
	 * @param nombrePropiedad
	 * @return
	 */
	public static String getPropiedad(String nombrePropiedad) {
		
		try {
			if (propSet == null) {
				loadPropSet();
			}
			
			return propSet.get(nombrePropiedad).toString();
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	public static void setINUTIL_FILE_PATH(String filePath) throws Exception {
		if (filePath == null || filePath.trim().length() == 0) {
			throw new Exception("INUTIL_FILE_PATH no cambiado a: "+filePath);
		}
		INUTIL_FILE_PATH = filePath;
		loadPropSet();
	}
	
	private static void loadPropSet() throws Exception {
		/**
		 * 
		 */
		File f = new File(INUTIL_FILE_PATH);
		if (!f.exists()) {
			throw new Exception("Archivo " + INUTIL_FILE_PATH + " no encontrado.");
		} else {
			InputStream is = new FileInputStream(INUTIL_FILE_PATH);
			propSet = new Properties();
			propSet.load(is);
		}
	}
}
//SIB_CAMBIO_MONY_1 (fin)