package CRUD_Operations;

import CRUD_Operations.Deserialization.Deserialization;
import DefiningRequestBody.DefiningJsonBody;
import Payload.payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import comBase.Base_Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class CreateAPI extends Base_Properties {

    static Logger log = Logger.getLogger(CreateAPI.class);
    public static String bookingid;
    public static String name=null,checkOut;
    DefiningJsonBody jsonData=new DefiningJsonBody();
    Response response;
    Deserialization deserialization=new Deserialization();
    @Test
    public void createBooking() throws JsonProcessingException {

        log.info("Booking Creation Started");

        RestAssured.baseURI=loadproperties().getProperty("URI");
        RestAssured.basePath="booking";

        // Hitting API with POST request.
        response=given().accept("application/json").contentType("application/json")
                .body(jsonData.definingRequestBody()).when().post().then().extract().response();

        response.then().log().all();

        bookingid=response.jsonPath().get("bookingid").toString();

        deserialization();
        validations();

        log.info("Booking Record Has Been Created and Validated");
    }

    public void deserialization(){
        payload payload=deserialization.deserialization();
        name=payload.getFirstname();
        checkOut=payload.getBookingdates().getCheckout();
    }

    public void validations(){

        //Validations with name , checkout-date and status-code
        Assert.assertEquals(response.getStatusCode(),200 , "Status Code Not Matched. ");
        Assert.assertEquals(name, response.jsonPath().get("booking.firstname"), " Created Name doesn't matched with expected name. ");
        Assert.assertEquals(response.jsonPath().get("booking.bookingdates.checkout"), checkOut, "Created checkout date and checkout date we are passing has not matched. ");
    }
}
