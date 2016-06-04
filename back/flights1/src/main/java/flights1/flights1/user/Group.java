package flights1.flights1.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group {
	@Id 
	@Column(name = "id_group")
	private int idGroup;
	@Column(name = "groupname")
	private String groupName;
	
	public int getIdGroup() {
		return idGroup;
	}
	
	public Group(){
	}
	
	public Group(String groupName){
		this.groupName=groupName;
		ManagerUser mu = new ManagerUser();
		this.idGroup = mu.getMaxIdGroup()+1;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
