package ai.ecma.appauthservice.utils;

import java.util.Random;

public class CommonUtils {
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static String generateVerificationCodeMessage(String code) {
        return "Sizning tasdiqlash kodingiz: " + code;
    }
}
