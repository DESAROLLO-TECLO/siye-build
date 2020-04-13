package mx.com.teclo;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionesGenerales {

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
 		dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@172.25.25.13:1521:teclodes");
        dataSourceBuilder.username("TECLO");
        dataSourceBuilder.password("T3cl0D3s4");
		return dataSourceBuilder.build();
	}
	
}
