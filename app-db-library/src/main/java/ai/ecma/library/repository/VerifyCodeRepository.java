package ai.ecma.library.repository;

import ai.ecma.library.entity.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, UUID> {

    long countAllByPhoneNumberAndResetIsFalseAndCreatedAtAfter(String phoneNumber, Timestamp after);

    Optional<VerifyCode> findFirstByPhoneNumberAndConfirmedIsFalseAndCreatedAtAfterOrderByCreatedAtDesc(String phoneNumber, Timestamp createdAt);

    Optional<VerifyCode> findFirstByPhoneNumberAndCodeAndConfirmedIsTrueOrderByCreatedAtDesc(String phoneNumber, String code);

}
