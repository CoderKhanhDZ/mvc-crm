package mvcproject.java11.crm.model;

import java.time.LocalDate;

public class Task {
	
	private int id;
	private String name;
	private LocalDate start_date;
	private LocalDate end_date;
	private int account_id;
	private int project_id;
	private int status_id;
	private String account_name;
	private String project_name;
	private String status_name;
	
	
	public Task() {
		
	}
	
	public Task(String name, LocalDate start_date, LocalDate end_date, int account_id, int project_id) {
		super();
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.account_id = account_id;
		this.project_id = project_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	
}
