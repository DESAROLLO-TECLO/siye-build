package mx.com.teclo.siye.persistencia.vo.async;

import java.io.Serializable;

public class ResponseDowloadFileVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3315181004983873829L;
	private byte[] arrayFile;
	private String nameFile;
	/**
	 * @return the arrayFile
	 */
	public byte[] getArrayFile() {
		return arrayFile;
	}
	/**
	 * @param arrayFile the arrayFile to set
	 */
	public void setArrayFile(byte[] arrayFile) {
		this.arrayFile = arrayFile;
	}
	/**
	 * @return the nameFile
	 */
	public String getNameFile() {
		return nameFile;
	}
	/**
	 * @param nameFile the nameFile to set
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
}
