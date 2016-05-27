package flights1.flights1.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class UserGroupPK implements Serializable{
	@Column(name="id_user")
	private String idUser;
	@Column(name="id_group")
	private int idGroup;
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	
	
	public UserGroupPK() {}

	public UserGroupPK(String idUser, int idGroup) {
		this.idUser=idUser;
		this.idGroup=idGroup;
	}

}
