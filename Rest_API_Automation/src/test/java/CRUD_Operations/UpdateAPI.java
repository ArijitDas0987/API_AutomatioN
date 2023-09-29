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

public class UpdateAPI extends Base_Properties {

    static Logger log = Logger.getLogger(UpdateAPI.class);
    public static String updatedName=null , totalPrice , checkOut;
    DefiningJsonBody jsonData=new DefiningJsonBody();
    Deserialization deserialization=new Deserialization();
    Response resp;
    @Test
    public void updateBookingRecord() throws JsonProcessingException {

        log.info("Booking Updation Process Started");

        RestAssured.baseURI=loadproperties().getProperty("URI");
        RestAssured.basePath="booking/"+CreateAPI.bookingid;
        String token="token="+LoginAPI.token;

        // Hitting API with PUT request.
        resp=given().accept("application/json").contentType("application/json").header("Cookie",token).body(jsonData.definingRequestBody())
                .when().put().then().extract().response();

        resp.then().log().all();

        deserialization();
        validations();
        log.info("Booking Has Been Updated and Validated");
    }

    public void deserialization(){
        payload payload=deserialization.deserialization();
        updatedName=payload.getFirstname();
        totalPrice=String.valueOf(payload.getTotalprice());
        checkOut=payload.getBookingdates().getCheckout();
    }

    public void validations(){

        //Normal Validations
        Assert.assertEquals(200,resp.getStatusCode());
        Assert.assertEquals(resp.jsonPath().get("firstname"),updatedName);
        Assert.assertEquals(resp.jsonPath().get("totalprice").toString(),totalPrice);
        Assert.assertEquals(resp.jsonPath().get("bookingdates.checkout"),checkOut);

        // Validations for matching updated data with previously created data
        Assert.assertNotEquals(updatedName,CreateAPI.name);
        Assert.assertNotEquals(checkOut,CreateAPI.checkOut);
    }
}
