package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE MESSAGES (
  id INT NOT NULL AUTO_INCREMENT, 
  USER_ID INT NOT NULL,    
  MESSAGE VARCHAR(255),
  DATE DATETIME NOT NULL DEFAULT '2000-01-01 00:00:00',    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "MESSAGES")
public class Message {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "USER_ID")
   private Integer userId;

   @Column(name = "MESSAGE")
   private String message;
   
   @Column(name = "DATE")
   private String date;

   public Message() {
   }

   public Message(Integer id, Integer userId, String message, String date) {
      this.id = id;
      this.userId = userId;
      this.message = message;
      this.date = date;
   }

   public Message(Integer userId, String message, String date) {
	   this.userId = userId;
	   this.message = message;
	   this.date = date;
   }
   
   public Message(Integer userId, String message) {
	   this.userId = userId;
	   this.message = message;
	   this.date = "2000-01-01 00:00:00";
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
   
   public Integer getUserId() {
	   return userId;
   }
   
   public void setUserId(Integer userId) {
	   this.userId = userId;
   }
   
   public String getMessage() {
	   return message;
   }
   
   public void setMessage(String message) {
	   this.message = message;
   }
   
   public String getDate() {
	   return date;
   }
   
   public void setDate(String date) {
	   this.date = date;
   }
   

   

   @Override
   public String toString() {
      return "Message: " + this.id + ", " + this.userId /*Eventually will switch with code for users actual name*/ + ", " + this.date + "\n"+ this.message;
   }
}