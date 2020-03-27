package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Parsea los datos recibidos de un archivo csv y genera tantos objetos como
 * tantas l&iacute;neas hayan
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class CsvUtils {
	private static final CsvMapper mapper = new CsvMapper();

	/**
	 * 
	 * @param <T>
	 * @param clazz  Clase a la que se desea convertir cada l&iacute;nea del CSV
	 * @param stream Contenido del archivo en bytes
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
		CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
		ObjectReader reader = mapper.readerFor(clazz).with(schema);
		return reader.<T>readValues(stream).readAll();
	}
}
