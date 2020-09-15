package za.co.sita.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import za.co.sita.quiz.dto.User;
import za.co.sita.util.MyConnect;

public class UserDAO {
	

	
//=========================================EXECUTE INSERT========================================================	
	public int insert(User user) {
		
		try {
			PreparedStatement stmt = new MyConnect().Connect().prepareStatement("INSERT INTO users (name, surname, password, email, Id_Number, usertype) VALUES (?,?,?,?,?,?)");
			stmt.setString(1, user.getName());
			stmt.setString(2,user.getSurname());
			stmt.setString(3, user.getPassword()  == null? " " : user.getPassword());
			stmt.setString(4, user.getEmail()  == null? " " : user.getEmail());
			stmt.setString(5, user.getId_number());
			stmt.setString(6, user.getUsertype() == null? "normal" : user.getUsertype());
                        
                        
			 return stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}
		
		
	
	//=========================================EXECUTE UPDATE======================================================	
	
public int update(User user) {
		
		try {
			PreparedStatement stmt = new MyConnect().Connect().prepareStatement("UPDATE users SET password=? WHERE Id_Number=?");
			
			stmt.setString(1, user.getPassword());
			
			stmt.setString(2, user.getId_number());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}


		return 0;
			
		
	}



//=========================================EXECUTE SELECT======================================================	


		public User select(User user) {
			User userperson = null;
			
			try {
				PreparedStatement stmt = new MyConnect().Connect().prepareStatement("SELECT * FROM users where Id_Number = ? and password = ? ");
				stmt.setString(1, user.getId_number());
				stmt.setString(2, user.getPassword());
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					userperson = new User();
					userperson.setId_number(user.getId_number());
					userperson.setPassword(user.getPassword());
					userperson.setName(rs.getString("name"));
					userperson.setSurname(rs.getString("surname"));
					userperson.setUsertype(rs.getString("usertype"));
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}


			return userperson;
				
			
		}
		
		
		//=========================================EXECUTE DELETE======================================================
		
		public void delete(User user) {
			
			try {
				PreparedStatement stmt = new MyConnect().Connect().prepareStatement("DELETE FROM users WHERE Id_Number=?");
				
				stmt.setString(1, user.getId_number());
		
				
				return;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}

                
public List<User> getUsers (){
List<User> no = new ArrayList<User> ();
try {
                PreparedStatement stmt = new MyConnect().Connect().prepareStatement("SELECT * FROM users");

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                User rx  = new User();
                        rx.setId_number(rs.getString("Id_Number"));
                        rx.setName(rs.getString("name"));
                        rx.setSurname(rs.getString("surname"));
                        rx.setEmail(rs.getString("email"));
                        rx.setUsertype(rs.getString("usertype"));
                        no.add(rx);
                }

        } catch (SQLException e) {

                e.printStackTrace();
        }
        return no;

}
}



