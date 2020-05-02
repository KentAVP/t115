package t113;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet("/")
public class LoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    Properties properties = new Properties();
    FileInputStream fis;
    String type;


    {
        try {
            fis = new FileInputStream("C:\\Users\\alexe\\IdeaProjects\\serv\\src\\main\\java\\t113\\daoconfig.properties");
            properties.load(fis);
            type = properties.getProperty("daotype");
        } catch (Exception e) {
            System.out.println("отсутствует файл конфигурации");
        }
    }

    public void init() {
        userDao = UserDAOFactory.getUserDAO(type);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);




        String path = "/admin";

        try {
            User us = userDao.checkRole(loginBean);
            if (userDao.isValidate(loginBean)) {
                if(us.getRole().equals("admin")){
                    session.setAttribute("admin", "yes");
                    response.sendRedirect(path);
                }else{
                    session.removeAttribute("admin");
                    session.setAttribute("login", "yes");
                    response.sendRedirect("loginsuccess.jsp");
                }
            } else {
                session.invalidate();
                response.sendRedirect("/register");
            }
        } catch (SQLException e) {
            session.invalidate();
            response.sendRedirect("/register");
        }



}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher2 = req.getRequestDispatcher("login.jsp");
        dispatcher2.forward(req, resp);

    }


}
