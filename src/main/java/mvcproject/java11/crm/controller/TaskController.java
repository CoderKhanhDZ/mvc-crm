package mvcproject.java11.crm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.model.Task;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "task-controller", urlPatterns = {UrlsController.URL_TASK_VIEW, UrlsController.URL_TASK_EDIT,
        UrlsController.URL_TASK_ADD, UrlsController.URL_TASK_DELETE})
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
                getViews(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_TASK_EDIT:
                postEdit(req, resp);
                break;

            case UrlsController.URL_TASK_ADD:
                postAdd(req, resp);
                break;

        }

    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("project", crmServices.getAllProject());
        req.setAttribute("account", crmServices.getAllAccount());
        req.getRequestDispatcher(UrlsJSP.URL_TASK_ADD).forward(req, resp);
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
            int totalRecord = crmServices.getTotalRecordTask(keyword_search);
            int totalPage = (int) Math.ceil((float) totalRecord / (float) record_on_page);

            List<Task> tasks = crmServices.getTaskByKeyword(keyword_search, current_page, record_on_page);

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
            int id = Integer.valueOf(req.getParameter("id"));

            crmServices.deleteTask(id);
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

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Task task = getTaskByParemeter(req);
            task.setStatus_id(3); // default khi tao task (chua hoan thanh)

            crmServices.insertTask(task);
            req.setAttribute("message", "tao task thanh cong");

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String task_id = req.getParameter("id");

            Task task = getTaskByParemeter(req);
            task.setStatus_id(Integer.parseInt(req.getParameter("status_id")));

            if (task_id != null && !task_id.isEmpty()) {
                task.setId(Integer.parseInt(task_id));
                crmServices.updateTask(task);
            }
            resp.sendRedirect(req.getContextPath() + UrlsController.URL_TASK_VIEW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Task getTaskByParemeter(HttpServletRequest req) {

        String name = req.getParameter("name");
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");
        String account_id = req.getParameter("account_id");
        String project_id = req.getParameter("project_id");
        return new Task(name, LocalDate.parse(start_date), LocalDate.parse(end_date), Integer.parseInt(account_id), Integer.parseInt(project_id));
    }

}
