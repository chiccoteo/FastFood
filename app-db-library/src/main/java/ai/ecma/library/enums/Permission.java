package ai.ecma.library.enums;

import ai.ecma.library.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Created by Kholmakhmatov on 14/06/2022 !
 **/
@AllArgsConstructor
@Getter
public enum Permission {
    ADD_REGION(Set.of(RoleType.SUPER_ADMIN)),
    EDIT_REGION(Set.of(RoleType.SUPER_ADMIN)),
    DELETE_REGION(Set.of(RoleType.SUPER_ADMIN)),


    //ROLE
    GET_ALl_ROLES(Set.of(RoleType.SUPER_ADMIN)),
    GET_ROLE_BY_ID(Set.of(RoleType.SUPER_ADMIN)),
    ADD_ROLE(Set.of(RoleType.SUPER_ADMIN)),
    EDIT_ROLE(Set.of(RoleType.SUPER_ADMIN)),
    DELETE_ROLE(Set.of(RoleType.SUPER_ADMIN)),
    //EMPLOYEE
    GET_ALl_EMPLOYEES(Set.of(RoleType.SUPER_ADMIN)),
    GET_EMPLOYEE_BY_ID(Set.of(RoleType.SUPER_ADMIN)),
    ADD_EMPLOYEE(Set.of(RoleType.SUPER_ADMIN)),
    EDIT_EMPLOYEE(Set.of(RoleType.SUPER_ADMIN)),
    DELETE_EMPLOYEE(Set.of(RoleType.SUPER_ADMIN)),
    //OFFERTA
    GET_OFFERTA(Set.of(RoleType.SUPER_ADMIN)),
    ADD_OFFERTA(Set.of(RoleType.SUPER_ADMIN)),
    EDIT_OFFERTA(Set.of(RoleType.SUPER_ADMIN)),
    DELETE_OFFERTA(Set.of(RoleType.SUPER_ADMIN)),


    CREATE_ROLE(Set.of(RoleType.SUPER_ADMIN)),
    CREATE_DISTRICT(Set.of(RoleType.SUPER_ADMIN)),


    CREATE_REGION(Set.of(RoleType.RAMZIDDIN)),
    DELETE_DISTRICT(Set.of(RoleType.RAMZIDDIN)),
    dijhc(Set.of(RoleType.RAMZIDDIN))



    ;


    private final Set<RoleType> roleTypes;

}
