package ai.ecma.appauthservice.components;

import ai.ecma.library.entity.Role;
import ai.ecma.library.entity.User;
import ai.ecma.library.enums.Language;
import ai.ecma.library.enums.Permission;
import ai.ecma.library.enums.RoleType;
import ai.ecma.library.repository.RoleRepository;
import ai.ecma.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roleList = new ArrayList<>();
        if (initMode.equals("always")) {
            try {
                for (RoleType type : RoleType.values()) {
                    if (!type.equals(RoleType.CUSTOM)) {
                        Set<Permission> permissionSet = Stream.of(Permission.values()).filter(permission -> permission.getRoleTypes().contains(type)).collect(Collectors.toSet());
                        roleList.add(new Role(type.name(), type, permissionSet));
                    }
                }
                roleRepository.saveAll(roleList);

                Optional<Role> adminRole = roleRepository.findByType(RoleType.SUPER_ADMIN);
                User admin = new User("+998993890927", "Admin", passwordEncoder.encode("adminjon"), true, adminRole.get(), Language.EN);
                userRepository.save(admin);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } else if (initMode.equals("never")) {

            for (RoleType value : RoleType.values()) {
                Role role = roleRepository.findByType(value).orElse(new Role(value.name(), value, new HashSet<>()));
                    for (Permission permission : Permission.values()) {
                        if (permission.getRoleTypes().contains(value))
                            role.getPermissions().add(permission);
                    }
                    roleRepository.save(role);

            }
        }
    }
}
