package mx.com.teclo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class GeneradorCsvTest {
	
	
	
	private static final String NA = "NA";
	private static final String CD_DELIMITADOR = ",";
	// De las placas y vins se excluyen letras i, ñ, o, q
	private static final String CHARS_LETRAS = "ABCDEFGHJKLMNPRSTUVWXYZ";
	private static final String CHARS_ALFANUMERICOS = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
	private static final String CHARS_TAXI_PARTE1 = "AB";
	private static final String CHARS_NUMEROS = "0123456789";
	private static final String GUION = "-";
	private static final String DOS_PUNTOS = ":";
	// No repetir nombres de los headers
	private static final String HEADERS = "CD ORDEN SERVICIO,FH CITA,FH ATENCION INI,FH ATENCION FIN,FH ATENCION PARCIAL,NU TIEMPO DURACION,TX MOTIVO CAMBIO,CD PLACA VEHICULO,CD VIN,CD TARJETA CIRCULACION,NB MARCA,NB SUB MARCA,CD MODELO,ST VEHICULO,CD TIPO VEHICULO,NB TIPO VEHICULO,NU ORDEN VEHICULO,ST TIPO VEHICULO,CD CENTRO INSTALACION,NB CENTRO INSTALACION,NB CALLE,NU EXTERIOR,NB ENTRE CALLE,NB Y CALLE,NB COLONIA,NB ALCALDIA,NB DIAS ATENCION,HR ATENCION INI,HR ATENCION FIN,NU ORDEN CENTRO INST.,ST CENTRO INSTALACION,CD KIT INSTALACION,CD PLAN,NB PLAN,TX PLAN,CD CONCESION,NB CONCESION,NU ORDEN KIT,ST CONCESION";
	private static SecureRandom rnd = new SecureRandom();
	private List<String> rutas = new ArrayList<String>();
	private List<String> alcaldias = new ArrayList<String>();
	private List<String> horario = new ArrayList<String>();
	private List<String> cuartosHr = new ArrayList<String>();
	private SimpleDateFormat sdfCodigoOrdenServicio = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfCita = new SimpleDateFormat("dd/MM/yyyy");
	private int diaAnio = 365;
	private static final String RUTA_CSV_GENERADO = "C:\\Users\\UNITIS-ODM2\\Desktop\\CSV_DEMO_ENTREGA.csv";

	@PostConstruct
	public void init() {
		cargarCatalogos();
	}

	/**
	 * Automóviles A00-AAA<br>
	 * Camiones A-000-AA<br>
	 * Autobuses 00-AA-1<br>
	 * Remolques A-0A-00<br>
	 * Convertidor (Dolly)A-00000<br>
	 */

	@Test
	public void generarCsvTest() {
		File fileResultado = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fileResultado = new File(RUTA_CSV_GENERADO);
			fw = new FileWriter(fileResultado);
			bw = new BufferedWriter(fw);
			if (StringUtils.isNotBlank(HEADERS)) {
				bw.write(HEADERS);
				bw.newLine();
			}
			for (int i = 0; i < 3; i++) {
				int nuLinea = rnd.nextInt(15000);
				String lineaGenerada = getLineaDatos(nuLinea % 2 == 0, i + 1);
				bw.write(lineaGenerada);
				bw.newLine();
			}

			bw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getLineaDatos(boolean par, int nuLinea) {
		// CD ORDEN SERVICIO
		StringBuilder sbLin = new StringBuilder();
		String hora = horario.get(rnd.nextInt(8));
		String minuto = cuartosHr.get(rnd.nextInt(4));
		String segundo = "00";
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		if (nuLinea >= 10 && nuLinea % 10 == 0) {
			c.add(Calendar.DAY_OF_MONTH, 0);
		} else {
			c.add(Calendar.DAY_OF_MONTH, rnd.nextInt(diaAnio));
		}

		Date fechaCita = c.getTime();
		String siguienteFecha = sdfCodigoOrdenServicio.format(fechaCita);
		String codeAlcaldia = alcaldias.get(rnd.nextInt(alcaldias.size()));
		String placa = par ? getRandomTaxi() : getRandomCamion();
		String plan = par ? "PLN1" : "PLN2";
		String concesion = par ? rutas.get(rnd.nextInt(rutas.size())) : placa.substring(0, 5);

		sbLin.append(codeAlcaldia);
		sbLin.append(GUION);
		sbLin.append(siguienteFecha);
		sbLin.append(GUION);
		sbLin.append(hora);
		sbLin.append(minuto);
		sbLin.append(segundo);
		sbLin.append(GUION);
		sbLin.append(placa);
		sbLin.append(CD_DELIMITADOR);

		// FH CITA
		sbLin.append(sdfCita.format(fechaCita));
		sbLin.append(" ");
		sbLin.append(hora);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(minuto);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(segundo);
		sbLin.append(CD_DELIMITADOR);

		// FH ATENCION INI
		sbLin.append(sdfCita.format(fechaCita));
		sbLin.append(" ");
		sbLin.append(hora);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(minuto);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(segundo);
		sbLin.append(CD_DELIMITADOR);

		// FH ATENCION FIN
		sbLin.append(sdfCita.format(fechaCita));
		sbLin.append(" ");
		sbLin.append(hora);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(minuto);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(segundo);
		sbLin.append(CD_DELIMITADOR);

		// FH ATENCION PARCIAL
		sbLin.append(sdfCita.format(fechaCita));
		sbLin.append(" ");
		sbLin.append(hora);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(minuto);
		sbLin.append(DOS_PUNTOS);
		sbLin.append(segundo);
		sbLin.append(CD_DELIMITADOR);
		
		// NU TIEMPO DURACION
		sbLin.append(BigDecimal.ZERO.intValue());
		sbLin.append(CD_DELIMITADOR);

		// TX MOTIVO CAMBIO
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// CD PLACA VEHICULO
		sbLin.append(placa);
		sbLin.append(CD_DELIMITADOR);
		// CD VIN
		sbLin.append(getCodigoVin());
		sbLin.append(CD_DELIMITADOR);

		// CD TARJETA CIRCULACION
		sbLin.append(getNumTarjeta());
		sbLin.append(CD_DELIMITADOR);
		// NB MARCA
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB SUB MARCA
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// CD MODELO
		sbLin.append(2020 - rnd.nextInt(10));
		sbLin.append(CD_DELIMITADOR);
		// ST VEHICULO
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);

		// CD TIPO VEHICULO
		sbLin.append(par ? "AUTOMOVIL" : "CAMION");
		sbLin.append(CD_DELIMITADOR);

		// NB TIPO VEHICULO
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NU ORDEN
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);
		// ST TIPO VEHICULO
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);
		// CD CENTRO INSTALACION
		sbLin.append(codeAlcaldia);
		sbLin.append(CD_DELIMITADOR);
		// NB CENTRO INSTALACION
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB CALLE
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NU EXTERIOR
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB ENTRE CALLE
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB Y CALLE
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB COLONIA
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB ALCALDIA
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// NB DIAS ATENCION
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);
		// HR ATENCION INI
		sbLin.append("09:00:00");
		sbLin.append(CD_DELIMITADOR);
		// HR ATENCION FIN
		sbLin.append("17:00:00");
		sbLin.append(CD_DELIMITADOR);
		// NU ORDEN
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);
		// ST CENTRO INSTALACION
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);
		// CD KIT INSTALACION
		sbLin.append(getKit());
		sbLin.append(CD_DELIMITADOR);

		// CD PLAN
		sbLin.append(plan);
		sbLin.append(CD_DELIMITADOR);

		// NB PLAN
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);

		// TX PLAN
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);

		// CD CONCESION
		sbLin.append(concesion);
		sbLin.append(CD_DELIMITADOR);

		// NB CONCESION
		sbLin.append(NA);
		sbLin.append(CD_DELIMITADOR);

		// NU ORDEN
		sbLin.append(BigDecimal.ONE.intValue());
		sbLin.append(CD_DELIMITADOR);

		// ST CONCESION
		sbLin.append(BigDecimal.ONE.intValue());

		return sbLin.toString();
	}

	private String getRandomCamion() {
		StringBuilder sb = new StringBuilder();

		int posRuta = rnd.nextInt(81);
		int posPrimerNumPaq2 = rnd.nextInt(CHARS_LETRAS.length());
		int posSegundoNumPaq2 = rnd.nextInt(CHARS_LETRAS.length());
		sb.append(rutas.get(posRuta));
		sb.append(GUION);
		sb.append(CHARS_LETRAS.charAt(posPrimerNumPaq2));
		sb.append(CHARS_LETRAS.charAt(posSegundoNumPaq2));
		return sb.toString();
	}

	private String getRandomTaxi() {

		StringBuilder sb = new StringBuilder();

		int posLetraInicial = rnd.nextInt(CHARS_TAXI_PARTE1.length());
		int posSegundoNumPaq2 = rnd.nextInt(CHARS_NUMEROS.length());

		int posPrimerNumPaq3 = rnd.nextInt(CHARS_LETRAS.length());
		int posSegundoNumPaq3 = rnd.nextInt(CHARS_LETRAS.length());
		int posTerceroNumPaq3 = rnd.nextInt(CHARS_LETRAS.length());

		sb.append(CHARS_TAXI_PARTE1.charAt(posLetraInicial));
		sb.append(CHARS_NUMEROS.charAt(0));
		sb.append(CHARS_NUMEROS.charAt(posSegundoNumPaq2));
		sb.append(GUION);
		sb.append(CHARS_LETRAS.charAt(posPrimerNumPaq3));
		sb.append(CHARS_LETRAS.charAt(posSegundoNumPaq3));
		sb.append(CHARS_LETRAS.charAt(posTerceroNumPaq3));

		return sb.toString();
	}

	private String getCodigoVin() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 17; i++) {
			if (i < 9) {
				sb.append(CHARS_LETRAS.charAt(rnd.nextInt(CHARS_LETRAS.length())));
			} else {
				sb.append(CHARS_LETRAS.charAt(rnd.nextInt(CHARS_NUMEROS.length())));
			}
		}
		return sb.toString();
	}

	private String getKit() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 15; i++) {
			sb.append(CHARS_ALFANUMERICOS.charAt(rnd.nextInt(CHARS_ALFANUMERICOS.length())));
		}
		return sb.toString();
	}

	private String getNumTarjeta() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 11; i++) {
			sb.append(CHARS_NUMEROS.charAt(rnd.nextInt(CHARS_NUMEROS.length())));
		}
		return sb.toString();
	}

	private void cargarCatalogos() {
		//CAT CONCESIONARIAS 
		// VERIFICAR QUE EXISTAN LOS CODIGOS
		rutas.add("R-001");
		rutas.add("R-010");
		rutas.add("R-100");
		rutas.add("R-101");
		rutas.add("R-103");
		rutas.add("R-104");
		rutas.add("R-106");
		rutas.add("R-108");
		rutas.add("R-011");
		rutas.add("R-111");
		rutas.add("R-112");
		rutas.add("R-114");
		rutas.add("R-115");
		rutas.add("R-119");
		rutas.add("R-012");
		rutas.add("R-013");
		rutas.add("R-015");
		rutas.add("R-017");
		rutas.add("R-018");
		rutas.add("R-019");
		rutas.add("R-002");
		rutas.add("R-020");
		rutas.add("R-021");
		rutas.add("R-022");
		rutas.add("R-023");
		rutas.add("R-024");
		rutas.add("R-025");
		rutas.add("R-026");
		rutas.add("R-028");
		rutas.add("R-003");
		rutas.add("R-030");
		rutas.add("R-031");
		rutas.add("R-032");
		rutas.add("R-033");
		rutas.add("R-034");
		rutas.add("R-035");
		rutas.add("R-036");
		rutas.add("R-037");
		rutas.add("R-039");
		rutas.add("R-004");
		rutas.add("R-040");
		rutas.add("R-041");
		rutas.add("R-042");
		rutas.add("R-044");
		rutas.add("R-046");
		rutas.add("R-049");
		rutas.add("R-005");
		rutas.add("R-050");
		rutas.add("R-051");
		rutas.add("R-053");
		rutas.add("R-055");
		rutas.add("R-056");
		rutas.add("R-057");
		rutas.add("R-058");
		rutas.add("R-060");
		rutas.add("R-061");
		rutas.add("R-062");
		rutas.add("R-064");
		rutas.add("R-066");
		rutas.add("R-068");
		rutas.add("R-069");
		rutas.add("R-070");
		rutas.add("R-071");
		rutas.add("R-073");
		rutas.add("R-074");
		rutas.add("R-075");
		rutas.add("R-076");
		rutas.add("R-077");
		rutas.add("R-080");
		rutas.add("R-081");
		rutas.add("R-083");
		rutas.add("R-084");
		rutas.add("R-085");
		rutas.add("R-087");
		rutas.add("R-088");
		rutas.add("R-089");
		rutas.add("R-009");
		rutas.add("R-091");
		rutas.add("R-094");
		rutas.add("R-095");
		rutas.add("R-099");
		//CAT CENTROS DE INSTALACION
		//VERIFICAR QUE EXISTAN LOS MISMOS CODIGOS
		alcaldias.add("TLPN");
		alcaldias.add("VCAR");
		alcaldias.add("AZCP");
		alcaldias.add("IZTP");
		alcaldias.add("IZTC");
		alcaldias.add("MHDG");
		alcaldias.add("MGDC");
		alcaldias.add("CYCN");
		alcaldias.add("MLPA");
		alcaldias.add("TLHC");
		alcaldias.add("BENJ");
		alcaldias.add("CJMP");
		alcaldias.add("GAMD");
		alcaldias.add("CUAH");
		alcaldias.add("ALVO");
		alcaldias.add("XCHM");

		horario.add("09");
		horario.add("10");
		horario.add("11");
		horario.add("12");
		horario.add("13");
		horario.add("14");
		horario.add("15");
		horario.add("16");

		cuartosHr.add("15");
		cuartosHr.add("30");
		cuartosHr.add("45");
		cuartosHr.add("00");

	}
}
