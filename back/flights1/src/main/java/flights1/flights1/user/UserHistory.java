package flights1.flights1.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_HISTORY")
public class UserHistory{
	@Id 
	@Column(name = "id_history")
	private int idHistory;
	@Column(name = "id_user")
	private String idUser;
	@Column(name = "departure_id")
	private String departureId;
	@Column(name = "arrival_id")
	private String arrivalId;
	@Column(name = "count")
	private int count;
	public UserHistory(){
	}
	
	public UserHistory(String idUser, String departureId, String arrivalId){
		this.idUser=idUser;
		this.departureId=departureId;
		this.arrivalId=arrivalId;
		ManagerUserHistory mu = new ManagerUserHistory();
		if (mu.getMaxIdUserHistory()!=null)
			this.idHistory = mu.getMaxIdUserHistory()+1;
		else
			this.idHistory=1;
		this.count=1;
	}
	
	public void IncreaseCount(int count){
		this.count+=count;
	}
	
	public int getIdHistory() {
		return idHistory;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getDepartureId() {
		return departureId;
	}
	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}
	public String getArrivalId() {
		return arrivalId;
	}
	public void setArrivalId(String arrivalId) {
		this.arrivalId = arrivalId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
