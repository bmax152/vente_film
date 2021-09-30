package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the filmacteur database table.
 * 
 */
@Entity
@NamedQuery(name="Filmacteur.findAll", query="SELECT f FROM Filmacteur f")
public class Filmacteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Acteur
	@ManyToOne
	@JoinColumn(name="id_acteur")
	private Acteur acteur;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;

	public Filmacteur() {
	}

	
	
	public Filmacteur(int id, Acteur acteur, Film film) {
		super();
		this.id = id;
		this.acteur = acteur;
		this.film = film;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Acteur getActeur() {
		return this.acteur;
	}

	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "Filmacteur [acteur=" + acteur + "]";
	}

	
}