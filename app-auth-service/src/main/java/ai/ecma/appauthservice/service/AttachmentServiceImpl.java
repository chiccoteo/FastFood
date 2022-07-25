package ai.ecma.appauthservice.service;

import ai.ecma.library.entity.Attachment;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.AttachmentRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private static final String uploadDirectory = "uploads";

    private final AttachmentRepo attachmentRepo;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDirectory));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    @SneakyThrows
    @Override
    public ApiResult<?> uploadFileToSystem(@RequestParam("file") MultipartFile file) {
        if (file != null) {
            Attachment attachment = new Attachment();
            String originalFilename = file.getOriginalFilename();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());

            // Generate file name for system
            String[] strings = originalFilename.split("\\.");
            String name = UUID.randomUUID() + "." + strings[strings.length - 1];
            attachment.setFileNameInSystem(name);

            // Saving file fields to DB
            Attachment savedFile = attachmentRepo.save(attachment);

            // Saving file to System
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);
            return ApiResult.successResponse("Successfully saved. File's id : " + savedFile.getId());
        }
        return ApiResult.errorResponse("File not saved");
    }
}
