package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.model.Task;

public interface iTaskService {
	
	int updateTask(Task task);
	int deleteTask(int id);
	int insertTask(Task task);
	List<Task> getAllTask();
	Task getTaskById(int id);
	List<Task> getTaskByKeyword(String keyword, int index, int limit);
	int getTotalRecordTask(String keyword);
	List<Task> getTaskByAccountId(int accountId);
	List<Task> getTaskByProjectId(int projectId);
}
