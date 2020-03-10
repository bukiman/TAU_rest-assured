package test;

import io.restassured.http.ContentType;
import model.Location;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Chapter6Test {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        Location location =

        given().
        when().
            get("http://api.zippopotam.us/us/90210").
        as(Location.class);

        Assert.assertEquals(
                "Beverly Hills",
                location.getPlaces().get(0).getPlaceName()
        );
    }

    @Test
    public void requestLvZipCode1050_checkStatusCode_expect200() {

        Location location = new Location();

        given().
            contentType(ContentType.JSON).
            body(location).
            log().body().
        when().
            post("http://localhost:9876/lv/1050").
        then().
            assertThat().
            statusCode(200);
    }
}
