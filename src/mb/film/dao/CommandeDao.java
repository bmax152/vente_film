package mb.film.dao;

import java.util.List;

import javax.persistence.TypedQuery;

public class CommandeDao extends Dao<Commande> {

	public CommandeDao() {
		super(Commande.class, "Commande");
		
	}
	
	public List<Commande> getListByUser(User user) {
		
		
		TypedQuery<Commande> query = getEntityManager().createQuery("SELECT c FROM Commande c WHERE c.user=:user", Commande.class);
		query.setParameter("user", user);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
		
	}
}
