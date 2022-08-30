package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Role;

public interface iRoleService {
	 int insertRole(Role role) ;
	 int updateRole(Role role);
	 int deleteRole(int id);
	 List<Role> getAllRole();
	 Role getRoleById(int id);
	 int getTotalRecordRole(String keyword);
	 List<Role> getRoleByKeyword(String keyword, int index, int limit);
}
