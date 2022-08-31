package mvcproject.java11.crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.services.CrmServices;
import mvcproject.java11.crm.urls.UrlsController;
import mvcproject.java11.crm.urls.UrlsJSP;

@WebServlet(name = "auth-controller", urlPatterns = {UrlsController.URL_LOGIN, UrlsController.URL_LOGOUT})
public class AuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case UrlsController.URL_LOGIN:
                req.getRequestDispatcher(UrlsJSP.URL_LOGIN).forward(req, resp);
                break;

            case UrlsController.URL_LOGOUT:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + UrlsController.URL_LOGIN);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UrlsController.URL_LOGIN.equals(req.getServletPath())) {
            processLogin(req, resp);
            }
    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String get_email = req.getParameter("email");
            String get_password = req.getParameter("password");

            System.out.println(get_email);
            System.out.println(get_password);

            Account account = CrmServices.getINSTANCE().checkLogin(get_email, get_password);

            System.out.println(account);
            if (account == null) {
                req.setAttribute("error", "email or password is incorrect!");
                this.doGet(req, resp);
            } else {
                req.getSession().setAttribute("logging", account);
                resp.sendRedirect(req.getContextPath() + UrlsController.URL_HOME);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
