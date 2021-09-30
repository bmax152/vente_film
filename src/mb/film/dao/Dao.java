package mb.film.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Dao<T> {

	//nom uniter de persistance dans META-INF/persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "commerceFilm_pu";
	private static EntityManager em=null;
	
	Class<T> entityClass;
	 String entityName;
	
	public Dao(Class<T> entityClass, String entityName) {
		
		this.entityClass = entityClass;
		this.entityName = entityName;
		
		if(em==null) {
			 EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			 em =  emFactory.createEntityManager();
		}
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	/*
	 * CRUD
	 */
	
	public List<T> list(){

		TypedQuery<T> query = (TypedQuery<T>) em.createNamedQuery(entityName+".findAll", entityClass);
		
		return query.getResultList();
	}
	
	public T find(int id){
	
		TypedQuery<T> query = em.createNamedQuery(entityName+".find", entityClass);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public T findByName(String nom) {
		TypedQuery<T> query = getEntityManager().createQuery("SELECT x From "+ entityName +" x WHERE x.nom LIKE :nom",entityClass);
		query.setParameter("nom", nom);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public T findByNamePhoto(String nom) {
		TypedQuery<T> query = getEntityManager().createQuery("SELECT x From "+ entityName +" x WHERE x.label LIKE :nom",entityClass);
		query.setParameter("nom", nom);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void delete (T item) {
		
		em.getTransaction().begin();
		em.remove(item);
		em.getTransaction().commit();
	}
	
	public void delete (int id) {
		
		T item = find(id);
		if(item!=null) {
			delete(item);
		}
	}
	
	public void update(T item) {
		em.getTransaction().begin();
		em.merge(item);
		em.getTransaction().commit();
	}
	
	public void add(T item) {
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
	}
	
	public List<T> getSearch(String search) {
			
		TypedQuery<T> query1 = getEntityManager().createQuery("SELECT f From "+ entityName +" f WHERE f.nom LIKE :search",entityClass);
		query1.setParameter("search", "%"+search+"%");
		List<T> list1 = query1.getResultList();
		
		TypedQuery<T> query2 = getEntityManager().createQuery("SELECT f From "+ entityName +" f WHERE f.description LIKE :search",entityClass);
		query2.setParameter("search", "%"+search+"%");
		List<T> list2 = query2.getResultList();
		
		List<T> list = new ArrayList<T>();
		list.addAll(list1);
		list.addAll(list2);
		
		try {
			return list;
		} catch (Exception e) {
			return null;
		}	
		
	}
}
