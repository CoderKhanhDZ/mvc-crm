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

@WebServlet(name = "role-controller", urlPatterns = {UrlsController.URL_ROLE_VIEW, UrlsController.URL_ROLE_EDIT,
        UrlsController.URL_ROLE_ADD, UrlsController.URL_ROLE_DELETE})

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
                getViews(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_ROLE_EDIT:
                postEdit(req, resp);
                break;
            case UrlsController.URL_ROLE_ADD:
                postAdd(req, resp);
                break;

        }

    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlsJSP.URL_ROLE_ADD).forward(req, resp);
    }

    private void getViews(HttpServletRequest req, HttpServletResponse resp) {

        try {

            String keyword_search = req.getParameter("keyword_search");
            String _current_page = req.getParameter("current_page");
            String _record_on_page = req.getParameter("record_on_page");

            if (keyword_search == null || keyword_search.isEmpty()) {
                keyword_search = req.getServletContext().getInitParameter("keyword_search");
            }

            if (_current_page == null) {
                _current_page = req.getServletContext().getInitParameter("current_page");
            }

            if (_record_on_page == null) {
                _record_on_page = req.getServletContext().getInitParameter("record_on_page");
            }

            int current_page = Integer.parseInt(_current_page);
            int record_on_page = Integer.parseInt(_record_on_page);
            int totalRecord = crmServices.getTotalRecordRole(keyword_search);
            int totalPage = (int) Math.ceil((float) totalRecord / (float) record_on_page);

            List<Role> roles = crmServices.getRoleByKeyword(keyword_search, current_page, record_on_page);

            req.setAttribute("keyword_search", keyword_search);
            req.setAttribute("record_on_page", record_on_page);
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
            int id = Integer.valueOf(req.getParameter("id"));

            crmServices.deleteRole(id);
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

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) {
        Role role = new Role();
        try {
            role.setName(req.getParameter("name"));
            role.setDescription(req.getParameter("description"));

            crmServices.insertRole(role);
            req.setAttribute("message", "tao role thanh cong");

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String role_id = req.getParameter("id");
            Role role = new Role();
            role.setName(req.getParameter("name"));
            role.setDescription(req.getParameter("description"));

            if (role_id != null && !role_id.isEmpty()) {

                role.setId(Integer.parseInt(role_id));
                crmServices.updateRole(role);
                req.setAttribute("message", "edit role thanh cong");

            } else {
                req.setAttribute("message", "edit role that bai");
            }

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
