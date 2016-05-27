package flights1.flights1.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ch.qos.logback.core.net.SyslogOutputStream;
import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerUser {
	private static SessionFactory factoryUser =  new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
	private static SessionFactory factoryUserGroup =  new Configuration().configure().addAnnotatedClass(UserGroup.class).buildSessionFactory();
	
	public String addUser(String id, String firstName, String lastName, Date lastActivity){
		Session session = factoryUser.openSession();
		Transaction tx = null;
		String userID = null;
		try{
			tx = session.beginTransaction();
			User user = new User(id, firstName, lastName, lastActivity);
			userID = (String) session.save(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return userID;
	}
	
	public List <User> getUsersFromGroup(Integer groupId){
		Session session = factoryUserGroup.openSession();	
		String hql = "SELECT id.idUser FROM UserGroup UG WHERE UG.id.idGroup = :idGroup";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("idGroup",groupId);
		List <String> userIds = query.list();
		java.util.Iterator<String> it = userIds.iterator();
		List<User> usersInGroup = new <User> ArrayList();
		while (it.hasNext()){
			System.out.println("In WHILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String idUser = (String)(it.next());
			hql = "from User U WHERE U.id = :idUser";
			Session session2 = factoryUser.openSession();
			query = session2.createQuery(hql);
			query.setParameter("idUser", idUser);
			System.out.println(idUser + "user cautat !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
			User u = (User)query.list().get(0);
			usersInGroup.add(u);
		}
		return usersInGroup;
		
		//User user = new User(id, firstName, lastName, lastActivity);
		//userID = (String) session.save(user); 
		
	}
	
	public void addUserToGroup(String userId, Integer groupId){
		Session session = factoryUserGroup.openSession();
		Transaction tx = null;
		System.out.println("In add user");
		try{
			tx = session.beginTransaction();
			UserGroup userGroup= new UserGroup(userId,groupId, new Date());
			System.out.println("userId= "+userId+"  groupId"+groupId);
			session.save(userGroup); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		//return userGroup;
	}
	
	 public void deleteUser(String UserID){
	      Session session = factoryUser.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         User user =(User)session.get(User.class, UserID); 
	         session.delete(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
}
