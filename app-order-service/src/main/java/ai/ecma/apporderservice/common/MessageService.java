package ai.ecma.apporderservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private static MessageSource messageSource;

    @Autowired
    public void something(MessageSource messageSource) {
        MessageService.messageSource = messageSource;
    }

    public static String getMessage(String key) {
        return messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
    }
}
