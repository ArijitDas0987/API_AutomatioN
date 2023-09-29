package CRUD_Operations;

import comBase.Base_Properties;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAPI extends Base_Properties {

    static Logger log = Logger.getLogger(DeleteAPI.class);
    @Test
    public void deleteBookingRecord(){
        log.info("Booking Deletion Process Started");

        RestAssured.baseURI= loadproperties().getProperty("URI");
        RestAssured.basePath="booking/"+ CreateAPI.bookingid;
        String token="token="+LoginAPI.token;

        // Hitting API with DELETE request.
        given().header("Cookie",token).when().delete().then().assertThat().statusCode(201)
                .log().body();

        log.info("Booking Record Has Been Deleted and Validated");
    }
}
