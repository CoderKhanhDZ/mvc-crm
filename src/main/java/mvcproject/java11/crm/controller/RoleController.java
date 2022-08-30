package mvcproject.java11.crm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.java11.crm.model.Role;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "role-controller", urlPatterns = { UrlsController.URL_ROLE_VIEW,
		UrlsController.URL_ROLE_EDIT, 
		UrlsController.URL_ROLE_ADD, 
		UrlsController.URL_ROLE_DELETE})

public class RoleController extends HttpServlet {

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
		case UrlsController.URL_ROLE_VIEW:
			processViews(req, resp);
			break;
		case UrlsController.URL_ROLE_ADD:
			getAdd(req, resp);
			break;
		case UrlsController.URL_ROLE_EDIT:
			getEdit(req, resp);
			break;
		case UrlsController.URL_ROLE_DELETE:
			getDelete(req, resp);
			break;
		}
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(UrlsJSP.URL_ROLE_ADD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case UrlsController.URL_ROLE_EDIT:
			doEdit(req, resp);
			break;
		case UrlsController.URL_ROLE_ADD:
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
			
			if (keyword_search == null  || keyword_search.isEmpty()) {
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
			if(keyword_search.equals("default")) {
				keyword_search = "";
			}
			int totalRecord = crmServices.getTotalRecordRole(keyword_search);
			int totalPage = (int) Math
					.ceil((float) totalRecord / (float) record_on_page);
			int index = (current_page - 1) * record_on_page;

			System.out.println("current_page: " + current_page);
			System.out.println("record_on_page: " + record_on_page);
			System.out.println("totalPage: " + totalPage);
			System.out.println("totalRecord: " + totalRecord);
			System.out.println("index: " + index);
			System.out.println();

			List<Role> roles = crmServices.getRoleByKeyword(keyword_search, index, record_on_page);
			// List<Account> accounts = crmServices.getAllAccount();
			
			req.setAttribute("record_on_page", record_on_page);
			req.setAttribute("totalRecord", totalRecord);
			req.setAttribute("current_page", current_page);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("viewRoles", roles);
			
			req.getRequestDispatcher(UrlsJSP.URL_ROLE_VIEW).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.valueOf((String) req.getParameter("id"));
			if (crmServices.deleteRole(id) > 0) {
				req.setAttribute("message", "success!");
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_ROLE_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String role_id = req.getParameter("id");
			System.out.println("getEdit role_id: " + role_id);

			Role role = crmServices.getRoleById(Integer.parseInt(role_id));

			req.setAttribute("roles", role);

			req.getRequestDispatcher(UrlsJSP.URL_ROLE_EDIT).include(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) {
		Role role = new Role();
		try {
			role.setName(req.getParameter("name"));
			role.setDescription(req.getParameter("description"));
			
			if (crmServices.insertRole(role) > 0) {
				resp.sendRedirect(req.getContextPath() + UrlsController.URL_ROLE_VIEW);
			} else {
				req.setAttribute("message", "tao role that bai");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String role_id = req.getParameter("id");
			System.out.println(role_id);
			if (role_id != null && !role_id.isEmpty()) {
				Role role = new Role();
				role.setId(Integer.parseInt(role_id));
				role.setName(req.getParameter("name"));
				role.setDescription(req.getParameter("description"));
				
				crmServices.updateRole(role);
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_ROLE_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
