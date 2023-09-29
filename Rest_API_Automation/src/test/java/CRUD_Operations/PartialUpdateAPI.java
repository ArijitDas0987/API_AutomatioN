package CRUD_Operations;

import CRUD_Operations.Deserialization.Deserialization;
import DefiningRequestBody.DefiningJsonBody;
import Payload.payload;
import comBase.Base_Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class PartialUpdateAPI extends Base_Properties {

    static Logger log = Logger.getLogger(PartialUpdateAPI.class);
    Response resp; String totalPrice , updatedname , createdTotalPrice , createdFirstName;
    public DefiningJsonBody jsonBody=new DefiningJsonBody();

    Deserialization deserialization=new Deserialization();
    @Test
    public void partialUpdate_BookingRecord(){
        log.info("Partial Updation on Booking Started");

        createdTotalPrice=jsonBody.mapData.get("totalprice");
        createdFirstName=jsonBody.mapData.get("firstname");

        jsonBody.partialUpdation();
        RestAssured.baseURI=loadproperties().getProperty("URI");
        RestAssured.basePath="booking/"+CreateAPI.bookingid;
        String token="token="+LoginAPI.token;

        // Hitting API with PATCH request.
        resp=given().accept("application/json").contentType("application/json").header("Cookie",token)
                .body(jsonBody.mapData).when().patch().then().extract().response();

        resp.then().log().all();

        deserialization();
        validations();

        log.info("Booking Record Has Been Partially Updated and Validated");
    }

    public void deserialization(){
        payload payload=deserialization.deserialization();
        updatedname=payload.getFirstname();
        totalPrice=String.valueOf(payload.getTotalprice());
    }

    public void validations(){
        //Normal Validations
        Assert.assertEquals(200,resp.getStatusCode());
        Assert.assertEquals(updatedname,resp.jsonPath().get("firstname"));
        Assert.assertEquals(totalPrice,resp.jsonPath().get("totalprice").toString());

        // Validations for matching updated data with previously created data
        Assert.assertNotEquals(totalPrice,createdTotalPrice);
        Assert.assertNotEquals(updatedname,createdFirstName);
    }
}