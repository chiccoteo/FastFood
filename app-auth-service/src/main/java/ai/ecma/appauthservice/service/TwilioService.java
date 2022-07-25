package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.common.MessageService;
import ai.ecma.appauthservice.exception.RestException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Value("${app.twilio.account-sid}")
    private String accountSID;

    @Value("${app.twilio.auth-token}")
    private String authToken;

    @Value("${app.twilio.phone-number}")
    private String myPhoneNumber;

    public void sendSMS(String phoneNumber, String sendMessage) {
        try {
//            Twilio.init(accountSID, authToken);
//            Message message = Message.creator(
//                            new com.twilio.type.PhoneNumber(phoneNumber),
//                            new com.twilio.type.PhoneNumber(myPhoneNumber),
//                            sendMessage)
//                    .create();

            System.out.println("Send SMS FOR " + phoneNumber + ": -> " + sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.conflict(MessageService.getMessage("UNABLE_TO_SEND_SMS"));
        }
    }
}
