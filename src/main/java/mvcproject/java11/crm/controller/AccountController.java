package mvcproject.java11.crm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "account-controller", urlPatterns = { UrlsController.URL_ACCOUNT_VIEW,
		UrlsController.URL_ACCOUNT_EDIT, 
		UrlsController.URL_ACCOUNT_DELETE, 
		UrlsController.URL_ACCOUNT_ADD, 
		UrlsController.URL_ACCOUNT_DETAIL})
public class AccountController extends HttpServlet {

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
		case UrlsController.URL_ACCOUNT_VIEW:
			processViews(req, resp);
			break;
		case UrlsController.URL_ACCOUNT_DETAIL:
			processViewDetail(req, resp);
			break;
		case UrlsController.URL_ACCOUNT_ADD:
			getAdd(req, resp);
			break;
		case UrlsController.URL_ACCOUNT_EDIT:
			getEdit(req, resp);
			break;
		case UrlsController.URL_ACCOUNT_DELETE:
			getDelete(req, resp);
			break;
		}
	}

	private void processViewDetail(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String _accountId = req.getParameter("id");
		int accountId = Integer.parseInt(_accountId);
			
		req.setAttribute("account", crmServices.getAccountById(accountId));
		req.setAttribute("taskByAccountId", crmServices.getTaskByAccountId(accountId));
		
		req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_DETAIL).forward(req,resp);
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("role", crmServices.getAllRole());
		req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_ADD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case UrlsController.URL_ACCOUNT_EDIT:
			doEdit(req, resp);
			break;
		case UrlsController.URL_ACCOUNT_ADD:
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

			String get_current_page = req.getParameter("current_page");
			String get_record_on_page = req.getParameter("record_on_page");
			String keyword_search = req.getParameter("keyword_search");

			System.out.println("keyword_search: "+keyword_search);
			System.out.println("get_current_page: "+ get_current_page);
			System.out.println("get_record_on_page: "+ get_record_on_page);
			

			if (get_current_page == null) {
				get_current_page = req.getServletContext().getInitParameter("current_page");
			}

			if (get_record_on_page == null) {
				get_record_on_page = req.getServletContext().getInitParameter("record_on_page");
			}
			
			if (keyword_search == null || keyword_search.isEmpty()) {
				keyword_search = req.getServletContext().getInitParameter("keyword_search");
			}
			req.setAttribute("keyword_search", keyword_search);

			int current_page = Integer.parseInt(get_current_page);
			int record_on_page = Integer.parseInt(get_record_on_page);
			
			if(keyword_search.equals("default")) {
				keyword_search = "";
			}
			int totalRecord = crmServices.getTotalRecordAccount(keyword_search);
			int totalPage = (int) Math
					.ceil((float) totalRecord / (float) record_on_page);
			int index = (current_page - 1) * record_on_page;

			System.out.println("current_page: " + current_page);
			System.out.println("record_on_page: " + record_on_page);
			System.out.println("totalPage: " + totalPage);
			System.out.println("totalRecord: " + totalRecord);
			System.out.println("index: " + index);
			System.out.println();

			List<Account> accounts = crmServices.getAccountByKeyword(keyword_search, index, record_on_page);
			// List<Account> accounts = crmServices.getAllAccount();
			
			req.setAttribute("record_on_page", record_on_page);
			req.setAttribute("totalRecord", totalRecord);
			req.setAttribute("current_page", current_page);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("viewsAccount", accounts);
			
			
			
			req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_VIEW).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.valueOf((String) req.getParameter("id"));
			if (crmServices.deleteAccount(id) > 0) {
				req.setAttribute("message", "success!");
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_ACCOUNT_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String account_id = req.getParameter("id");
			System.out.println("getEdit account_id: " + account_id);

			Account account = crmServices.getAccountById(Integer.parseInt(account_id));

			req.setAttribute("role", crmServices.getAllRole());
			req.setAttribute("accountrs", account);

			req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_EDIT).include(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) {
		Account account = new Account();
		try {
			account.setFullname(req.getParameter("fullname"));
			account.setEmail(req.getParameter("email"));
			account.setPassword(req.getParameter("password"));
			account.setPhone(req.getParameter("phone"));
			account.setAddress(req.getParameter("address"));
			account.setRole_id(Integer.parseInt((String) req.getParameter("role_id")));
			if (crmServices.insertAccount(account) > 0) {
				resp.sendRedirect(req.getContextPath() + UrlsController.URL_ACCOUNT_VIEW);
			} else {
				req.setAttribute("message", "tao tai khoan that bai");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String account_id = req.getParameter("id");
			System.out.println(account_id);
			if (account_id != null && !account_id.isEmpty()) {
				Account account = new Account();
				account.setId(Integer.parseInt(account_id));
				account.setFullname(req.getParameter("fullname"));
				account.setEmail(req.getParameter("email"));
				account.setPassword(req.getParameter("password"));
				account.setPhone(req.getParameter("phone"));
				account.setAddress(req.getParameter("address"));
				account.setRole_id(Integer.parseInt((String) req.getParameter("role_id")));
				crmServices.updateAccount(account);
			}
			resp.sendRedirect(req.getContextPath() + UrlsController.URL_ACCOUNT_VIEW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
