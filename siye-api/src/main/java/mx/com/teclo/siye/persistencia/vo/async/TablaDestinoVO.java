package mx.com.teclo.siye.persistencia.vo.async;

public class TablaDestinoVO {
	private String nbTabla;
	private Boolean isReadOnly;
	private Boolean isTblBase;
	private Integer nuOrdenInsercion;
	private Boolean isTblBaseFinal;

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

	public Integer getNuOrdenInsercion() {
		return nuOrdenInsercion;
	}

	public void setNuOrdenInsercion(Integer nuOrdenInsercion) {
		this.nuOrdenInsercion = nuOrdenInsercion;
	}

	public Boolean getIsTblBaseFinal() {
		return isTblBaseFinal;
	}

	public void setIsTblBaseFinal(Boolean isTblBaseFinal) {
		this.isTblBaseFinal = isTblBaseFinal;
	}
	
	
	

}
