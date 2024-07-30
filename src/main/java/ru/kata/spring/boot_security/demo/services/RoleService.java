package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role saveRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Long id);
    List<Role> findAllById(List<Long> asList);
}
