package ai.ecma.appfastfoodbot.controller;

import ai.ecma.appfastfoodbot.utils.AppConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping(BotController.BOT_CONTROLLER)
public interface BotController {

    String BOT_CONTROLLER= AppConstant.BATH_PATH_V1;

    @PostMapping("/bot")
    void getRequestFrom(@RequestBody Update update);
}
