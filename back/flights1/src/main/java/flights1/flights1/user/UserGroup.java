package flights1.flights1.user;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USER_GROUP")

public class UserGroup {
    @EmbeddedId
	private UserGroupPK id;
	 
	/*@Column(name = "idUser")
	private String idUser;
	 
	@Column(name = "idGroup", nullable=false)
	private int idGroup;
*/
	@Column(name = "dateadded" , nullable=false)
	private Date dateAdded;
	
	public UserGroup(){
	}
	
	/*public UserGroup(UserGroupPK id, Date dateAdded){
		this.id=id;
		this.dateAdded=dateAdded;
	}*/
	
	public UserGroup(String idUser, int idGroup, Date dateAdded){
		this.id = new UserGroupPK(idUser, idGroup);
		/*this.idUser=idUser;
		this.idGroup=idGroup;*/
		this.dateAdded=dateAdded;
	}
	
	/*public UserGroup(UserGroupPK ugPK, Date dateAdded){
		this.idUser=ugPK.getClass(idUser);
		this.idGroup=ugPK.getidGroup;
		this.dateAdded=dateAdded;
	}*/
}
