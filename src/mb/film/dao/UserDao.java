package mb.film.dao;

import javax.persistence.TypedQuery;

public class UserDao extends Dao<User> {

	public UserDao() {
		super(User.class, "User");
	}
	
	public User log(String login, String psw) {
		
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login AND u.password=:psw", User.class);
		query.setParameter("login", login);
		query.setParameter("psw", psw);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
