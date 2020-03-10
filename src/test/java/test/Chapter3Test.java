package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter3Test {

    @DataProvider(name = "chapter_3")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"}
        };
    }

    @Test(dataProvider = "chapter_3")
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody(String countryCode,
                                                               String zipCode, String placeName) {

        given().
            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
            get("http://zippopotam.us/{countryCode}/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(placeName));
    }
}
