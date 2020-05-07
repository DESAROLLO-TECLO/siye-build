package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;



import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.IeStUsuEncuIntenDTO;


public interface IeStUsuEncuIntenDAO extends BaseDao<IeStUsuEncuIntenDTO> {
	

	public IeStUsuEncuIntenDTO getInfoByUsuEncInt(Long usuEncInte);

}
