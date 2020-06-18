package mx.com.teclo.siye.negocio.service.clave;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PasswordDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.comun.PasswordDTO;
import mx.com.teclo.siye.persistencia.vo.clave.PasswordVO;

@Service
public class ClaveServiceImpl implements ClaveService{
	
	@Autowired
	PasswordDAO passwordDAO;
	
	@Transactional
	@Override
	public PasswordVO clave() {
		PasswordDTO passwordDTO = passwordDAO.getClaveActual();
		PasswordVO passwordVO = ResponseConverter.copiarPropiedadesFull(passwordDTO, PasswordVO.class);
		passwordVO.setTxValor(passwordDAO.desencriptarCampo(passwordDTO.getTxValor()));
		
		return passwordVO;
	}	
}

