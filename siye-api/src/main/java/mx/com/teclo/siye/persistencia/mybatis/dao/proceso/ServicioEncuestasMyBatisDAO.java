package mx.com.teclo.siye.persistencia.mybatis.dao.proceso;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;


@Mapper
public interface ServicioEncuestasMyBatisDAO {
	
	String INSERTAR_TRAMITE = "INSERT INTO TIE002D_EE_ODS_ENCUESTA ( "
			+ "ID_ODS_ENCUESTA,ID_ORDEN_SERVICIO,ID_ENCUESTA,NU_INTENTOS,ST_APLICA_ENCUESTA ,ST_ACTIVO,"
			+ "ID_USR_CREACION,FH_CREACION,ID_USR_MODIFICA,FH_MODIFICACION) VALUES ("
			+ "SQIE002D_EE_ODS_ENCUE.NEXTVAL,#{idSolicitud},#{idEncuesta},0,#{stAplicaEncuesta},1,1,SYSDATE,1,SYSDATE)";
	
	String INSERTAR_PROCESOS = "INSERT INTO TIE065D_ODS_PROCESOS ( "
			+ "ID_ODS_PROCESO,ID_ORDEN_SERVICIO,ID_PROCESO,FH_INI_PROCESO,FH_FIN_PROCESO ,ST_ACTIVO) "
			+ "VALUES ("
			+ "SQIE065D_ODS_PROCESOS.NEXTVAL,#{idSolicitud},#{idProceso},null,null,1)";
	
	
	@Insert(INSERTAR_TRAMITE)
	public Boolean insertarEncuestas(@Param("idSolicitud") Long idSolicitud, 
			@Param("idEncuesta") Long idEncuesta,@Param("stAplicaEncuesta") Boolean stAplicaEncuesta) throws PersistenceException;
	
	@Insert(INSERTAR_PROCESOS)
	public Boolean insertarprocesos(@Param("idSolicitud") Long idSolicitud, 
			@Param("idProceso") Long idProceso);

}
