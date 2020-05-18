package mx.com.teclo.siye.persistencia.vo.reportes;

import java.io.ByteArrayInputStream;

public class ImagenesEvidenciaReporteVO {
	private String filename;
	private ByteArrayInputStream fileBase64toBlob;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public ByteArrayInputStream getFileBase64toBlob() {
		return fileBase64toBlob;
	}
	public void setFileBase64toBlob(ByteArrayInputStream fileBase64toBlob) {
		this.fileBase64toBlob = fileBase64toBlob;
	}
}
