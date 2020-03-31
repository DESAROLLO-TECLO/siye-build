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
	
	@Insert(INSERTAR_TRAMITE)
	public Boolean insertarEncuestas(@Param("idSolicitud") Long idSolicitud, 
			@Param("idEncuesta") Long idEncuesta,@Param("stAplicaEncuesta") Boolean stAplicaEncuesta) throws PersistenceException;

}
