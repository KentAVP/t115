package t113;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection = DBHelper.getConnection();
    private Executor executor = new Executor(connection);
/*
    public UserJdbcDAO(Connection connection) {
        this.executor = new Executor(connection);
    }*/

    public UserJdbcDAO() {

    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        return executor.execQuery("select * from uers", result -> {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                String role = result.getString("role");
                User user = new User(id, name, age,role);
                list.add(user);
            }
            return list;
        });
    }

    @Override
    public void delete(User user) throws SQLException {
        executor.execUpdate("delete from uers where id = "+user.getId());

    }

    @Override
    public void add(User user) throws SQLException {
        executor.execUpdate("insert into uers (name, age, role, username, password) values ('" + user.getName()
                +"', '"+user.getAge()+"', '"+user.getRole()+"', '"+user.getUsername()+"', '"+user.getPassword()+"')");

    }

    @Override
    public void update(User user) throws SQLException {
        executor.execUpdate("update uers set "+
                "name = '"+user.getName()+"', "+
                "age = '"+user.getAge()+"', "+
                "role = '"+user.getRole()+"', "+
                "username = '"+user.getUsername()+"', "+
                "password = '"+user.getPassword()+"' "+
                "WHERE id = "+user.getId());

    }

    @Override
    public User getbyID(int id) throws SQLException {
        return executor.execQuery("select * from uers where id = "+id,result->{
            result.next();
            return new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("role")
            );
        });
    }

    @Override
    public boolean isValidate(LoginBean user) throws SQLException {
        boolean status = false;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from uers where username = ? and password = ? ");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        status = rs.next();
        return status;
    }

    @Override
    public User checkRole(LoginBean user) throws SQLException {
        User us = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from uers where username = ? and password = ? ");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String role = rs.getString("role");
            us = new User(id, name, age,role);
        }
        return us;
    }
}
