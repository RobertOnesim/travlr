package flights1.flights1.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

public class ManagerUserHistory {
	private static SessionFactory factoryUserHistory =  new Configuration().configure().addAnnotatedClass(UserHistory.class).buildSessionFactory();
	
	public void addUserHistory(String idUser, String departureId, String arrivalId){
		System.out.println("In add user history");
		Session session = factoryUserHistory.openSession();
		String hql = "from UserHistory UH WHERE UH.idUser = :idUser and UH.departureId = :departureId and UH.arrivalId = :arrivalId";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("idUser", idUser);
		query.setParameter("departureId", departureId);
		query.setParameter("arrivalId", arrivalId);
		List <UserHistory> userHistorys = query.list();
		UserHistory userHistory=null;
		Session session2 = factoryUserHistory.openSession();
		Transaction tx =session2.beginTransaction();
	
		if (userHistorys.size()==0){
			System.out.println("size =0");
			userHistory = new UserHistory(idUser, departureId, arrivalId);
			session2.save(userHistory); 
		}else{
			System.out.println("In else");
			userHistory=userHistorys.get(0);
			userHistory.IncreaseCount(1);
			System.out.println("count="+userHistory.getCount());
			System.out.println("userHistory "+userHistory.getIdHistory());
			session2.update(userHistory); 
		}
		tx.commit();
		session2.close();
		session.close();
	}
	
	public Integer getMaxIdUserHistory(){
		Session session = factoryUserHistory.openSession();	
		Criteria criteria = session
				.createCriteria(UserHistory.class)
				.setProjection(Projections.max("idHistory"));
		Integer maxId = (Integer)criteria.uniqueResult();
		System.out.println("maxId = "+maxId);
		session.close();
		return maxId;
	}
	
	public List<UserHistory> getMostSearched(String userId){
		String hql = "from UserHistory UH WHERE UH.idUser = :userId order by UH.count desc";
		Session session2 = factoryUserHistory.openSession();
		org.hibernate.Query query = session2.createQuery(hql);
		query.setParameter("userId", userId);
		List <UserHistory> userHistories = query.list(); 
		session2.close(); 
		return userHistories;
	}
	
	public List<UserHistory> getMostSearchedUniversal(){
		String hql = "from UserHistory UH order by UH.count desc";
		Session session2 = factoryUserHistory.openSession();
		org.hibernate.Query query = session2.createQuery(hql);
		List <UserHistory> userHistories = query.list(); 
		session2.close(); 
		return userHistories;
	}

}
