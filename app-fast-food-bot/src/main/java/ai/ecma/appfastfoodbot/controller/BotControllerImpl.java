package ai.ecma.appfastfoodbot.controller;

import ai.ecma.appfastfoodbot.service.BotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class BotControllerImpl implements BotController{

    private final BotServiceImpl botService;

    @Override
    public void getRequestFrom(Update update) {
       botService.sendMessage(update);
    }

}
