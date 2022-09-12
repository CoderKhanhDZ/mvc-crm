package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Project;
import mvcproject.java11.crm.repository.ProjectRepository;

import java.util.List;

public class ProjectServiceImp implements IProjectService {
    private static ProjectRepository projectRepository;
    private static ProjectServiceImp INSTANCE;

    private ProjectServiceImp() {
        projectRepository = new ProjectRepository();
    }

    public static ProjectServiceImp getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ProjectServiceImp();
        }
        return INSTANCE;
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    @Override
    public void deleteProject(int id) {
        projectRepository.deleteProject(id);
    }

    @Override
    public void insertProject(Project project) {
        projectRepository.insertProject(project);
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.getAllProject();
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }

    @Override
    public int getTotalRecordProject(String keyword) {
        if (keyword.equals("default")) {
            keyword = "";
        }
        return projectRepository.getTotalRecordProject(keyword);
    }

    @Override
    public List<Project> getProjectByKeyword(String keyword, int current_page, int record_on_page) {
        int index = (current_page - 1) * record_on_page;

        if (keyword.equals("default")) {
            keyword = "";
        }

        List<Project> projects = projectRepository.getProjectByKeyword(keyword, index, record_on_page);

        return projects;
    }
}
