package mx.com.teclo.siye.util.comun;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class RutinasTiempoImpl {
	private final int HOY = 1, AYER = 2, ESTA_SEMANA = 3, ULTIMA_SEMANA = 4, ULTIMO_7_DIAS = 5, ESTE_MES = 6,
			MES_PASADO = 7, ULTIMOS30_DIAS = 8;

	public Date getFechaActual() {
		Date fechaActual = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(fechaActual);
		try {
			fechaActual = formateador.parse(fecha);
		} catch (ParseException e) {
		}
		return fechaActual;
	}

	/**
	 * @author Kevin Ojeda
	 * @return Integer
	 */
	public Integer diferenciaDeDiasEntreFechas(Date Finicial, Date Ffinal) {
		// String hoy = new
		// SimpleDateFormat("dd-MM-yyyy").format(Finicial);//Finicial.getTime();
		long today = Finicial.getTime();
		long lastDate = Ffinal.getTime();
		if (lastDate < today)
			return -1;
		long diffTime = lastDate - today;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return ((int) diffDays);
	}

	public Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

	public String getStringDateFromFormta(String format, Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}

	/**
	 * @author genunt
	 * @param stringDate String
	 * @return Date dd/MM/yyyy
	 */
	public Date convertirStringDate(String stringDate, String formato) {
		SimpleDateFormat format = new SimpleDateFormat(formato);
		Date fecha = new Date();

		try {
			fecha = format.parse(stringDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fecha;
	}

//	public List<ComboValuesVO> obtenerFechasSalarios() {
//		List<ComboValuesVO> listaComboValuesVO = new ArrayList<ComboValuesVO>();
//		Date date = new Date();
//		SimpleDateFormat custom = new SimpleDateFormat("yyyy");
//		String fecha = custom.format(date);
//		List<Integer> anios = new ArrayList<Integer>();
//		anios.add(Integer.parseInt(fecha));
//		anios.add(Integer.parseInt(fecha) - 1);
//		anios.add(Integer.parseInt(fecha) - 2);
//		return listaComboValuesVO;
//	}

	/**
	 * @author genunt
	 * @param fecha
	 * @param dias
	 * @return Date
	 */
	public Date incrementarNumeroDias(Date fecha, Integer dias) {
		List<Integer> listaAno = new ArrayList<Integer>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		listaAno.add(calendar.get(Calendar.YEAR));

		for (int i = 1; i <= dias; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, i);
		}

		return calendar.getTime();
	}

	/**
	 * @author genunt
	 * @param fecha Date
	 * @return Map<String, String>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> obtenerFechaDesglosada(Date fecha) {
		Map listaFecha = new HashMap<String, String>();
		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);

		switch (month) {
		case 0: {
			result = "enero";
			break;
		}
		case 1: {
			result = "febrero";
			break;
		}
		case 2: {
			result = "marzo";
			break;
		}
		case 3: {
			result = "abril";
			break;
		}
		case 4: {
			result = "mayo";
			break;
		}
		case 5: {
			result = "junio";
			break;
		}
		case 6: {
			result = "julio";
			break;
		}
		case 7: {
			result = "agosto";
			break;
		}
		case 8: {
			result = "septiembre";
			break;
		}
		case 9: {
			result = "octubre";
			break;
		}
		case 10: {
			result = "noviembre";
			break;
		}
		case 11: {
			result = "diciembre";
			break;
		}
		default: {
			result = "Error";
			break;
		}
		}

		listaFecha.put("day", day);
		listaFecha.put("month", result);
		listaFecha.put("year", year);

		return listaFecha;
	}

	/**
	 * @author genunt
	 * @param fecha String yyyyMMdd
	 * @return Date dd/MM/yyyy
	 */
	public Date getFechaStringDate(String fecha) {
		String ano = fecha.substring(0, 4);
		String mes = fecha.substring(4, 6);
		String dia = fecha.substring(6, 8);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		try {
			date = format.parse(dia + "/" + mes + "/" + ano);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return date;
	}

	/**
	 * @author genunt
	 * @return String
	 */
	public String getStringFechaDDMMYYYHHMMSS() {
		Calendar fecha = Calendar.getInstance();
		int ano = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		int segundo = fecha.get(Calendar.SECOND);

		return dia + "-" + mes + "-" + ano + "-" + hora + "-" + minuto + "-" + segundo;
	}

	/**
	 * @author UnitisDes0416
	 * @param fecha Date
	 * @return String anio
	 */
	public String obtenerAnioFecha(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		Integer year = calendar.get(Calendar.YEAR);

		return year.toString();
	}

	/**
	 * @author javier07
	 * @param file File
	 * @return Date
	 */
	public Date obtenrFechaModificacionArchivo(File file) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Mexico_City"));
		calendar.setTimeInMillis(file.lastModified());

		Date fecha = calendar.getTime();
		String fechaArchivo = formateador.format(fecha);
		try {
			fecha = formateador.parse(fechaArchivo);
		} catch (ParseException e) {
		}
		return fecha;
	}

	/**
	 * @author genunt
	 * @param stringDate String
	 * @return Date dd/MM/yyyy
	 */
	public Date convertirStringDate(String stringDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();

		try {
			fecha = format.parse(stringDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fecha;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public String getFecha_ddMMYYYY_hhmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * Obtiene el año en dos digitos 2017 -> 17
	 * 
	 * @author Jorge Luis
	 * @return Stirng
	 */
	public String getAnioEnDosDigistos(Date f) {
		DateFormat df = new SimpleDateFormat("YY");
		String formattedDate = df.format(f.getTime());
		return formattedDate;
	}

	/**
	 * Metodo que recibe el numero del mes y retorna el nombre
	 * 
	 * @author Javier Flores
	 * @param mes
	 * @return String
	 */
	public String getNombreMes(int mes) {

		String nombreMes;

		switch (mes - 1) {
		case 0: {
			nombreMes = "Enero";
			break;
		}
		case 1: {
			nombreMes = "Febrero";
			break;
		}
		case 2: {
			nombreMes = "Marzo";
			break;
		}
		case 3: {
			nombreMes = "Abril";
			break;
		}
		case 4: {
			nombreMes = "Mayo";
			break;
		}
		case 5: {
			nombreMes = "Junio";
			break;
		}
		case 6: {
			nombreMes = "Julio";
			break;
		}
		case 7: {
			nombreMes = "Agosto";
			break;
		}
		case 8: {
			nombreMes = "Septiembre";
			break;
		}
		case 9: {
			nombreMes = "Octubre";
			break;
		}
		case 10: {
			nombreMes = "Noviembre";
			break;
		}
		case 11: {
			nombreMes = "Diciembre";
			break;
		}
		default: {
			nombreMes = "Error";
			break;
		}
		}// fin case

		return nombreMes;
	}

	/**
	 * Metodo que recibe el numero y año y devuelbe el total de dias de ese mes
	 * 
	 * @author Fernando Octavio
	 * @param mes
	 * @param anio
	 * @return String
	 */
	public int numeroDiasXMesyAnio(int anio, int mes) {
		Calendar fecha = Calendar.getInstance();
		fecha.set(anio, mes, 0);
		int totalDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
		return totalDias;
	}

	/**
	 * Metodo que devuelve la fecha actual eb formato dd/MM/YYYY hh:mm:ss
	 * 
	 * @author Fernando Octavio
	 * @return String
	 */

	public String getFechaActualFO() {
		String fechaActual = "";
		Date myDate = new Date();
		fechaActual = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(myDate);
		return fechaActual;
	}

	// Genera Fechas por rango al perido de fecha
	// * @author xxx
	public List<String> generaRangoFechas(Long periodoFecha) {
		int rangoF = Math.round(periodoFecha);
		List<String> rangoFechas = new ArrayList<String>();
		Calendar dateActual = Calendar.getInstance();
		Calendar dateInicio;
		dateInicio = (Calendar) dateActual.clone();
		int diaInicio = 0, mesInicio = 0, añoInicio = 0;
		String fechaInicio = "", fechaFin = "";
		Calendar dateFin;
		dateFin = (Calendar) dateActual.clone();
		int diaFin = 0, mesFin = 0, añoFin = 0;
		int diaSemana = 0, inicioSemana = 0, diaMes = 0, diasMes = 0, inicioMes = 0, finMes = 0;

		switch (rangoF) {
		case HOY:
			dateFin = (Calendar) dateInicio.clone();
			break;

		case AYER:
			dateInicio.add(Calendar.DAY_OF_YEAR, -1);
			dateFin.add(Calendar.DAY_OF_YEAR, -1);
			break;

		case ESTA_SEMANA:
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if (diaSemana != 1) {
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}
			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
			break;

		case ULTIMA_SEMANA:
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if (diaSemana != 1) {
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}
			dateInicio.add(Calendar.WEEK_OF_YEAR, -1);

			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
			break;

		case ULTIMO_7_DIAS:
			dateInicio.add(Calendar.DAY_OF_YEAR, -7);
			dateFin = (Calendar) dateActual.clone();
			break;

		case ESTE_MES:
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if (diaMes != 1) {
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}

			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;

			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
			break;

		case MES_PASADO:
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if (diaMes != 1) {
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}
			dateInicio.add(Calendar.MONTH, -1);

			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;

			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
			break;

		case ULTIMOS30_DIAS:
			dateInicio.add(Calendar.DAY_OF_YEAR, -30);
			dateFin = (Calendar) dateActual.clone();
			break;

		default:
			return rangoFechas;
		}
		diaInicio = dateInicio.get(Calendar.DAY_OF_MONTH);
		mesInicio = dateInicio.get(Calendar.MONTH) + 1;
		añoInicio = dateInicio.get(Calendar.YEAR);
		fechaInicio = diaInicio + "/" + mesInicio + "/" + añoInicio;

		diaFin = dateFin.get(Calendar.DAY_OF_MONTH);
		mesFin = dateFin.get(Calendar.MONTH) + 1;
		añoFin = dateFin.get(Calendar.YEAR);
		fechaFin = diaFin + "/" + mesFin + "/" + añoFin;

		rangoFechas.add(fechaInicio);
		rangoFechas.add(fechaFin);
		return rangoFechas;
	}

	public String ParseFecha_dd_MM_yyyy(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * Descripción: Método para sacar la diferencia de tiempo entre dos fechas
	 * devuelve un Map con los días, horas, minutos y segundos
	 * 
	 * @author jorgeluis
	 * @return Map
	 */
	public Map<String, Object> diferenciaFechas(Date fechaInicio, Date fechaFin, String formato) {
		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat(formato);
		Date d1 = null;
		Date d2 = null;
		Map<String, Object> mapReturn = null;
		try {

			String dateStart = format.format(fechaInicio);
			String dateStop = format.format(fechaFin);

			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			Long diffSeconds = diff / 1000 % 60;
			Long diffMinutes = diff / (60 * 1000) % 60;
			Long diffHours = diff / (60 * 60 * 1000) % 24;
			Long diffDays = diff / (24 * 60 * 60 * 1000);

			mapReturn = new HashMap<>();
			mapReturn.put("days", diffDays.toString());
			mapReturn.put("hours", diffHours.toString());
			mapReturn.put("minutes", diffMinutes.toString());
			mapReturn.put("seconds", diffSeconds.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapReturn;
	}

	@SuppressWarnings("unused")
	public String tiempoEntreDosFechas(String fInicio, String fFin) {
		Date dinicio = null, dfinal = null;
		long milis1, milis2, diff;
		Long segundosPorDia = 86400L;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// PARSEO STRING A DATE
		try {
			dinicio = sdf.parse(fInicio);
			dfinal = sdf.parse(fFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// INSTANCIA DEL CALENDARIO GREGORIANO
		Calendar cinicio = Calendar.getInstance();
		Calendar cfinal = Calendar.getInstance();

		// ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
		cinicio.setTime(dinicio);
		cfinal.setTime(dfinal);

		milis1 = cinicio.getTimeInMillis();

		milis2 = cfinal.getTimeInMillis();

		diff = milis2 - milis1;

		// calcular la diferencia en segundos
		long diffSegundos = 0L, diffdias = 0L, diffHoras = 0L;

		diffSegundos = Math.abs(diff / 1000);

		// calcular dias
		if (diffSegundos >= segundosPorDia) {
			diffdias = diffSegundos / segundosPorDia;
			if (diffdias != 0L) {
				diffSegundos = diffSegundos - (diffdias * segundosPorDia);
			}
		}

		if (diffSegundos >= 3600L) {
			diffHoras = (diffSegundos / 3600L);
			if (diffHoras != 0L) {
				diffSegundos = diffSegundos - (diffHoras * 3600L);
			}
		}

		long diffMinutos = Math.abs(diffSegundos / 60);
		long restominutos = diffMinutos % 60;
		
		String devolver = "";
		String d="",hr="",min="";
		
		if(diffdias!=0) {
			d="Dias: "+diffdias+" ";
		}
		
		if(diffHoras!=0) {
			hr="Horas: "+diffHoras+" ";
		}
		
		if(restominutos!=0) {
			min ="Minutos: "+restominutos;
		}

		devolver = String.valueOf(d+hr+min);

		return devolver;

	}

}
