package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Role;
import mvcproject.java11.crm.repository.RoleRepository;

import java.util.List;

public class RoleServiceImp implements IRoleService {

    private static RoleRepository roleRepository;
    private static RoleServiceImp INSTANCE;

    private RoleServiceImp() {
        roleRepository = new RoleRepository();
    }

    public static RoleServiceImp getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new RoleServiceImp();
        }
        return INSTANCE;
    }


    @Override
    public void insertRole(Role role) {
        roleRepository.insertRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.updateRole(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteRole(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.getAllRole();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public int getTotalRecordRole(String keyword) {
        if (keyword.equals("default")) {
            keyword = "";
        }
        return roleRepository.getTotalRecordRole(keyword);
    }

    @Override
    public List<Role> getRoleByKeyword(String keyword, int current_page, int record_on_page) {
        int index = (current_page - 1) * record_on_page;

        if (keyword.equals("default")) {
            keyword = "";
        }

        List<Role> roles = roleRepository.getRoleByKeyWord(keyword, index, record_on_page);


        return roles;

    }
}
