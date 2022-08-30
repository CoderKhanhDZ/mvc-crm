package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.model.Project;
import mvcproject.java11.crm.model.Task;

public interface iProjectService {
	
	 int updateProject(Project project);
	 int deleteProject(int id);
	 int insertProject(Project project) ;
	 List<Project> getAllProject();
	 Project getProjectById(int id);
	 List<Project> getProjectByKeyword(String keyword, int index, int limit);
	 int getTotalRecordProject(String keyword);
	 
}
