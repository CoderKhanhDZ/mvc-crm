package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Task;
import mvcproject.java11.crm.repository.TaskRepository;

import java.util.List;

public class TaskServiceImp implements ITaskService {

    private static TaskRepository taskRepository;
    private static TaskServiceImp INSTANCE;

    private TaskServiceImp() {
        taskRepository = new TaskRepository();
    }

    public static TaskServiceImp getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new TaskServiceImp();
        }
        return INSTANCE;
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public void insertTask(Task task) {
        taskRepository.insertTask(task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskRepository.getTaskById(id);
        task.setAccount_name(AccountServiceImp.getInstance().getAccountById(task.getAccount_id()).getFullname());
        task.setProject_name(ProjectServiceImp.getInstance().getProjectById(task.getProject_id()).getName());
        task.setStatus_name(StatusServiceImp.getInstance().getStatusById(task.getStatus_id()).getName());

        return task;
    }

    @Override
    public List<Task> getTaskByKeyword(String keyword, int current_page, int record_on_page) {
        int index = (current_page - 1) * record_on_page;

        if (keyword.equals("default")) {
            keyword = "";
        }

        List<Task> tasks = taskRepository.getTaskByKeyword(keyword, index, record_on_page);

        // set role name cho account
        for (Task task : tasks) {
            task.setAccount_name(AccountServiceImp.getInstance().getAccountById(task.getAccount_id()).getFullname());
        }
        for (Task task : tasks) {
            task.setProject_name(ProjectServiceImp.getInstance().getProjectById(task.getProject_id()).getName());
        }
        for (Task task : tasks) {
            task.setStatus_name(StatusServiceImp.getInstance().getStatusById(task.getStatus_id()).getName());
        }

        return tasks;
    }

    @Override
    public int getTotalRecordTask(String keyword) {
        if (keyword.equals("default")) {
            keyword = "";
        }
        return taskRepository.getTotalRecordTask(keyword);
    }

    @Override
    public List<Task> getTaskByAccountId(int accountId) {
        return taskRepository.getTaskByAccountId(accountId);
    }

    @Override
    public List<Task> getTaskByProjectId(int projectId) {
        List<Task> tasks = taskRepository.getTaskByProjectId(projectId);

        for (Task task : tasks) {
            task.setAccount_name(AccountServiceImp.getInstance().getAccountById(task.getAccount_id()).getFullname());
        }

        return tasks;
    }
}
