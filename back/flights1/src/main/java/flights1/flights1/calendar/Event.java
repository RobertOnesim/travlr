package flights1.flights1.calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Event {
	@Id 
	@Column(name = "id")
	private Integer id;
	@Column(name = "userid")
	private String userID;
	@Column(name = "data")
	private String data;
	@Column(name = "groupid")
	private Integer groupID;
	@Column(name = "name")
	private String name;
	
	public Event(){
		
	}
	public Event(String userID, Integer groupID, String name, String data){
		this.userID=userID;
		this.groupID=groupID;
		this.name=name;
		this.data=data;
		if (ManagerEvent.getMaxId()==null)
			this.id=1;
		else
			this.id = ManagerEvent.getMaxId()+1;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
