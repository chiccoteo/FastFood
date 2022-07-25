package ai.ecma.appauthservice.service;

import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    ApiResult<?> uploadFileToSystem(@RequestParam("file") MultipartFile file);
}
