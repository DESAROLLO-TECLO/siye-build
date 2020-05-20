package mx.com.teclo.siye.negocio.service.reportes;

import java.io.ByteArrayOutputStream;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

public interface ReportesService {
	
	ByteArrayOutputStream getReporteDOS(OrdenServicioVO os, CargaExpedienteImgVO detalle, Boolean conImagenes);

	byte[] getPDF(ByteArrayOutputStream b);
}
