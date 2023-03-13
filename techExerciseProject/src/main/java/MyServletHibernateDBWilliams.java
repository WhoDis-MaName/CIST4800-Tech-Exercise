import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.User;
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
      UtilDBWilliams.createUser("guest", "password", "email@url.com");
      
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
      List<User> listEmployees = UtilDBWilliams.listUsers();
      for (User user : listEmployees) {
         System.out.println("[DBG] " + user.getId() + ", " //
               + user.getUsername() + ", " //
               + user.getEmail());

         out.println("<li>" + user.getId() + ", " //
               + user.getUsername() + ", " //
               + user.getEmail() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
