package mvcproject.java11.crm.controller;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.services.AccountServiceImp;
import mvcproject.java11.crm.services.RoleServiceImp;
import mvcproject.java11.crm.services.TaskServiceImp;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "account-controller", urlPatterns = {UrlsController.URL_ACCOUNT_VIEW,
        UrlsController.URL_ACCOUNT_EDIT, UrlsController.URL_ACCOUNT_DELETE, UrlsController.URL_ACCOUNT_ADD,
        UrlsController.URL_ACCOUNT_DETAIL})
public class AccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_ACCOUNT_VIEW:
                getViews(req, resp);
                break;

            case UrlsController.URL_ACCOUNT_DETAIL:
                getViewDetail(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_ACCOUNT_EDIT:
                postEdit(req, resp);
                break;

            case UrlsController.URL_ACCOUNT_ADD:
                postAdd(req, resp);
                break;

        }

    }

    private void getViewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _accountId = req.getParameter("id");
        int accountId = Integer.parseInt(_accountId);

        req.setAttribute("account", AccountServiceImp.getInstance().getAccountById(accountId));
        req.setAttribute("taskByAccountId", TaskServiceImp.getInstance().getTaskByAccountId(accountId));

        req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_DETAIL).forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role", RoleServiceImp.getInstance().getAllRole());
        req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_ADD).forward(req, resp);
    }

    /**
     * phan trang voi keyword-search, record-on-page va current-page
     */
    private void getViews(HttpServletRequest req, HttpServletResponse resp) {

        try {

            String _current_page = req.getParameter("current_page");
            String _record_on_page = req.getParameter("record_on_page");
            String keyword_search = req.getParameter("keyword_search");


            if (_current_page == null) {
                _current_page = req.getServletContext().getInitParameter("current_page");
            }

            if (_record_on_page == null) {
                _record_on_page = req.getServletContext().getInitParameter("record_on_page");
            }

            if (keyword_search == null || keyword_search.isEmpty()) {
                keyword_search = req.getServletContext().getInitParameter("keyword_search");
            }


            int current_page = Integer.parseInt(_current_page);
            int record_on_page = Integer.parseInt(_record_on_page);
            int totalRecord = AccountServiceImp.getInstance().getTotalRecordAccount(keyword_search);
            int totalPage = (int) Math.ceil((float) totalRecord / (float) record_on_page);

            List<Account> accounts = AccountServiceImp.getInstance().getAccountByKeyword(keyword_search, record_on_page, current_page);

            req.setAttribute("keyword_search", keyword_search);
            req.setAttribute("current_page", current_page);
            req.setAttribute("record_on_page", record_on_page);
            req.setAttribute("totalPage", totalPage);
            req.setAttribute("viewsAccount", accounts);

            req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_VIEW).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.valueOf(req.getParameter("id"));

            AccountServiceImp.getInstance().deleteAccount(id);
            req.setAttribute("message", "success!");

            resp.sendRedirect(req.getContextPath() + UrlsController.URL_ACCOUNT_VIEW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String account_id = req.getParameter("id");
            System.out.println("getEdit account_id: " + account_id);

            Account account = AccountServiceImp.getInstance().getAccountById(Integer.parseInt(account_id));

            req.setAttribute("role", RoleServiceImp.getInstance().getAllRole());
            req.setAttribute("accountrs", account);

            req.getRequestDispatcher(UrlsJSP.URL_ACCOUNT_EDIT).include(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) {

        try {

            Account account = getAccountByParemeter(req);

            if (AccountServiceImp.getInstance().existedByEmail(account.getEmail())) {
                req.setAttribute("message", "Email đã tồn tại");
            } else {
                AccountServiceImp.getInstance().insertAccount(account);
                req.setAttribute("message", "tao tài khoản thành công");
            }

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String account_id = req.getParameter("id");
            Account account = getAccountByParemeter(req);

            if (account_id != null && !account_id.isEmpty()) {

                account.setId(Integer.parseInt(account_id));
                AccountServiceImp.getInstance().updateAccount(account);
                req.setAttribute("message", "update thanh cong");

            } else {
                req.setAttribute("message", "update that bai");
            }

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Account getAccountByParemeter(HttpServletRequest req) {

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String role_id = req.getParameter("role_id");
        return new Account(email, password, fullname, phone, address, Integer.parseInt(role_id));
    }
}
