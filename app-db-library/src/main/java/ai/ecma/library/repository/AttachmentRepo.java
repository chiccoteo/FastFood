package ai.ecma.library.repository;

import ai.ecma.library.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
