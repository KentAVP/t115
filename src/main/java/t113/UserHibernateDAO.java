package t113;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    public UserHibernateDAO() {
    }

    @Override
    public List<User> getAll() {
        Transaction transaction = null;
        List<User> listOfUser = null;
        try {
            // start a transaction
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // get an user object

            listOfUser = session.createQuery("from User").list(); // забераем из наименование класса

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;

        try {
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (user != null) {
                session.delete(user);
                System.out.println("Вы удалили пользователя!");
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        Transaction transaction = null;
        try {
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try {
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User getbyID(int id) {
        Transaction transaction = null;
        User user = null;
        try {
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isValidate(LoginBean user) throws SQLException {
        Transaction transaction = null;
        List remp = null;
        boolean status = false;
        try{
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String s = "select * from uers where username = "+"'"+user.getUsername()+"'";
            SQLQuery spSQLQuery = session.createSQLQuery(s);
            remp = spSQLQuery.list();
            if (remp.get(0) != null) status=true;
            else status=false;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            status=false;
        }
       return status;
    }

    @Override
    public User checkRole(LoginBean user) throws SQLException {
        Transaction transaction = null;
        User us= null;
        try{
            Session session = DBHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List list = session.createCriteria(User.class).add(Restrictions.like("username",user.getUsername())).list();
            us = (User) list.get(0);
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
        return us;
    }
}
