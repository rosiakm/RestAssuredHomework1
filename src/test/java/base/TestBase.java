package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public static ResponseSpecification responseSpecification;

    @BeforeEach
    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static RequestSpecification getWeatherInfoById(int id){
        return (new RequestSpecBuilder()
                .setBaseUri("https://api.openweathermap.org/data/2.5/weather")
                .addParam("id",id)
                .addParam("appid", "89a2ed8a594cc497a6273490e7ca59dd")
                .addHeader("Content-Type","application/json")
                .build());
    }

    public static ResponseSpecification getResponse(String city, String code, int id){
        responseSpecification = (new ResponseSpecBuilder()
                .expectStatusCode(200))
                .expectContentType("application/json")
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("sys.country",Matchers.equalTo(code))
                .expectBody("id",Matchers.equalTo(id))
                .expectBody("name",Matchers.equalTo(city))
                .build();
        return responseSpecification;
    }
}
