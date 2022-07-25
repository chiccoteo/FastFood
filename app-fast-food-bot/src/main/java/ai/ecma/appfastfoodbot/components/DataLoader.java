package ai.ecma.appfastfoodbot.components;

import ai.ecma.appfastfoodbot.feign.WebhookFeign;
import ai.ecma.appfastfoodbot.payload.WebhookResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final WebhookFeign webhookFeign;

    @Value("${app.telegram-bot.token}")
    private String token;

    @Value("${app.telegram-bot.url}")
    private String url;


    @Override
    public void run(String... args) throws Exception {
        try {

            WebhookResult webhookResult = webhookFeign.connectWebhook(token, url);
            if (!webhookResult.isOk()) {
                System.exit(-1);
            }
            System.err.println(webhookResult.getDescription());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
