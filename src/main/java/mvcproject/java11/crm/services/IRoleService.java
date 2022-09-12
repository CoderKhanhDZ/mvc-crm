package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Role;

import java.util.List;

public interface IRoleService {
    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(int id);

    List<Role> getAllRole();

    Role getRoleById(int id);

    int getTotalRecordRole(String keyword);

    List<Role> getRoleByKeyword(String keyword, int index, int limit);
}
