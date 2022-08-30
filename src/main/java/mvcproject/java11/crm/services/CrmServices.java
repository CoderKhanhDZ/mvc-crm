package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.model.Project;
import mvcproject.java11.crm.model.Role;
import mvcproject.java11.crm.model.Status;
import mvcproject.java11.crm.model.Task;
import mvcproject.java11.crm.repository.AccountRepository;
import mvcproject.java11.crm.repository.ProjectRepository;
import mvcproject.java11.crm.repository.RoleRepository;
import mvcproject.java11.crm.repository.StatusRepository;
import mvcproject.java11.crm.repository.TaskRepository;

public class CrmServices implements iAccountService, iRoleService, iTaskService, iProjectService {

	private static CrmServices INSTANCE = null;
	private static AccountRepository accountRepository;
	private static RoleRepository roleRepository;
	private static TaskRepository taskRepository;
	private static ProjectRepository projectRepository;
	private static StatusRepository statusRepository;

	private CrmServices() {
		accountRepository = new AccountRepository();
		roleRepository = new RoleRepository();
		taskRepository = new TaskRepository();
		projectRepository = new ProjectRepository();
		statusRepository = new StatusRepository();
	}

	public static CrmServices getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new CrmServices();
		}
		return INSTANCE;
	}

	@Override
	public int deleteAccount(int id) {
		return accountRepository.deleteAccount(id);
	}

	@Override
	public List<Account> getAllAccount() {
		List<Account> accounts = accountRepository.getAllAccount();

		// set role name cho account
		for (Account account : accounts) {
			account.setRole_name(getRoleById(account.getRole_id()).getName());
		}
		return accounts;
	}

	@Override
	public int getTotalRecordAccount(String keyword) {
		return accountRepository.getTotalRecordAccount(keyword);
	}

	@Override
	public List<Account> getAccountByKeyword(String keyword, int index, int limit) {
		List<Account> accounts = accountRepository.getAccountByKeyword(keyword, index, limit);

		// set role name cho account
		for (Account account : accounts) {
			account.setRole_name(getRoleById(account.getRole_id()).getName());
		}
		return accounts;
	}

	@Override
	public int insertAccount(Account account) {
		return accountRepository.insertAccount(account);
	}

	@Override
	public int updateAccount(Account account) {
		return accountRepository.updateAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		Account account = accountRepository.getAccountById(id);
		account.setRole_name(getRoleById(account.getRole_id()).getName());
		return account;
	}

	@Override
	public int insertRole(Role role) {
		return roleRepository.insertRole(role);
	}

	@Override
	public int updateRole(Role role) {
		return roleRepository.updateRole(role);
	}

	@Override
	public int deleteRole(int id) {
		return roleRepository.deleteRole(id);
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
	public int updateTask(Task task) {
		return taskRepository.updateTask(task);
	}

	@Override
	public int deleteTask(int id) {
		return taskRepository.deleteTask(id);
	}

	@Override
	public int insertTask(Task task) {
		return taskRepository.insertTask(task);
	}

	@Override
	public List<Task> getAllTask() {
		return taskRepository.getAllTask();
	}

	@Override
	public Task getTaskById(int id) {
		Task task = taskRepository.getTaskById(id);
		task.setAccount_name(getAccountById(task.getAccount_id()).getFullname());
		task.setProject_name(getProjectById(task.getProject_id()).getName());
		task.setStatus_name(statusRepository.getStatusById(task.getStatus_id()).getName());

		return task;
	}

	@Override
	public List<Task> getTaskByKeyword(String keyword, int index, int limit) {
		List<Task> tasks = taskRepository.getByKeyword(keyword, index, limit);

		// set role name cho account
		for (Task task : tasks) {
			task.setAccount_name(getAccountById(task.getAccount_id()).getFullname());
		}
		for (Task task : tasks) {
			task.setProject_name(getProjectById(task.getProject_id()).getName());
		}
		for (Task task : tasks) {
			task.setStatus_name(statusRepository.getStatusById(task.getStatus_id()).getName());
		}
		return tasks;
	}

	@Override
	public int getTotalRecordTask(String keyword) {
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
			task.setAccount_name(getAccountById(task.getAccount_id()).getFullname());
		}

		return tasks;
	}

	@Override
	public int updateProject(Project project) {
		return projectRepository.updateProject(project);
	}

	@Override
	public int deleteProject(int id) {
		return projectRepository.deleteProject(id);
	}

	@Override
	public int insertProject(Project project) {
		return projectRepository.insertProject(project);
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
		return projectRepository.getTotalRecordProject(keyword);
	}

	@Override
	public int getTotalRecordRole(String keyword) {
		return roleRepository.getTotalRecordRole(keyword);
	}

	@Override
	public List<Role> getRoleByKeyword(String keyword, int index, int limit) {
		return roleRepository.getRoleByKeyWord(keyword, index, limit);
	}

	@Override
	public List<Project> getProjectByKeyword(String keyword, int index, int limit) {
		return projectRepository.getProjectByKeyword(keyword, index, limit);
	}

	public List<Status> getAllStatus() {
		return statusRepository.getAllStatus();
	}

	public Account checkLogin(String email, String password) {
		
		Account account = accountRepository.getAccountByEmail(email);
		
		if (account == null) {
			return null;

		}
		
		if(account.getPassword().equals(password)) {
			return account;
		}
		
		return null;
	}

}
