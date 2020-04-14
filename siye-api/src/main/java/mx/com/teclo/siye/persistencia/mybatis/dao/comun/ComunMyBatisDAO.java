package mx.com.teclo.siye.persistencia.mybatis.dao.comun;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorCompVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaCompVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComunMyBatisDAO {
	
	String CONS_PERSONALIZADA = "${consPersonalizada}";
	
	@Select(CONS_PERSONALIZADA)
	public List<ConductorCompVO> consConductoresPersonalizada(
		@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public List<PersonaCompVO> consInstaladoresPersonalizada(
		@Param("consPersonalizada") String consPersonalizada);
}
