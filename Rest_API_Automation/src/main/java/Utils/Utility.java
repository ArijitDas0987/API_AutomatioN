package Utils;

import comBase.Base_Properties;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Utility extends Base_Properties {

    public String getFirstName(){
        String name=loadproperties().getProperty("firstname")+ RandomUtils.nextInt(1000,10000);
        return name;
    }

    public String getLastName(){
        String name=loadproperties().getProperty("lastname")+ RandomUtils.nextInt(1000,10000);
        return name;
    }

    public String getTotalPrice(){
        int name=RandomUtils.nextInt(3000,500000);
        return Integer.toString(name);
    }

    public String getDepositPaid(){
        Boolean name=RandomUtils.nextBoolean();
        return name.toString();
    }

    public String getAdditionalNeeds(){
        String name=loadproperties().getProperty("additionalneeds")+ RandomUtils.nextInt(1000,10000);
        return name;
    }
    public String getDate() {
        int hundredYears = 100 * 365;
        LocalDate date= LocalDate.ofEpochDay(ThreadLocalRandom
                .current().nextInt(-hundredYears, hundredYears));
        return date.toString();
    }
}
