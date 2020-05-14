package mx.com.teclo.siye.persistencia.hibernate.dao.tipoExpediente;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente.TipoExpedienteDTO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;


@Repository
public class TipoExpedienteDAOImpl extends BaseDaoHibernate<TipoExpedienteDTO> implements TipoExpedienteDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoExpedienteVO> getTipoExpedientes() {
		StringBuilder consulta = new StringBuilder("SELECT ID_TIPO_EXPEDIENTE  AS idTipoExpediente,"
				+"CD_TIPO_EXPEDIENTE AS cdTIpoExpediente,"
				+"NB_TIPO_EXPEDIENTE AS nbTipoExpediente,"
				+"NU_ORDEN AS nuOrden" 
				+"  FROM TIE054C_IE_TIPO_EXPEDIENTE" 
				+"   WHERE ST_ACTIVO =1 ORDER BY NU_ORDEN ASC");	
		
		List<TipoExpedienteVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idTipoExpediente", LongType.INSTANCE)
				.addScalar("cdTipoExpediente", StringType.INSTANCE)
				.addScalar("nbTipoExpediente", StringType.INSTANCE)
				.addScalar("nuOrden", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(TipoExpedienteVO.class)).list();
		return respuesta;
	}

}
