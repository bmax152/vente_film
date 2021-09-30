package mb.film.junit;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import mb.film.dao.Commande;
import mb.film.dao.Film;
import mb.film.dao.FilmDao;
import mb.film.dao.User;
import mb.film.model.CommandeRepository;
import mb.film.model.FilmRepository;
import mb.film.model.UserRepository;

public class FilmJunit {

	//@Test
	public void test() {
		FilmDao dao = new FilmDao();
		List<Film> liste = dao.list();
		System.out.println(liste);
		assertNotNull(liste);
	}
	
	//@Test
	public void test2() {
		
		FilmRepository repo = new FilmRepository();
		List<Film> listFilms = repo.nouveautFilm();
		
		for (Film film : listFilms) {
			System.out.println(film.getFilmphotos().get(0).getPhoto());

		}
		
		assertNotNull(listFilms);
	}
	
	//@Test
//	public void testSearch() {
//		FilmRepository repo = new FilmRepository();
//		List<Film> listFilms = repo.getSearch("enfer", "description");
//		
//		assertNotNull(listFilms);
//		System.out.println(listFilms);
//	}

	//@Test
	public void testListCommande() {
		CommandeRepository repo = new CommandeRepository();
		UserRepository repoU = new UserRepository();
		
		User test = repoU.find(2);
		
		List<Commande> list = repo.getListByUser(test);
		
		assertNotNull(list);
		System.out.println(list);
	}
}
