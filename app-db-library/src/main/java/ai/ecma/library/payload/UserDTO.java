package ai.ecma.library.payload;

import ai.ecma.library.entity.Address;
import ai.ecma.library.entity.Attachment;
import ai.ecma.library.entity.District;
import ai.ecma.library.entity.Role;
import ai.ecma.library.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private UUID id;

    private Language language;

    private String phoneNumber;

    private String extraPhoneNumber;

    private String firstName;

    private String lastName;

    private boolean online;

    private boolean enabled;

    private UUID photoId;

    private RoleDTO role;

    private UUID districtId;

}
