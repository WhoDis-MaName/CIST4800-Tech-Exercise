package datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE Messages (
	ID INT NOT NULL AUTO_INCREMENT,
	USER_ID INT NOT NULL,
	MESSAGE TEXT,
	DATE DATETIME NOT NULL DEFAULT '2000-01-01 00:00:00',
	PRIMARY KEY (`ID`)
);
 */
@Entity
@Table(name = "Messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "USER_ID")
	private Integer user_id;
	
	@Column(name = "TEXT")
	private String text;
	   
	@Column(name = "DATE")
	private java.sql.Timestamp date;
	
	public Messages() {
	}
	
	   
	public Messages(Integer user_id, String text) {
		super();
		this.user_id = user_id;
		this.text = text;
		long millis = System.currentTimeMillis();  
        this.date=new java.sql.Timestamp(millis);
	}
	
	
	
	public Messages(Integer id, Integer user_id, String text) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.text = text;
		long millis = System.currentTimeMillis();  
        this.date=new java.sql.Timestamp(millis);
	}
	
	
	
	public Integer getUser_id() {
	   return user_id;
	}
	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public java.sql.Timestamp getDate() {
		return date;
	}
	
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public String toString() {
      return "User ID: "+ this.user_id + "Message:  "+ this.id + "\n\tTime:" + this.date + "\n\t" + this.text;
   }
}