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

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE007D_EE_OPCIONES", catalog = "")
public class OpcionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OPCION", nullable = false)
    private Long idOpcion;
    @Basic(optional = false)
    @Column(name = "TX_OPCION", nullable = false, length = 1000)
    private String txOpcion;
    @Basic(optional = false)
    @Column(name = "ST_CORRECTO", nullable = false)
    private Integer stCorrecto;
    @Basic(optional = false)
    @Column(name = "CD_OPCION", nullable = false, length = 15)
    private String cdOpcion;
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
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PreguntasDTO idPregunta;

    public OpcionesDTO() {
    }

    public OpcionesDTO(Long idOpcion) {
        this.idOpcion = idOpcion;
    }

    public OpcionesDTO(Long idOpcion, String txOpcion, Integer stCorrecto, String cdOpcion, Integer stActivo, Long idUsrCreacion, Date fhCreacion, Long idUsrModifica, Date fhModificacion) {
        this.idOpcion = idOpcion;
        this.txOpcion = txOpcion;
        this.stCorrecto = stCorrecto;
        this.cdOpcion = cdOpcion;
        this.stActivo = stActivo;
        this.idUsrCreacion = idUsrCreacion;
        this.fhCreacion = fhCreacion;
        this.idUsrModifica = idUsrModifica;
        this.fhModificacion = fhModificacion;
    }

    public Long getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Long idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getTxOpcion() {
        return txOpcion;
    }

    public void setTxOpcion(String txOpcion) {
        this.txOpcion = txOpcion;
    }

    public Integer getStCorrecto() {
        return stCorrecto;
    }

    public void setStCorrecto(Integer stCorrecto) {
        this.stCorrecto = stCorrecto;
    }

    public String getCdOpcion() {
        return cdOpcion;
    }

    public void setCdOpcion(String cdOpcion) {
        this.cdOpcion = cdOpcion;
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

    public PreguntasDTO getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(PreguntasDTO idPregunta) {
        this.idPregunta = idPregunta;
    }    
}
