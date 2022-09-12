package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Task;

import java.util.List;

public interface ITaskService {

    void updateTask(Task task);

    void deleteTask(int id);

    void insertTask(Task task);

    List<Task> getAllTask();

    Task getTaskById(int id);

    List<Task> getTaskByKeyword(String keyword, int index, int limit);

    int getTotalRecordTask(String keyword);

    List<Task> getTaskByAccountId(int accountId);

    List<Task> getTaskByProjectId(int projectId);
}
