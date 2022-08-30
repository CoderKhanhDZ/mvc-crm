package mvcproject.java11.crm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.java11.crm.model.Task;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "task-controller", urlPatterns = { UrlsController.URL_TASK_VIEW, UrlsController.URL_TASK_EDIT,
		UrlsController.URL_TASK_ADD, UrlsController.URL_TASK_DELETE })
public class TaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static CrmServices crmServices;

	@Override
	public void init() throws ServletException {
		super.init();
		crmServices = CrmServices.getINSTANCE();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case UrlsController.URL_TASK_VIEW:
			processViews(req, resp);
			break;
		case UrlsController.URL_TASK_ADD:
			getAdd(req, resp);
			break;
		case UrlsController.URL_TASK_EDIT:
			getEdit(req, resp);
			break;
		case UrlsController.URL_TASK_DELETE:
			getDelete(req, resp);
			break;
		}
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("project", crmServices.getAllProject());
		req.setAttribute("account", crmServices.getAllAccount());
		req.getRequestDispatcher(UrlsJSP.URL_TASK_ADD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case UrlsController.URL_TASK_EDIT:
			doEdit(req, resp);
			break;
		case UrlsController.URL_TASK_ADD:
			doAdd(req, resp);
			break;
		}

	}

	private void processViews(HttpServletRequest req, HttpServletResponse resp) {

		try {
			// System.out.println("keyword_search: " +
			// req.getServletContext().getInitParameter("keyword_search"));
			// System.out.println("current_page: " +
			// req.getServletContext().getInitParameter("current_page"));
			// System.out.println("record_on_page: " +
			// req.getServletContext().getInitParameter("record_on_page"));

			// get cac paremeter tren neu khac null thi su dung
			// nguoc lai = null thi lay cac context parameter

			String keyword_search = req.getParameter("keyword_search");
			String get_current_page = req.getParameter("current_page");
			String get_record_on_page = req.getParameter("record_on_page");

			System.out.println("keyword_search: " + keyword_search);
			System.out.println("get_current_page: " + get_current_page);
			System.out.println("get_record_on_page: " + get_record_on_page);

			if (keyword_search == null || keyword_search.isEmpty()) {
				keyword_search = req.getServletContext().getInitParameter("keyword_search");
			}
			req.setAttribute("keyword_search", keyword_search);

			if (get_current_page == null) {
				get_current_page = req.getServletContext().getInitParameter("current_page");
			}

			if (get_record_on_page == null) {
				get_record_on_page = req.getServletContext().getInitParameter("record_on_page");
			}

			int current_page = Integer.parseInt(get_current_page);
			int record_on_page = Integer.parseInt(get_record_on_page);
			
			if (keyword_search.equals("default") ) {
				keyword_search = "";
			}
			
			int totalPage = (int) Math
					.ceil((float) crmServices.getTotalRecordTask(keyword_search) / (float) record_on_page);
			int totalRecord = crmServices.getTotalRecordTask(keyword_search);
			int index = (current_page - 1) * record_on_page;

			System.out.println("current_page: " + current_page);
			System.out.println("record_on_page: " + record_on_page);
			System.out.println("totalPage: " + totalPage);
			System.out.println("totalRecord: " + totalRecord);
			System.out.println("index: " + index);
			System.out.println();

			List<Task> tasks = crmServices.getTaskByKeyword(keyword_search, index, record_on_page);
			// List<Account> accounts = crmServices.getAllAccount();

			req.setAttribute("record_on_page", record_on_page);
			req.setAttribute("totalRecord", totalRecord);
			req.setAttribute("current_page", current_page);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("viewTasks", tasks);

			req.getRequestDispatcher(UrlsJSP.URL_TASK_VIEW).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.valueOf((String) req.getParameter("id"));
			if (crmServices.deleteTask(id) > 0) {
				req.setAttribute("message", "success!");
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_TASK_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String task_id = req.getParameter("id");
			System.out.println("getEdit task_id: " + task_id);

			Task task = crmServices.getTaskById(Integer.parseInt(task_id));

			req.setAttribute("project", crmServices.getAllProject());
			req.setAttribute("account", crmServices.getAllAccount());
			req.setAttribute("status", crmServices.getAllStatus());
			req.setAttribute("task", task);

			req.getRequestDispatcher(UrlsJSP.URL_TASK_EDIT).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) {
		Task task = new Task();
		try {
			// name, start_date, end_date, account_id, project_id, status_id
			task.setName(req.getParameter("name"));
			task.setStart_date(LocalDate.parse(req.getParameter("start_date")));
			task.setEnd_date(LocalDate.parse(req.getParameter("end_date")));
			task.setAccount_id(Integer.parseInt((String) req.getParameter("account_id")));
			task.setProject_id(Integer.parseInt((String) req.getParameter("project_id")));
			task.setStatus_id(3);

			if (crmServices.insertTask(task) > 0) {
				resp.sendRedirect(req.getContextPath() + UrlsController.URL_TASK_VIEW);
			} else {
				req.setAttribute("message", "tao task that bai");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String task_id = req.getParameter("id");
			System.out.println(task_id);
			if (task_id != null && !task_id.isEmpty()) {
				Task task = new Task();
				task.setId(Integer.parseInt(task_id));
				task.setName(req.getParameter("name"));
				task.setStart_date(LocalDate.parse(req.getParameter("start_date")));
				task.setEnd_date(LocalDate.parse(req.getParameter("end_date")));
				task.setAccount_id(Integer.parseInt((String) req.getParameter("account_id")));
				task.setProject_id(Integer.parseInt((String) req.getParameter("project_id")));
				task.setStatus_id(Integer.parseInt((String) req.getParameter("status_id")));
				crmServices.updateTask(task);
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_TASK_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
