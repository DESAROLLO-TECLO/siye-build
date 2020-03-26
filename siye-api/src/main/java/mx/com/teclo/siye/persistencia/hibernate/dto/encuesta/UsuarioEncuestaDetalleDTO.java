/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;
	
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

/**	
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE002D_EE_ODS_ENCUESTA", catalog = "")
public class UsuarioEncuestaDetalleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ODS_ENCUESTA", nullable = false)
    private Long idUsuarioEncuesta;
    @JoinColumn(name = "ID_ORDEN_SERVICIO", referencedColumnName = "ID_ORDEN_SERVICIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenServicioDTO ordenServicio;
    @Basic(optional = false)
    @Column(name = "NU_INTENTOS", nullable = false)
    private Integer nuIntentos;
    @Basic(optional = false)
    @Column(name = "ST_APLICA_ENCUESTA", nullable = false)
    private Boolean stAplicaEncuesta;
    @Column(name = "ST_ACTIVO")
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
    @JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EncuestaDetalleDTO encuesta;
    

    public OrdenServicioDTO getUsuario() {
		return ordenServicio;
	}

	public void setUsuario(OrdenServicioDTO usuario) {
		this.ordenServicio = ordenServicio;
	}

	public Integer getNuIntentos() {
		return nuIntentos;
	}

	public void setNuIntentos(Integer nuIntentos) {
		this.nuIntentos = nuIntentos;
	}

	public UsuarioEncuestaDetalleDTO() {
    }

    public UsuarioEncuestaDetalleDTO(Long idUsuarioEncuesta) {
        this.idUsuarioEncuesta = idUsuarioEncuesta;
    }

    public UsuarioEncuestaDetalleDTO(Long idUsuarioEncuesta, Integer nuIntentos, Boolean stAplicaEncuesta, Long idUsrCreacion, Date fhCreacion, Long idUsrModifica, Date fhModificacion) {
        this.idUsuarioEncuesta = idUsuarioEncuesta;
        this.nuIntentos = nuIntentos;
        this.stAplicaEncuesta = stAplicaEncuesta;
        this.idUsrCreacion = idUsrCreacion;
        this.fhCreacion = fhCreacion;
        this.idUsrModifica = idUsrModifica;
        this.fhModificacion = fhModificacion;
    }

    public Long getIdUsuarioEncuesta() {
        return idUsuarioEncuesta;
    }

    public void setIdUsuarioEncuesta(Long idUsuarioEncuesta) {
        this.idUsuarioEncuesta = idUsuarioEncuesta;
    }

    public Integer getNuIntegerentos() {
        return nuIntentos;
    }

    public void setNuIntegerentos(Integer nuIntegerentos) {
        this.nuIntentos = nuIntegerentos;
    }

    public Boolean getStAplicaEncuesta() {
        return stAplicaEncuesta;
    }

    public void setStAplicaEncuesta(Boolean stAplicaEncuesta) {
        this.stAplicaEncuesta = stAplicaEncuesta;
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


	public EncuestaDetalleDTO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestaDetalleDTO encuesta) {
		this.encuesta = encuesta;
	}
}
