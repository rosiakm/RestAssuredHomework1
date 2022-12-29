package tests;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class WeatherServiceApiTest extends TestBase {

    @DisplayName("Should get weather info by city ID")
    @ParameterizedTest(name = "City {0} with id {2} has been tested")
    @CsvFileSource(resources = "/testdata.csv")
    @Tag("weather")
    @Execution(ExecutionMode.CONCURRENT)
    public void shouldGetWeatherInfoByCityFromCsvSourceTest(String city, String code, int id){
        given()
                .spec(getWeatherInfoById(id)).
        when()
                .get().
        then()
                .spec(getResponse(city, code, id));
    }
}
