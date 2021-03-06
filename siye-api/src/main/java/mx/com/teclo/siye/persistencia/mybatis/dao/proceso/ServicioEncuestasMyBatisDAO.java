package mx.com.teclo.siye.persistencia.mybatis.dao.proceso;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;


@Mapper
public interface ServicioEncuestasMyBatisDAO {
	
	String INSERTAR_TRAMITE = "INSERT INTO TIE002D_EE_ODS_ENCUESTA ( "
			+ "ID_ODS_ENCUESTA,ID_ORDEN_SERVICIO,ID_ENCUESTA,NU_INTENTOS,ST_APLICA_ENCUESTA ,ST_ACTIVO,"
			+ "ID_USR_CREACION,FH_CREACION,ID_USR_MODIFICA,FH_MODIFICACION) VALUES ("
			+ "SQIE002D_EE_ODS_ENCUE.NEXTVAL,#{idSolicitud},#{idEncuesta},0,#{stAplicaEncuesta},1,1,SYSDATE,1,SYSDATE)";
	
	String INSERTAR_PROCESOS = "INSERT INTO TIE065D_ODS_PROCESOS ( "
			+ "ID_ODS_PROCESO,ID_ORDEN_SERVICIO,ID_PROCESO,FH_INI_PROCESO,FH_FIN_PROCESO ,ST_ACTIVO,ID_ST_SEGUIMIENTO) "
			+ "VALUES ("
			+ "SQIE065D_ODS_PROCESOS.NEXTVAL,#{idSolicitud},#{idProceso},null,null,1,21)";
	
	String INICIAR_PROCESO = "UPDATE TIE065D_ODS_PROCESOS SET FH_INI_PROCESO=#{fecha},ID_ST_SEGUIMIENTO=22 "
			+ "WHERE ID_ORDEN_SERVICIO=#{idSolicitud} AND ID_PROCESO=#{idProceso}";
	
	String FINALIZAR_PROCESO = "UPDATE TIE065D_ODS_PROCESOS SET FH_FIN_PROCESO=#{fecha},ID_ST_SEGUIMIENTO=23 "
			+ "WHERE ID_ORDEN_SERVICIO=#{idSolicitud} AND ID_PROCESO=#{idProceso}";
	
	String CONSULTA_FECHAINICIAL = "SELECT FH_INI_PROCESO FROM TIE065D_ODS_PROCESOS "
			+"WHERE ID_PROCESO=#{idProceso} AND ID_ORDEN_SERVICIO=#{idSolicitud}";
	
	String CONSULTA_FECHAFINAL = "SELECT FH_FIN_PROCESO FROM TIE065D_ODS_PROCESOS "
			+"WHERE ID_PROCESO=#{idProceso} AND ID_ORDEN_SERVICIO=#{idSolicitud}";
	
	String CONSULTA_FECHAINICIALENCUESTA = "SELECT FH_INICIO FROM TIE006D_EE_USU_ENCU_INTEN "
			+"WHERE ID_ODS_ENCUESTA=#{idOdsEncuesta}";
	
	String CONSULTA_FECHAFINALENCUESTA = "SELECT FH_FIN FROM TIE006D_EE_USU_ENCU_INTEN "
			+"WHERE ID_ODS_ENCUESTA=#{idOdsEncuesta}";
	
	
	
	
	
	
	
	
	@Insert(INSERTAR_TRAMITE)
	public Boolean insertarEncuestas(@Param("idSolicitud") Long idSolicitud, 
			@Param("idEncuesta") Long idEncuesta,@Param("stAplicaEncuesta") Boolean stAplicaEncuesta) throws PersistenceException;
	
	@Insert(INSERTAR_PROCESOS)
	public Boolean insertarprocesos(@Param("idSolicitud") Long idSolicitud, 
			@Param("idProceso") Long idProceso);
	
	@Update(INICIAR_PROCESO)
	public Boolean iniciarProceso(@Param("idSolicitud") Long idSolicitud, 
			@Param("idProceso") Long idProceso,@Param("fecha") Date fecha);
	
	@Update(FINALIZAR_PROCESO)
	public Boolean finalizarProceso(@Param("idSolicitud") Long idSolicitud, 
			@Param("idProceso") Long idProceso,@Param("fecha") Date fecha);
	
	@Select(CONSULTA_FECHAINICIAL)
	public Date getFechaInicioProceso(@Param("idProceso") Long idProceso,
			@Param("idSolicitud") Long idSolicitud);
	
	@Select(CONSULTA_FECHAFINAL)
	public Date getFechaFinProceso(@Param("idProceso") Long idProceso,
			@Param("idSolicitud") Long idSolicitud);
	
	
	@Select(CONSULTA_FECHAINICIALENCUESTA)
	public Date getFechaInicioEncuesta(@Param("idOdsEncuesta") Long idOdsEncuesta);
	
	@Select(CONSULTA_FECHAFINALENCUESTA)
	public Date getFechaFinEncuesta(@Param("idOdsEncuesta") Long idOdsEncuesta);

}
