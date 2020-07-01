package mx.com.teclo.siye.persistencia.mybatis.dao.ordenservicio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.RespuestaVO;

@Mapper
public interface OrdenServicioMyBatisDAO {
	
	String GET_ID_USU_ENCU_INTENTO = "sELECT TIE006.ID_USU_ENCU_INTENTO FROM TIE002D_EE_ODS_ENCUESTA TIE002 " 
								   + "INNER JOIN TIE006D_EE_USU_ENCU_INTEN TIE006 ON TIE006.ID_ODS_ENCUESTA = TIE002.ID_ODS_ENCUESTA " 
								   + "WHERE TIE002.ID_ORDEN_SERVICIO = #{idOrdenServicio} AND TIE002.ID_ENCUESTA = #{idEncuesta} "
								   + "AND TIE002.ST_ACTIVO = 1 AND TIE006.ST_ACTIVO = 1";
	
	String GET_RESPUESTAS = "select tie007.TX_OPCION as respuesta, tie003.TX_OBSERVACION as justificacion, tb_causa.NB_CAUSA as causa "
						  + "from tie003d_ee_usu_enc_resp tie003 " 
						  + "inner join tie007d_ee_opciones tie007 on tie007.ID_OPCION = tie003.ID_OPCION " 
						  + "left join (select tie063.ID_USU_ENCU_INTENTO, tie063.ID_ENCUESTA, tie063.ID_SECCION, "
						  + "tie063.ID_PREGUNTA, tie061.NB_CAUSA from tie063d_ie_resp_causa tie063 "
						  + "inner join tie061c_ie_causas tie061 on tie063.id_causa = tie061.id_causa " 
						  + "where tie063.st_activo = 1 and tie061.st_activo =1) tb_causa "
						  + "on tb_causa.id_usu_encu_intento = tie003.id_usu_encu_intento "
						  + "and tb_causa.ID_ENCUESTA = tie003.ID_ENCUESTA and tb_causa.ID_SECCION = tie003.ID_SECCION "
						  + "and tb_causa.ID_PREGUNTA = tie003.ID_PREGUNTA " 
						  + "where tie003.id_usu_encu_intento = #{idUsuEncuIntento} and tie003.ID_ENCUESTA = #{idEncuesta} "
						  + "and tie003.ID_SECCION = #{idSeccion} and tie003.ID_PREGUNTA = #{idPregunta} and tie003.st_activo = 1";
	
	@Select(GET_ID_USU_ENCU_INTENTO)
	public Long getIdOsEncuesta(
			@Param(value="idOrdenServicio") Long idOrdenServicio, 
			@Param(value="idEncuesta") Long idEncuesta);
	
	@Select(GET_RESPUESTAS)
	public List<RespuestaVO> getRespuestas (
			@Param(value="idUsuEncuIntento") Long idUsuEncuIntento, 
			@Param(value="idEncuesta") Long idEncuesta, 
			@Param(value="idSeccion") Long idSeccion, 
			@Param(value="idPregunta") Long idPregunta);
}
