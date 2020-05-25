package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.EstatusCalificacionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;

public class IntentoDetalleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4454547316451095312L;

	private Long idUsuEncuIntento;
	private Date fhInicio;
	private Date fhFin;
	private StEncuestaVO stEncuesta;
	private Integer nuCalificacion;
	private EstatusCalificacionVO stCalificacion;
	private Boolean stMostrar;
	private Integer nuPreguntas;
	private Integer nuPreguntasCorrectas;
	private Integer nuPreguntasIncorr;
	private Integer nuSeccionesTot;
	private Integer nuSeccionesRsp;
	private Integer nuPreguntasVacias;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	@JsonIgnore
	private OrdenEncuestaDTO usuarioEncuesta;
	private List<UsuarioEncuestaRespuestaVO> resumenRespuesta;
	private Integer nuMinConsumidos;
	
	//nuevos
	private String transportista;
	private Long  idVehiculoCnductor; 
	private String supervisor;
	private Long idGerenteSupervisor;
	private String tecnico;
	private Long idPersona;

	/**
	 * @return the idUsuEncuIntento
	 */
	public Long getIdUsuEncuIntento() {
		return idUsuEncuIntento;
	}

	/**
	 * @param idUsuEncuIntento the idUsuEncuIntento to set
	 */
	public void setIdUsuEncuIntento(Long idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

	/**
	 * @return the fhInicio
	 */
	public Date getFhInicio() {
		return fhInicio;
	}

	/**
	 * @param fhInicio the fhInicio to set
	 */
	public void setFhInicio(Date fhInicio) {
		this.fhInicio = fhInicio;
	}

	/**
	 * @return the fhFin
	 */
	public Date getFhFin() {
		return fhFin;
	}

	/**
	 * @param fhFin the fhFin to set
	 */
	public void setFhFin(Date fhFin) {
		this.fhFin = fhFin;
	}

	/**
	 * @return the stEncuesta
	 */
	public StEncuestaVO getStEncuesta() {
		return stEncuesta;
	}

	/**
	 * @param stEncuesta the stEncuesta to set
	 */
	public void setStEncuesta(StEncuestaVO stEncuesta) {
		this.stEncuesta = stEncuesta;
	}

	/**
	 * @return the nuCalificacion
	 */
	public Integer getNuCalificacion() {
		return nuCalificacion;
	}

	/**
	 * @param nuCalificacion the nuCalificacion to set
	 */
	public void setNuCalificacion(Integer nuCalificacion) {
		this.nuCalificacion = nuCalificacion;
	}

	/**
	 * @return the stCalificacion
	 */
	public EstatusCalificacionVO getStCalificacion() {
		return stCalificacion;
	}

	/**
	 * @param stCalificacion the stCalificacion to set
	 */
	public void setStCalificacion(EstatusCalificacionVO stCalificacion) {
		this.stCalificacion = stCalificacion;
	}

	/**
	 * @return the stMostrar
	 */
	public Boolean getStMostrar() {
		return stMostrar;
	}

	/**
	 * @param stMostrar the stMostrar to set
	 */
	public void setStMostrar(Boolean stMostrar) {
		this.stMostrar = stMostrar;
	}

	/**
	 * @return the nuPreguntas
	 */
	public Integer getNuPreguntas() {
		return nuPreguntas;
	}

	/**
	 * @param nuPreguntas the nuPreguntas to set
	 */
	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}

	/**
	 * @return the nuPreguntasCorrectas
	 */
	public Integer getNuPreguntasCorrectas() {
		return nuPreguntasCorrectas;
	}

	/**
	 * @param nuPreguntasCorrectas the nuPreguntasCorrectas to set
	 */
	public void setNuPreguntasCorrectas(Integer nuPreguntasCorrectas) {
		this.nuPreguntasCorrectas = nuPreguntasCorrectas;
	}

	/**
	 * @return the nuPreguntasIncorr
	 */
	public Integer getNuPreguntasIncorr() {
		return nuPreguntasIncorr;
	}

	/**
	 * @param nuPreguntasIncorr the nuPreguntasIncorr to set
	 */
	public void setNuPreguntasIncorr(Integer nuPreguntasIncorr) {
		this.nuPreguntasIncorr = nuPreguntasIncorr;
	}

	/**
	 * @return the nuSeccionesTot
	 */
	public Integer getNuSeccionesTot() {
		return nuSeccionesTot;
	}

	/**
	 * @param nuSeccionesTot the nuSeccionesTot to set
	 */
	public void setNuSeccionesTot(Integer nuSeccionesTot) {
		this.nuSeccionesTot = nuSeccionesTot;
	}

	/**
	 * @return the nuSeccionesRsp
	 */
	public Integer getNuSeccionesRsp() {
		return nuSeccionesRsp;
	}

	/**
	 * @param nuSeccionesRsp the nuSeccionesRsp to set
	 */
	public void setNuSeccionesRsp(Integer nuSeccionesRsp) {
		this.nuSeccionesRsp = nuSeccionesRsp;
	}

	/**
	 * @return the nuPreguntasVacias
	 */
	public Integer getNuPreguntasVacias() {
		return nuPreguntasVacias;
	}

	/**
	 * @param nuPreguntasVacias the nuPreguntasVacias to set
	 */
	public void setNuPreguntasVacias(Integer nuPreguntasVacias) {
		this.nuPreguntasVacias = nuPreguntasVacias;
	}

	/**
	 * @return the stActivo
	 */
	public Boolean getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}

	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}

	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	/**
	 * @return the usuarioEncuesta
	 */
	public OrdenEncuestaDTO getUsuarioEncuesta() {
		return usuarioEncuesta;
	}

	/**
	 * @param usuarioEncuesta the usuarioEncuesta to set
	 */
	public void setUsuarioEncuesta(OrdenEncuestaDTO usuarioEncuesta) {
		this.usuarioEncuesta = usuarioEncuesta;
	}

	/**
	 * @return the resumenRespuesta
	 */
	public List<UsuarioEncuestaRespuestaVO> getResumenRespuesta() {
		return resumenRespuesta;
	}

	/**
	 * @param resumenRespuesta the resumenRespuesta to set
	 */
	public void setResumenRespuesta(List<UsuarioEncuestaRespuestaVO> resumenRespuesta) {
		this.resumenRespuesta = resumenRespuesta;
	}

	/**
	 * @return the nuMinConsumidos
	 */
	public Integer getNuMinConsumidos() {
		return nuMinConsumidos;
	}

	/**
	 * @param nuMinConsumidos the nuMinConsumidos to set
	 */
	public void setNuMinConsumidos(Integer nuMinConsumidos) {
		this.nuMinConsumidos = nuMinConsumidos;
	}

	public String getTransportista() {
		return transportista;
	}

	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public Long getIdVehiculoCnductor() {
		return idVehiculoCnductor;
	}

	public void setIdVehiculoCnductor(Long idVehiculoCnductor) {
		this.idVehiculoCnductor = idVehiculoCnductor;
	}

	public Long getIdGerenteSupervisor() {
		return idGerenteSupervisor;
	}

	public void setIdGerenteSupervisor(Long idGerenteSupervisor) {
		this.idGerenteSupervisor = idGerenteSupervisor;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	

}
