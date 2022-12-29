package base;

import configuration.YamlProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    private static YamlProvider yamlProvider;
    @BeforeEach
    public void setup(){
        yamlProvider = YamlProvider.getInstance();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static RequestSpecification getWeatherInfoById(int id){
        return (new RequestSpecBuilder()
                .setBaseUri(System.getProperty("base_uri"))
                .addParam("id",id)
                .addParam("appid", System.getProperty("appid"))
                .addHeader("Content-Type",System.getProperty("content_type"))
                .build());
    }

    public static ResponseSpecification getResponse(String city, String code, int id){
        return (new ResponseSpecBuilder()
                .expectStatusCode(200))
                .expectContentType(System.getProperty("content_type"))
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("sys.country",Matchers.equalTo(code))
                .expectBody("id",Matchers.equalTo(id))
                .expectBody("name",Matchers.equalTo(city))
                .build();
    }
}
