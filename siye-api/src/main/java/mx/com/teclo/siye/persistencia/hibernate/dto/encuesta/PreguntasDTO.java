/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoPreguntaDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE005D_EE_PREGUNTAS", catalog = "")
public class PreguntasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PREGUNTA", nullable = false)
    private Long idPregunta;
    
    @Basic(optional = false)
    @Column(name = "CD_PREGUNTA", nullable = false, length = 15)
    private String cdPregunta;
    
    @Basic(optional = false)
    @Column(name = "TX_PREGUNTA", nullable = false, length = 250)
    private String txPregunta;
    
    @Column(name = "NU_ORDEN")
    private Integer nuOrden;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false)
    private Integer stActivo;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION", nullable = false)
    private Long idUsrCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA", nullable = false)
    private Long idUsrModifica;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntasDTO", fetch = FetchType.LAZY)
    //private List<UsuaroEncuestaRespuestaDTO> usuaroEncuestaRespuestaDTOList;
    
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SeccionDTO idSeccion;
    
    @JoinColumn(name = "ID_TIPO_PREGUNTA", referencedColumnName = "ID_TP_PREGUNTA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPreguntaDTO idTipoPregunta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta", fetch = FetchType.LAZY)
    private List<OpcionesDTO> opciones;
    
    @Column(name = "NU_MAX_IMAGENES", nullable = false)
    private Long nuMaxImagenes;
	
    public PreguntasDTO() {
    }

    public PreguntasDTO(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public PreguntasDTO(Long idPregunta, String cdPregunta, String txPregunta, Integer stActivo, Long idUsrCreacion, Date fhCreacion, Long idUsrModifica, Date fhModificacion) {
        this.idPregunta = idPregunta;
        this.cdPregunta = cdPregunta;
        this.txPregunta = txPregunta;
        this.stActivo = stActivo;
        this.idUsrCreacion = idUsrCreacion;
        this.fhCreacion = fhCreacion;
        this.idUsrModifica = idUsrModifica;
        this.fhModificacion = fhModificacion;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getCdPregunta() {
        return cdPregunta;
    }

    public void setCdPregunta(String cdPregunta) {
        this.cdPregunta = cdPregunta;
    }

    public String getTxPregunta() {
        return txPregunta;
    }

    public void setTxPregunta(String txPregunta) {
        this.txPregunta = txPregunta;
    }

    public Integer getNuOrden() {
        return nuOrden;
    }

    public void setNuOrden(Integer nuOrden) {
        this.nuOrden = nuOrden;
    }

    public Integer getStActivo() {
        return stActivo;
    }

    public void setStActivo(Integer stActivo) {
        this.stActivo = stActivo;
    }

    public Long getIdUsrCreacion() {
        return idUsrCreacion;
    }

    public void setIdUsrCreacion(Long idUsrCreacion) {
        this.idUsrCreacion = idUsrCreacion;
    }

    public Date getFhCreacion() {
        return fhCreacion;
    }

    public void setFhCreacion(Date fhCreacion) {
        this.fhCreacion = fhCreacion;
    }

    public Long getIdUsrModifica() {
        return idUsrModifica;
    }

    public void setIdUsrModifica(Long idUsrModifica) {
        this.idUsrModifica = idUsrModifica;
    }

    public Date getFhModificacion() {
        return fhModificacion;
    }

    public void setFhModificacion(Date fhModificacion) {
        this.fhModificacion = fhModificacion;
    }

    public SeccionDTO getIdSección() {
        return idSeccion;
    }

    public void setIdSección(SeccionDTO idSección) {
        this.idSeccion = idSección;
    }

    public TipoPreguntaDTO getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(TipoPreguntaDTO idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

	public SeccionDTO getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(SeccionDTO idSeccion) {
		this.idSeccion = idSeccion;
	}

	public List<OpcionesDTO> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OpcionesDTO> opciones) {
		this.opciones = opciones;
	}

	/**
	 * @return the nuMaxImagenes
	 */
	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}

	/**
	 * @param nuMaxImagenes the nuMaxImagenes to set
	 */
	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}
}
