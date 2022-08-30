package mvcproject.java11.crm.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.java11.crm.model.Project;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "project-controller", urlPatterns = { UrlsController.URL_PROJECT_VIEW,
		UrlsController.URL_PROJECT_EDIT, 
		UrlsController.URL_PROJECT_DELETE, 
		UrlsController.URL_PROJECT_ADD, 
		UrlsController.URL_PROJECT_DETAIL})

public class ProjectController extends HttpServlet {

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
		case UrlsController.URL_PROJECT_VIEW:
			processViews(req, resp);
			break;
		case UrlsController.URL_PROJECT_DETAIL:
			processViewDetail(req, resp);
			break;
		case UrlsController.URL_PROJECT_ADD:
			getAdd(req, resp);
			break;
		case UrlsController.URL_PROJECT_EDIT:
			getEdit(req, resp);
			break;
		case UrlsController.URL_PROJECT_DELETE:
			getDelete(req, resp);
			break;
		}
	}

	private void processViewDetail(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String _projectId = req.getParameter("id");
		int projectId = Integer.parseInt(_projectId);
			
		req.setAttribute("project", crmServices.getProjectById(projectId));
		req.setAttribute("taskByProjectId", crmServices.getTaskByProjectId(projectId));
		
		req.getRequestDispatcher(UrlsJSP.URL_PROJECT_DETAIL).forward(req,resp);
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(UrlsJSP.URL_PROJECT_ADD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case UrlsController.URL_PROJECT_EDIT:
			doEdit(req, resp);
			break;
		case UrlsController.URL_PROJECT_ADD:
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

			System.out.println("keyword_search: "+keyword_search);
			System.out.println("get_current_page: "+ get_current_page);
			System.out.println("get_record_on_page: "+ get_record_on_page);
			
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
			if(keyword_search.equals("default") || keyword_search.isEmpty()) {
				keyword_search = "";
			}
			int totalRecord = crmServices.getTotalRecordProject(keyword_search);
			int totalPage = (int) Math.ceil((float) totalRecord / (float) record_on_page);
			int index = (current_page - 1) * record_on_page;

			System.out.println("current_page: " + current_page);
			System.out.println("record_on_page: " + record_on_page);
			System.out.println("totalPage: " + totalPage);
			System.out.println("totalRecord: " + totalRecord);
			System.out.println("index: " + index);
			System.out.println();

			List<Project> projects = crmServices.getProjectByKeyword(keyword_search, index, record_on_page);
			// List<Account> accounts = crmServices.getAllAccount();
			
			req.setAttribute("record_on_page", record_on_page);
			req.setAttribute("totalRecord", totalRecord);
			req.setAttribute("current_page", current_page);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("viewProjects", projects);
			
			req.getRequestDispatcher(UrlsJSP.URL_PROJECT_VIEW).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.valueOf((String) req.getParameter("id"));
			if (crmServices.deleteProject(id) > 0) {
				req.setAttribute("message", "success!");
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_PROJECT_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String project_id = req.getParameter("id");
			System.out.println("getEdit project_id: " + project_id);

			Project project = crmServices.getProjectById(Integer.parseInt(project_id));

			req.setAttribute("projects", project);

			req.getRequestDispatcher(UrlsJSP.URL_PROJECT_EDIT).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) {
		Project project = new Project();
		try {
			// yyyy-mm-dd
			
			project.setName(req.getParameter("name"));
			project.setStart_date(LocalDate.parse(req.getParameter("start_date")));
			project.setEnd_date(LocalDate.parse(req.getParameter("end_date")));
			
			if (crmServices.insertProject(project) > 0) {
				resp.sendRedirect(req.getContextPath() + UrlsController.URL_PROJECT_VIEW);
			} else {
				req.setAttribute("message", "tao tai khoan that bai");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String project_id = req.getParameter("id");
			System.out.println("project_id"+project_id);
			if (project_id != null && !project_id.isEmpty()) {
				Project project = new Project();
				project.setId(Integer.parseInt((String)project_id));
				project.setName(req.getParameter("name"));
				project.setStart_date(LocalDate.parse(req.getParameter("start_date")));
				project.setEnd_date(LocalDate.parse(req.getParameter("end_date")));
				crmServices.updateProject(project);
			}
			
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_PROJECT_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
