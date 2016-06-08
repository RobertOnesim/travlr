package flights1.flights1.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import ch.qos.logback.core.net.SyslogOutputStream;
import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerUser {
	private static SessionFactory factoryUser =  new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
	private static SessionFactory factoryGroup =  new Configuration().configure().addAnnotatedClass(Group.class).buildSessionFactory();
	private static SessionFactory factoryUserGroup =  new Configuration().configure().addAnnotatedClass(UserGroup.class).buildSessionFactory();

	public String addUser(String id, String lastName, String imgUrl){
		System.out.println("In create user");
		Session session = factoryUser.openSession();
		Transaction tx = null;
		String userID = null;
		try{
			tx = session.beginTransaction();
			User user = new User(id, lastName, imgUrl);
			System.out.println(user.getId()+user.getLastName()+user.getImgUrl());
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

	public Integer createGroup(String name){
		System.out.println("In create group");
		Session session = factoryGroup.openSession();
		Transaction tx = null;
		Integer idGroup = null;
		try{
			tx = session.beginTransaction();
			Group group = new Group(name);
			idGroup = group.getIdGroup(); 
			session.save(group);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return idGroup;
	}

	public List<User> getUserByName(String userName){
		System.out.println("In get user by name");
		String hql = "from User U WHERE U.lastName = :userName";
		Session session2 = factoryUser.openSession();
		org.hibernate.Query query = session2.createQuery(hql);
		query.setParameter("userName", userName);
		List <User> users = query.list(); 
		session2.close(); 
		return users;
	}
	
	public User getUserById(String userId){
		System.out.println("In get user by id");
		String hql = "from User U WHERE U.id = :userId";
		Session session2 = factoryUser.openSession();
		org.hibernate.Query query = session2.createQuery(hql);
		query.setParameter("userId", userId);
		List <User> users = query.list(); 
		session2.close(); 
		if (users.size()>0)
			return users.get(0);
		else
			return null;
	}

	public List<User> getUsersNotInGroup(Integer groupId){
		System.out.println("In get users not in group");

		String hql = "from User U";
		Session session = factoryUser.openSession();
		org.hibernate.Query query = session.createQuery(hql);
		List <User> users = query.list();
		List <User> usersInGroup = getUsersFromGroup(groupId);
		List <User> usersNotInGroup = users;
		usersNotInGroup.removeAll(usersInGroup);
		session.close();
		return usersNotInGroup;
	}

	public List<User> getUsersNotInGroupByName(Integer groupId, String userName){
		System.out.println("In get users not in group by name");

		String hql = "from User U WHERE U.lastName = :userName";
		Session session = factoryUser.openSession();
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		List <User> users = query.list();
		List <User> usersInGroup = getUsersFromGroup(groupId);
		List <User> usersNotInGroup = users;
		System.out.println("users  "+users.size() + "<<---");
		System.out.println("usersInGroup  "+usersInGroup.size() + "<<---");
		/*for (User u:usersNotInGroup){
			System.out.println(u.getFirstName()+" ID "+ u.getId()+" Contine "+usersInGroup.contains(u));
			if (usersInGroup.contains(u)){
				System.out.println(u.firstName);
				usersNotInGroup.remove(u);
			}
		}*/
		usersNotInGroup.removeAll(usersInGroup);
		session.close();
		return usersNotInGroup;
	}


	public List<PtRares> getGroupsOfUser(String userId){
		System.out.println("In get group of users");

		Session session = factoryUserGroup.openSession();	
		String hql = "SELECT id.idGroup FROM UserGroup UG WHERE UG.id.idUser = :userId";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("userId",userId);
		List <Integer> groupIds = query.list();
		List <String> groupNames = new ArrayList();
		List <PtRares> rezultate = new ArrayList();
		for (Integer id :groupIds){
			Session session2 = factoryGroup.openSession();	
			String hql2 = "SELECT groupName FROM Group G WHERE G.idGroup = :idGroup";
			org.hibernate.Query query2 = session2.createQuery(hql2);
			query2.setParameter("idGroup",id);
			groupNames.add((String)query2.list().get(0));
			rezultate.add(new PtRares(id,(String)query2.list().get(0)));
			
		}
		session.close();
		return rezultate;
	}

	public Integer getMaxIdGroup(){
		Session session = factoryGroup.openSession();	
		Criteria criteria = session
				.createCriteria(Group.class)
				.setProjection(Projections.max("idGroup"));
		Integer maxId = (Integer)criteria.uniqueResult();
		session.close();
		return maxId;
	}

	public List <User> getUsersFromGroup(Integer groupId){
		System.out.println("In get users from group");

		Session session = factoryUserGroup.openSession();	
		String hql = "SELECT id.idUser FROM UserGroup UG WHERE UG.id.idGroup = :idGroup";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("idGroup",groupId);
		List <String> userIds = query.list();
		java.util.Iterator<String> it = userIds.iterator();
		List<User> usersInGroup = new <User> ArrayList();
		while (it.hasNext()){
			//System.out.println("In WHILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String idUser = (String)(it.next());
			hql = "from User U WHERE U.id = :idUser";
			Session session2 = factoryUser.openSession();
			query = session2.createQuery(hql);
			query.setParameter("idUser", idUser);
			//System.out.println(idUser + "user cautat !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
			User u = (User)query.list().get(0);
			usersInGroup.add(u);
			session2.close();
		}
		session.close();
		return usersInGroup;

		//User user = new User(id, firstName, lastName, lastActivity);
		//userID = (String) session.save(user); 

	}

	public void addUserToGroup(String userId, Integer groupId){
		System.out.println("In add user to group");

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

	public void removeUserFromGroup(String userId, Integer groupId){
		System.out.println("In remove user from group");

		Session session = factoryUserGroup.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			UserGroup userGroup = (UserGroup)session.get(UserGroup.class, new UserGroupPK(userId,groupId)); 
			session.delete(userGroup); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}


	public void deleteUser(String UserID){
		System.out.println("In delete user");

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
