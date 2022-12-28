package tests;

import base.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class WeatherServiceApiTest extends TestBase {

    @ParameterizedTest(name = "{0} has been verified")
    @CsvFileSource(resources = "/testdata.csv")
    public void shouldGetWeatherInfoByCityFromCsvSourceTest(String city, String code, int id){
        given()
                .spec(getWeatherInfoById(id)).
        when()
                .get().
        then()
                .spec(getResponse(city, code, id));
    }
}
