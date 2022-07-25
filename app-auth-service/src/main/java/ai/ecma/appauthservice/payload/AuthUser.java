package ai.ecma.appauthservice.payload;

import ai.ecma.library.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AuthUser implements UserDetails {
    private final User user;

    public AuthUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> permissions = user.getRole().getPermissions().stream().map(Enum::name).collect(Collectors.toSet());
        permissions.add(user.getRole().getType().name());

        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
//
//        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
//            simpleGrantedAuthorityList.add(simpleGrantedAuthority);
//        }
//
//        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
