/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBWilliams {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Users> listUsers() {
      List<Users> resultList = new ArrayList<Users>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> users = session.createQuery("FROM Users").list();
         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
            Users user = (Users) iterator.next();
            resultList.add(user);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Users> listUsers(String keyword) {
      List<Users> resultList = new ArrayList<Users>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> users = session.createQuery("FROM Users").list();
         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
            Users user = (Users) iterator.next();
            if (user.getName().contains(keyword)||user.getEmail().contains(keyword)){
               resultList.add(user);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   public static List<Users> listUsers(String username, String password) {
	      List<Users> resultList = new ArrayList<Users>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM Users").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	            Users user = (Users) iterator.next();
	            if ((user.getName().equals(username)||user.getEmail().equals(username))&&user.getPassword().equals(password)) {
	            		resultList.add(user);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

   public static void createUser(String userName, String password, String email) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Users(userName, password, email));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   public static Messages createMessage(String userName, String message) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      Messages newMessage = null;
	      try {
	         tx = session.beginTransaction();
	         List<Users> users = listUsers(userName);
	         List<Users> resultList = new ArrayList<Users>();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	            Users user = (Users) iterator.next();
	            if (user.getName().equals(userName)){
	            		resultList.add(user);
	            }
	         }
	         newMessage = new Messages(users.get(0).getId(), message);
	         session.save(newMessage);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return newMessage;
	   }
}
