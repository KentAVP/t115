package t113;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/register")
public class RegisterServ extends HttpServlet {
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

        String name = request.getParameter("name");
        String ageS = request.getParameter("age");
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(ageS);
        User user = new User(name, age, role, username, password);
        try {
            userDao.add(user);
        } catch (SQLException e) {
            response.sendRedirect("/");
        }

        response.sendRedirect("details.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher2 = req.getRequestDispatcher("register.jsp");
        dispatcher2.forward(req, resp);
    }
}
