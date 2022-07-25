package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.service.RabbitMQSenderService;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RabbitMQMessageTest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.BASE_PATH_V1+"/test")
public class TestController {
    private final RabbitMQSenderService rabbitMQSenderService;

    @PostMapping()
    public ApiResult<?> send(@RequestBody RabbitMQMessageTest rabbitMQMessageTest){
         rabbitMQSenderService.send(rabbitMQMessageTest);
         return ApiResult.successResponse("Message send to queue");
    }

}
