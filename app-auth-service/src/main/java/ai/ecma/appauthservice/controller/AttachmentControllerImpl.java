package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.service.AttachmentService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController{

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<?> uploadFileToSystem(@RequestParam("file") MultipartFile file) {
        return attachmentService.uploadFileToSystem(file);
    }
}
