package mx.com.teclo.siye.persistencia.vo.async;

public class TablaDestinoVO {
	private String nbTabla;
	private Boolean isReadOnly;
	private Boolean isTblBase;

	public String getNbTabla() {
		return nbTabla;
	}

	public void setNbTabla(String nbTabla) {
		this.nbTabla = nbTabla;
	}

	public Boolean getIsReadOnly() {
		return isReadOnly;
	}

	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public Boolean getIsTblBase() {
		return isTblBase;
	}

	public void setIsTblBase(Boolean isTblBase) {
		this.isTblBase = isTblBase;
	}

}
