package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Project;

import java.util.List;

public interface IProjectService {

    void updateProject(Project project);

    void deleteProject(int id);

    void insertProject(Project project);

    List<Project> getAllProject();

    Project getProjectById(int id);

    List<Project> getProjectByKeyword(String keyword, int index, int limit);

    int getTotalRecordProject(String keyword);

}
