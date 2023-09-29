package CRUD_Operations;

import CRUD_Operations.Deserialization.Deserialization;
import Payload.payload;
import comBase.Base_Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class GetAPI extends Base_Properties {

    Deserialization deserialization=new Deserialization();
    static Logger log = Logger.getLogger(GetAPI.class);

    public String name; String additionalneeds;Response resp;
    @Test
    public void getBookingRecord(){

        log.info("Fetching Booking Record Process Started");

        RestAssured.baseURI= loadproperties().getProperty("URI");
        RestAssured.basePath="booking/"+ CreateAPI.bookingid;

        // Hitting API with GET request.
        resp=given().accept("application/json").when().get().then().log().body().extract().response();

        deserialization();
        validations();
        log.info("Booking Record Has Been Fetched and Validated");
    }

    public void deserialization(){
        payload payload=deserialization.deserialization();
        name=payload.getFirstname();
        additionalneeds=payload.getAdditionalneeds();
    }

    public void validations(){
        Assert.assertEquals(200,resp.getStatusCode());
        Assert.assertEquals(name,resp.jsonPath().get("firstname")," Both name doesn't match. ");
        Assert.assertEquals(additionalneeds,resp.jsonPath().get("additionalneeds") , "Both additionalneeds value doesn't match.");
    }
}