package flights1.flights1.user;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
	@Id 
	@Column(name = "id_user")
	public String id;
	@Column(name = "firstname")
	public String firstName;
	@Column(name = "lastname")
	public String lastName;
	@Column(name = "lastactivity")
	public Date lastActivity;
	
	public User(){
	}
	
	public User(String id, String firstName, String lastName, Date lastActivity){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.lastActivity=lastActivity;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNume() {
		return firstName;
	}
	public void setNume(String nume) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
