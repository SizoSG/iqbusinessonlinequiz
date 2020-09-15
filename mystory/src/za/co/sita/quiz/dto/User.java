package za.co.sita.quiz.dto;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * The persistent class for the users database table.
 * 
 */

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id_number;

	private String email;

	private String name;

	private String password;

	private String surname;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
        
        
       private String usertype;

	public User() {
	}


        
        
    public String getConfirmmypassword() {
        return confirmmypassword;
    }

    public void setConfirmmypassword(String confirmmypassword) {
        this.confirmmypassword = confirmmypassword;
    }
   
       private String confirmmypassword;

	   
        
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	
}