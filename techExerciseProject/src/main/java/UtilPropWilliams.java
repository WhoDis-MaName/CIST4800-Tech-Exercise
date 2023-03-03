import java.util.Properties;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;

public class UtilPropWilliams {

   static Properties prop = new Properties();

   public static void loadProperty(ServletContext servletContext) throws Exception {
      String filePath = "/WEB-INF/connections.properties";
      InputStream is = servletContext.getResourceAsStream(filePath);

      System.out.println("[DBG] Loaded: " + new File(filePath).getAbsolutePath());
      prop.load(is);
   }

   public static String getProp(String key) {
      return prop.getProperty(key).trim();
   }
}
