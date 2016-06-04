package flights1.flights1.user;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User implements Comparable {
	@Id 
	@Column(name = "id_user")
	public String id;
	@Column(name = "lastname")
	public String lastName;
	@Column(name = "lastactivity")
	public Date lastActivity;
	@Column(name = "imgurl")
	public String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public User(){
	}
	
	public User(String id,  String lastName, Date lastActivity){
		this.id=id;
		this.lastName=lastName;
		this.lastActivity=lastActivity;
	}
	
	public User(String id,  String lastName, String imgUrl){
		this.id=id;
		this.lastName=lastName;
		this.imgUrl=imgUrl;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNume(String nume) {
		this.lastName = lastName;
	}



	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o){
		return id.equals(((User)o).getId());
	}
	
	@Override
	public int compareTo(Object o) {
		return id.compareTo(((User)o).getId());
	}
}
