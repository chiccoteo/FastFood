package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER)
public interface AttachmentController {

    String ATTACHMENT_CONTROLLER= AppConstant.BASE_PATH_V1+"/attachment";

    @PostMapping
    ApiResult<?> uploadFileToSystem(@RequestParam("file") MultipartFile file);
}
