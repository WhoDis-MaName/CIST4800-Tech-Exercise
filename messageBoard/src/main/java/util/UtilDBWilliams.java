/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import util.SessionLog;

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
   static SessionLog session = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }
   
   public static SessionLog getSession() {
      if (session != null) {
         return session;
      }
      session = new SessionLog();
      return session;
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
   
   public static List<Messages> listMessages() {
      List<Messages> resultList = new ArrayList<Messages>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> messages = session.createQuery("FROM Messages").list();
         for (Iterator<?> iterator = messages.iterator(); iterator.hasNext();) {
            Messages message = (Messages) iterator.next();
            resultList.add(message);
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
   
   public static String userNameById(Integer userId) {
	  String userName = "";
      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> messages = session.createQuery("FROM Users").list();
         for (Iterator<?> iterator = messages.iterator(); iterator.hasNext();) {
            Users user = (Users) iterator.next();
            if (user.getId().equals(userId)){
               userName = user.getName();
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
      return userName;
   }
   
   public static List<Messages> listMessagesByUser(Users user) {
	      List<Messages> resultList = new ArrayList<Messages>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> messages = session.createQuery("FROM Messages").list();
	         for (Iterator<?> iterator = messages.iterator(); iterator.hasNext();) {
	            Messages message = (Messages) iterator.next();
	            if (message.getUser_id().equals(user.getId())){
	               resultList.add(message);
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
   
   public static List<Messages> listMessagesToday() {
	      List<Messages> resultList = new ArrayList<Messages>();
	      long millis = System.currentTimeMillis();  
	      java.sql.Timestamp today = new java.sql.Timestamp(millis);
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> messages = session.createQuery("FROM Messages").list();
	         for (Iterator<?> iterator = messages.iterator(); iterator.hasNext();) {
	            Messages message = (Messages) iterator.next();
	            if (message.getDate().getMonth() == today.getMonth() && message.getDate().getYear() == today.getYear() && message.getDate().getDate() == today.getDate()){
	               resultList.add(message);
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
   
}
