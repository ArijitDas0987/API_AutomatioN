package DefiningRequestBody;

import Payload.Bookingdates;
import Payload.payload;
import Utils.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DefiningJsonBody extends Utility {

    public Map<String, String> mapData = new HashMap<>();

    payload payload=new payload();
    public void getData() {
        mapData.put("checkin", getDate());
        mapData.put("checkout", getCheckOutDate());
        mapData.put("firstname", getFirstName());
        mapData.put("lastname", getLastName());
        mapData.put("totalprice", getTotalPrice());
        mapData.put("depositpaid", getDepositPaid());
        mapData.put("additionalneeds", getAdditionalNeeds());
    }

    public String definingRequestBody() throws JsonProcessingException {
        getData();

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin(mapData.get("checkin"));
        bookingdates.setCheckout(mapData.get("checkout"));

        payload.setFirstname(mapData.get("firstname"));
        payload.setLastname(mapData.get("lastname"));
        payload.setTotalprice(Integer.parseInt(mapData.get("totalprice")));
        payload.setDepositpaid(Boolean.parseBoolean(mapData.get("depositpaid")));
        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(mapData.get("additionalneeds"));


        ObjectMapper mapData = new ObjectMapper();
        String actual_JsonData=mapData.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        return actual_JsonData;
    }

    public void loginCredentials(){
        mapData.put("username",loadproperties().getProperty("username"));
        mapData.put("password",loadproperties().getProperty("password"));
    }

    public void partialUpdation(){
        payload.setFirstname(getFirstName());
        payload.setTotalprice(Integer.parseInt(getTotalPrice()));
        mapData.put("firstname",payload.getFirstname());
        mapData.put("totalprice",String.valueOf(payload.getTotalprice()));
    }

    public String getCheckOutDate(){
        LocalDate date= LocalDate.parse(getDate());

        while(date.isBefore(LocalDate.parse(mapData.get("checkin")))) {
            date=LocalDate.parse(getDate());
        }
        return date.toString();
    }

}
