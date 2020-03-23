package mx.com.teclo;
/*import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Value("${prop.swagger.enabled:true}")
	private boolean enableSwagger;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(enableSwagger)
				.select()
				.apis(RequestHandlerSelectors.basePackage("mx.com.teclo.sie.api.rest"))
				.paths(PathSelectors.any())
				.build()
              .apiInfo(metaData())
              .securitySchemes(Lists.newArrayList(apiKey()));
		
	}
		
	   @Bean
	    public SecurityConfiguration securityInfo() {
	        return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "X-Auth-Token", "");
	    }

	    private ApiKey apiKey() {
	        return new ApiKey("X-Auth-Token", "X-Auth-Token", "X-Auth-Token");
	    }
	
	
	  @SuppressWarnings("rawtypes")
	private ApiInfo metaData() {
	        return new ApiInfo(
	                "Sistema de Inspección y Evaluaciones (SIE) REST API",
	                "REST API para Sistema de Inspección y Evaluaciones (SIE)",
	                "1.0",
	                "Terms of service",
	                new Contact("Teclo Mexicana", "http://teclomexicana.mx/", "francisco.martinez@teclo.mx"),
	                "",
	                "",
	                new ArrayList<VendorExtension>());// 
	    }
	  

}*/
