package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE USERS (
  id INT NOT NULL AUTO_INCREMENT,    
  USERNAME VARCHAR(30) NOT NULL,   
  PASSWORD VARCHAR(30) NOT NULL,
  EMAIL VARCHAR(30) NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "USER")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "USERNAME")
   private String username;

   @Column(name = "PASSWORD")
   private String password;
   
   @Column(name = "EMAIL")
   private String email;

   public User() {
   }

   public User(Integer id, String username, String password, String email) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.email = email;
   }

   public User(String username, String password, String email) {
	   this.username = username;
	   this.password = password;
	   this.email = email;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
   
   public String getUsername() {
	   return username;
   }
   
   public void setUsername(String username) {
	   this.username = username;
   }
   
   public String getPassword() {
	   return password;
   }
   
   public void setPassword(String password) {
	   this.password = password;
   }
   
   public String getEmail() {
	   return email;
   }
   
   public void setEmail(String email) {
	   this.email = email;
   }
   

   

   @Override
   public String toString() {
      return "User: " + this.id + ", " + this.username + ", " + this.email;
   }
}