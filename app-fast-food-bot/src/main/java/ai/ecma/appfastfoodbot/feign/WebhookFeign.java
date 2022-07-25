package ai.ecma.appfastfoodbot.feign;

import ai.ecma.appfastfoodbot.payload.WebhookResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@FeignClient(url = "https://api.telegram.org",name = "TelegramFeign")
public interface WebhookFeign {

    @GetMapping("/bot{token}/setWebhook")
    WebhookResult connectWebhook(@PathVariable String token, @RequestParam String url);

    @PostMapping("/bot{token}/sendMessage")
    Object sendMessageToUser(@RequestBody SendMessage sendMessage, @PathVariable String token);

    //https://api.telegram.org/bot5414547306:AAFbtLdHX3ftiGKR2ZKJZWwI1yhhX7NojBU/setWebhook?
    // url=https://60d1-178-218-201-90.ngrok.io/bot
}
