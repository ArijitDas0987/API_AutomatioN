package CRUD_Operations.Deserialization;

import CRUD_Operations.CreateAPI;
import Payload.payload;
import comBase.Base_Properties;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;

public class Deserialization extends Base_Properties {

    public Payload.payload deserialization(){
        RestAssured.baseURI=loadproperties().getProperty("URI");
        RestAssured.basePath="booking/"+ CreateAPI.bookingid;

        payload payload=get().as(Payload.payload.class);
        return payload;
    }
}
