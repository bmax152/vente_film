package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the realisateurs database table.
 * 
 */
@Entity
@Table(name="realisateurs")
@NamedQuery(name="Realisateur.findAll", query="SELECT r FROM Realisateur r")
public class Realisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String nom;


	//bi-directional many-to-one association to Filmrealisateur
	@OneToMany(mappedBy="realisateur")
	private List<Filmrealisateur> filmrealisateurs;

	public Realisateur() {
	}

	
	
	public Realisateur(int id, String nom, String description, List<Filmrealisateur> filmrealisateurs) {
		super();
		this.id = id;
		this.description = description;
		this.nom = nom;
		this.filmrealisateurs = filmrealisateurs;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Filmrealisateur> getFilmrealisateurs() {
		return this.filmrealisateurs;
	}

	public void setFilmrealisateurs(List<Filmrealisateur> filmrealisateurs) {
		this.filmrealisateurs = filmrealisateurs;
	}

	public Filmrealisateur addFilmrealisateur(Filmrealisateur filmrealisateur) {
		getFilmrealisateurs().add(filmrealisateur);
		filmrealisateur.setRealisateur(this);

		return filmrealisateur;
	}

	public Filmrealisateur removeFilmrealisateur(Filmrealisateur filmrealisateur) {
		getFilmrealisateurs().remove(filmrealisateur);
		filmrealisateur.setRealisateur(null);

		return filmrealisateur;
	}

}