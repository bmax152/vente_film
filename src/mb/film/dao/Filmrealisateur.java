package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the filmrealisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Filmrealisateur.findAll", query="SELECT f FROM Filmrealisateur f")
public class Filmrealisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Realisateur
	@ManyToOne
	@JoinColumn(name="id_real")
	private Realisateur realisateur;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;

	public Filmrealisateur() {
	}

	
	
	public Filmrealisateur(int id, Realisateur realisateur, Film film) {
		super();
		this.id = id;
		this.realisateur = realisateur;
		this.film = film;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Realisateur getRealisateur() {
		return this.realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}