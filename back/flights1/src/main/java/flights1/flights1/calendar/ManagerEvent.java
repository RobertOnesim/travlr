package flights1.flights1.calendar;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import flights1.flights1.user.Group;
import flights1.flights1.user.User;
import flights1.flights1.user.UserGroup;
import flights1.flights1.user.UserGroupPK;

public class ManagerEvent {
	private static SessionFactory factoryEvent =  new Configuration().configure().addAnnotatedClass(Event.class).buildSessionFactory();
	
	public static void deleteEvent(String idUser, Integer idGroup){
		Session session = factoryEvent.openSession();	
		String hql = "from Event where userID= :idUser and groupID= :idGroup";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("idUser", idUser);
		query.setParameter("idGroup", idGroup);
		List<Event> events  = query.list();
		for (Event e:events){
			System.out.println("EV");
			Session session2=factoryEvent.openSession();
			Transaction tx = null;
			try{
				tx = session2.beginTransaction();
				session2.delete(e);
				tx.commit();
			}catch (HibernateException ex) {
				if (tx!=null) tx.rollback();
				ex.printStackTrace(); 
			}finally {
				session2.close(); 
			}
		}
		
		session.close();
	}
	
	public static void insertEvent(String userId, Integer groupId, String name, String date){
		Event event = new Event(userId, groupId, name, date);
		Session session = factoryEvent.openSession();	
		Transaction tx = null;
		String userID = null;
		try{
			tx = session.beginTransaction();
			session.save(event); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
	
	public static Integer getMaxId(){
		Session session = factoryEvent.openSession();	
		Criteria criteria = session
				.createCriteria(Event.class)
				.setProjection(Projections.max("id"));
		Integer maxId = (Integer)criteria.uniqueResult();
		session.close();
		return maxId;
	}
}
