package controller;

import java.io.IOException;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entityManager.UserDB;
import entityManager.RoleDB;
import entity.User;
import entity.Role;
import utils.DBUtil;

@WebServlet({ "/RoleController", "/manage-user-role" })
@MultipartConfig
public class RoleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println("check action RoleController servlet: " + action);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        System.out.println("user: " + user.getUsername());

        request.setAttribute("userId", user.getUserId());

        if (user != null && (user.getRole().getRoleId() >= 2 && user.getRole().getRoleId() <= 5)) {
            if (action != null && action.equals("get-page-edit-user-role")) {
                getPageEditUserRole(request, response);
            } else {
                getUserRolePage(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void getPageEditUserRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = (int) request.getAttribute("userId");
        System.out.println("get page edit role check userId: " + userId);
        request.setAttribute("userId", userId);
        try {
            EntityManager entityManager = DBUtil.getEntityManager();

            UserDB userDB = new UserDB(entityManager);
            User user = userDB.getUserById(userId);

            RoleDB roleDB = new RoleDB(entityManager);
            request.setAttribute("roles", roleDB.getAllRoles()); // Corrected to fetch all roles

            entityManager.close();

            if (user != null) {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user-role.jsp");
                dispatcher.forward(request, response);
            } else {
                System.out.println("User variable is null!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void getUserRolePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = (int) request.getAttribute("userId");
        System.out.println(userId);
        try {
            EntityManager entityManager = null;
            try {
                entityManager = DBUtil.getEntityManager();
                UserDB userDB = new UserDB(entityManager);
                User user = userDB.getUserById(userId);
                request.setAttribute("userId", userId);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/manage-user-role.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (entityManager != null && entityManager.isOpen()) {
                    entityManager.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println("check action RoleController Servlet: " + action);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && (user.getRole().getRoleId() == 2 || user.getRole().getRoleId() == 3
                || user.getRole().getRoleId() == 4 || user.getRole().getRoleId() == 5)) {
            if (action != null && action.equals("edit-user-role")) {
                handleEditUserRole(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void handleEditUserRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        EntityManager entityManager = DBUtil.getEntityManager();
        try {
            String userIdStr = request.getParameter("userId");
            if (userIdStr == null || userIdStr.isEmpty()) {
                throw new NumberFormatException("userId is missing");
            }
            String roleIdStr = request.getParameter("roleId");
            if (roleIdStr == null || roleIdStr.isEmpty()) {
                throw new NumberFormatException("roleId is missing");
            }
            
            int userId = Integer.parseInt(userIdStr);
            int roleId = Integer.parseInt(roleIdStr);

            System.out.println("check utf-8 from jsp: " + roleId);

            UserDB userDB = new UserDB(entityManager);
            User user = userDB.getUserById(userId);

            if (user != null) {
                RoleDB roleDB = new RoleDB(entityManager);
                Role role = roleDB.getRoleById(roleId);
                if (role != null) {
                    user.setRole(role);
                } else {
                    System.out.println("Role not found!");
                }

                if (userDB.updateUser(user)) {
                    request.setAttribute("userId", userId);
                    response.sendRedirect(request.getContextPath() + "/manage-user-role");
                }
            } else {
                System.out.println("User not found!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

}
