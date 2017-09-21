package com.usersapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.usersapi.entity.User;

@Transactional
@Repository
public class UsersDAO implements IUsersDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as user ORDER BY user.id";
		return (List<User>) em.createQuery(hql).getResultList();
	}

	@Override
	public User getUserById(int id) {
		return em.find(User.class, id);
		
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public void updateUser(User user) {
		User aUser = getUserById(user.getId());
		if(!user.getUsername().equals("")){
			aUser.setUsername(user.getUsername());
		}
		if(!user.getPassword().equals("")){
			aUser.setPassword(user.getPassword());
		}
		em.flush();
	}

	@Override
	public void deleteUser(int id) {
		em.remove(getUserById(id));
	}

	@Override
	public boolean isUserPresent(String username) {
		String hql = "FROM User as user WHERE user.username = :username";
		int count = em.createQuery(hql).setParameter("username", username).getResultList().size();
		
		return count > 0 ? true : false;
	}

	@Override
	public User getUserByUserName(String username) {
		String hql = "FROM User as user WHERE user.username = :username";
		User user = null;
		try{
			 user = (User) em.createQuery(hql).setParameter("username", username).getSingleResult();

		}
		catch (Exception e){
			
		}
		return user;
	}

}
