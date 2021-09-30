package mb.film.dao;

import java.util.List;

import javax.persistence.TypedQuery;

public class FilmDao extends Dao<Film> {

	public FilmDao() {
		super(Film.class, "Film");
	}

	public List<Film> nouveautFilm(){
		
		TypedQuery<Film> query = getEntityManager().createQuery("SELECT f From Film f ORDER BY f.id DESC",Film.class);
		//on limite au 4 dernier films ajouter en bdd
		List<Film> list = query.setMaxResults(4).getResultList();
		
		try {
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
//	public List<Film> getSearch(String search) {
//		
//		String test = "f.description";
//		TypedQuery<Film> query = getEntityManager().createQuery("SELECT f From Film f WHERE "+test+" LIKE :search",Film.class);
//		query.setParameter("search", "%"+search+"%");
//
//		try {
//			return query.getResultList();
//		} catch (Exception e) {
//			return null;
//		}	
//		
//	}
}
