package ai.ecma.appfastfoodbot.service;

import ai.ecma.appfastfoodbot.feign.WebhookFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class BotServiceImpl {

    private final WebhookFeign webhookFeign;

    @Value("${app.telegram-bot.token}")
    private String token;

    public void sendMessage(Update update) {
        SendMessage sendMessage = new
                SendMessage(update.getMessage().getChatId().toString(), "Borakansanu ukam");
        webhookFeign.sendMessageToUser(sendMessage, token);
    }
}
