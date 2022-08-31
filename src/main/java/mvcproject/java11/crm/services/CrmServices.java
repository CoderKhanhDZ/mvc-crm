package mvcproject.java11.crm.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public static CrmServices getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new CrmServices();
		}
		return INSTANCE;
	}

	private CrmServices() {
		accountRepository = new AccountRepository();
		roleRepository = new RoleRepository();
		taskRepository = new TaskRepository();
		projectRepository = new ProjectRepository();
		statusRepository = new StatusRepository();
	}

	@Override
	public void deleteAccount(int id) {
		accountRepository.deleteAccount(id);
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
		if (keyword.equals("default")) {
			keyword = "";
		}
		return accountRepository.getTotalRecordAccount(keyword);
	}

	/**
	 * @param keyword        : tu khoa
	 * @param record_on_page : so trang tren page
	 * @param current_page   : trang hien tai
	 * @param index          : vi tri bat dau tren limit (index,record_on_page)
	 */
	@Override
	public List<Account> getAccountByKeyword(String keyword, int record_on_page, int current_page) {

		int index = (current_page - 1) * record_on_page;

		if (keyword.equals("default")) {
			keyword = "";
		}

		List<Account> accounts = accountRepository.getAccountByKeyword(keyword, index, record_on_page);

		// set role name cho account
		for (Account account : accounts) {
			account.setRole_name(getRoleById(account.getRole_id()).getName());
		}
		return accounts;
	}

	@Override
	public void insertAccount(Account account) {

		accountRepository.insertAccount(account);
	}

	@Override
	public void updateAccount(Account account) {
		accountRepository.updateAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		Account account = accountRepository.getAccountById(id);
		account.setRole_name(getRoleById(account.getRole_id()).getName());
		return account;
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
		task.setAccount_name(getAccountById(task.getAccount_id()).getFullname());
		task.setProject_name(getProjectById(task.getProject_id()).getName());
		task.setStatus_name(statusRepository.getStatusById(task.getStatus_id()).getName());

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
			task.setAccount_name(getAccountById(task.getAccount_id()).getFullname());
		}

		return tasks;
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

	@Override
	public List<Project> getProjectByKeyword(String keyword, int current_page, int record_on_page) {
		int index = (current_page - 1) * record_on_page;

		if (keyword.equals("default")) {
			keyword = "";
		}

		List<Project> projects =  projectRepository.getProjectByKeyword(keyword, index, record_on_page);

		return projects;
	}

	public List<Status> getAllStatus() {
		return statusRepository.getAllStatus();
	}

	public Account checkLogin(String email, String password) {

		Account account = accountRepository.getAccountByEmail(email);

		if (account == null) {
			return null;

		}

		if (account.getPassword().equals(password)) {
			return account;
		}

		return null;
	}

	@Override
	public boolean existedByEmail(String email) {
		return accountRepository.existedByEmail(email);
	}

}
