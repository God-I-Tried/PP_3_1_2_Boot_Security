package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findRoleById(Long id);
    Role saveRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Long id);
    List<Role> findAllRolesById(List<Long> asList);
}
