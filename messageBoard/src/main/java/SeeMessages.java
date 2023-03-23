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
import util.Info;
import util.UtilDBWilliams;
import util.SessionLog;

@WebServlet("/SeeMessages")
public class SeeMessages extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SeeMessages() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String option = request.getParameter("options").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      if(option.equals("my")) {
    	  
      }else if(option.equals("all")) {
		  List<Messages> listMessages = null;
		     listMessages = UtilDBWilliams.listMessages();
		     display(listMessages, out);
      }
      
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + newMessage + ">Create Message</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Messages> listMessages, PrintWriter out) {
      for (Messages message : listMessages) {
         System.out.println("[DBG] " + message.getId() + ", " //
               + UtilDBWilliams.userNameById(message.getUser_id()) + ", " //
               + message.getText());

         out.println("<li>" + message.getId() + ", "
                 + UtilDBWilliams.userNameById(message.getUser_id()) + ", "
                 + message.getText() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
