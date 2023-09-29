package CRUD_Operations;

import DefiningRequestBody.DefiningJsonBody;
import comBase.Base_Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class LoginAPI extends Base_Properties {

    static Logger log = Logger.getLogger(LoginAPI.class);
    public static String token=null;
    DefiningJsonBody jsonBody=new DefiningJsonBody();


    @Test
    public void login(){

        log.info(" Logging into Account ");

        jsonBody.loginCredentials();
        RestAssured.baseURI=loadproperties().getProperty("URI");
        RestAssured.basePath="auth";

        // Hitting API with POST request.
        Response response=given().contentType("application/json").body(jsonBody.mapData).when().post().then().extract().response();
        token=response.jsonPath().getString("token");
        response.then().log().all();

        Assert.assertEquals(200,response.getStatusCode(),"Status code not matched , Something wrong implementation. ");

        log.info(" Logged in ");
    }
}
