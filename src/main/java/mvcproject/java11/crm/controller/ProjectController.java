package mvcproject.java11.crm.controller;

import mvcproject.java11.crm.model.Project;
import mvcproject.java11.crm.services.ProjectServiceImp;
import mvcproject.java11.crm.services.TaskServiceImp;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "project-controller", urlPatterns = {UrlsController.URL_PROJECT_VIEW,
        UrlsController.URL_PROJECT_EDIT, UrlsController.URL_PROJECT_DELETE, UrlsController.URL_PROJECT_ADD,
        UrlsController.URL_PROJECT_DETAIL})

public class ProjectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_PROJECT_VIEW:
                getViews(req, resp);
                break;

            case UrlsController.URL_PROJECT_DETAIL:
                getViewDetail(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_PROJECT_EDIT:
                postEdit(req, resp);
                break;

            case UrlsController.URL_PROJECT_ADD:
                postAdd(req, resp);
                break;

        }

    }

    private void getViewDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String _projectId = req.getParameter("id");
        int projectId = Integer.parseInt(_projectId);

        req.setAttribute("project", ProjectServiceImp.getInstance().getProjectById(projectId));
        req.setAttribute("taskByProjectId", TaskServiceImp.getInstance().getTaskByProjectId(projectId));

        req.getRequestDispatcher(UrlsJSP.URL_PROJECT_DETAIL).forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlsJSP.URL_PROJECT_ADD).forward(req, resp);
    }


    private void getViews(HttpServletRequest req, HttpServletResponse resp) {

        try {


            String keyword_search = req.getParameter("keyword_search");
            String get_current_page = req.getParameter("current_page");
            String get_record_on_page = req.getParameter("record_on_page");

            if (keyword_search == null || keyword_search.isEmpty()) {
                keyword_search = req.getServletContext().getInitParameter("keyword_search");
            }

            if (get_current_page == null) {
                get_current_page = req.getServletContext().getInitParameter("current_page");
            }

            if (get_record_on_page == null) {
                get_record_on_page = req.getServletContext().getInitParameter("record_on_page");
            }

            int current_page = Integer.parseInt(get_current_page);
            int record_on_page = Integer.parseInt(get_record_on_page);
            int totalRecord = ProjectServiceImp.getInstance().getTotalRecordProject(keyword_search);
            int totalPage = (int) Math.ceil((float) totalRecord / (float) record_on_page);

            List<Project> projects = ProjectServiceImp.getInstance().getProjectByKeyword(keyword_search, current_page, record_on_page);

            req.setAttribute("keyword_search", keyword_search);
            req.setAttribute("record_on_page", record_on_page);
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
            int id = Integer.valueOf(req.getParameter("id"));
            ProjectServiceImp.getInstance().deleteProject(id);
            resp.sendRedirect(req.getContextPath() + UrlsController.URL_PROJECT_VIEW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String project_id = req.getParameter("id");
            System.out.println("getEdit project_id: " + project_id);

            Project project = ProjectServiceImp.getInstance().getProjectById(Integer.parseInt(project_id));

            req.setAttribute("projects", project);

            req.getRequestDispatcher(UrlsJSP.URL_PROJECT_EDIT).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) {
        Project project = new Project();
        try {

            project.setName(req.getParameter("name"));
            project.setStart_date(LocalDate.parse(req.getParameter("start_date")));
            project.setEnd_date(LocalDate.parse(req.getParameter("end_date")));

            ProjectServiceImp.getInstance().insertProject(project);
            req.setAttribute("message", "tao project thanh cong");

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postEdit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String project_id = req.getParameter("id");
            Project project = new Project();
            project.setName(req.getParameter("name"));
            project.setStart_date(LocalDate.parse(req.getParameter("start_date")));
            project.setEnd_date(LocalDate.parse(req.getParameter("end_date")));

            if (project_id != null && !project_id.isEmpty()) {

                project.setId(Integer.parseInt(project_id));
                ProjectServiceImp.getInstance().updateProject(project);
                req.setAttribute("message", "edit project thanh cong");

            } else {
                req.setAttribute("message", "edit project that bai");
            }

            this.doGet(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
