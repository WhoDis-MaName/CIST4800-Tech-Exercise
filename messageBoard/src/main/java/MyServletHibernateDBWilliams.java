import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Messages;
import datamodel.Users;
import util.UtilDBWilliams;

@WebServlet("/MyServletHibernateDBWilliams")
public class MyServletHibernateDBWilliams extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBWilliams() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDBWilliams.createUser("userA", "password","emailA@email");
      UtilDBWilliams.createUser("userB", "test", "emailB@email");
      UtilDBWilliams.createMessage("userA", "I hope this works.");
      UtilDBWilliams.createMessage("userB", "This should also work.");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Users> listUsers = UtilDBWilliams.listUsers();
      for (Users user : listUsers) {
         System.out.println("[DBG] " + user.getId() + ", " //
               + user.getName() + ", " //
               + user.getEmail());

         out.println("<li>" + user.getId() + ", " //
               + user.getName() + ", " //
               + user.getEmail() + "</li>");
      }
      out.println("</ul>");
      out.println("<ul>");
      List<Messages> listMessages = UtilDBWilliams.listMessages();
      for (Messages message : listMessages) {
         System.out.println("[DBG] " + message.getId() + ", " //
               + message.getUser_id() + ", " //
               + message.getText());

         out.println("<li>" + message.getId() + ", " //
                 + message.getUser_id() + ", " //
                 + message.getText() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
