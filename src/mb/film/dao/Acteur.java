package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the acteurs database table.
 * 
 */
@Entity
@Table(name="acteurs")
@NamedQueries({

	@NamedQuery(name="Acteur.findAll", query="SELECT a FROM Acteur a"),
	@NamedQuery(name="Acteur.find", query="SELECT a FROM Acteur a WHERE a.id=:id")
})
public class Acteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="description")
	private String description;

	@Column(name="nom")
	private String nom;


	//bi-directional many-to-one association to Filmacteur
	@OneToMany(mappedBy="acteur")
	private List<Filmacteur> filmacteurs;

	public Acteur() {
	}
	
	

	public Acteur(int id, String nom, String description) {
		super();
		this.id = id;
		this.description = description;
		this.nom = nom;
	}

	

	public Acteur(int id, String description, String nom, List<Filmacteur> filmacteurs) {
		super();
		this.id = id;
		this.description = description;
		this.nom = nom;
		this.filmacteurs = filmacteurs;
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


	public List<Filmacteur> getFilmacteurs() {
		return this.filmacteurs;
	}

	public void setFilmacteurs(List<Filmacteur> filmacteurs) {
		this.filmacteurs = filmacteurs;
	}

	public Filmacteur addFilmacteur(Filmacteur filmacteur) {
		getFilmacteurs().add(filmacteur);
		filmacteur.setActeur(this);

		return filmacteur;
	}

	public Filmacteur removeFilmacteur(Filmacteur filmacteur) {
		getFilmacteurs().remove(filmacteur);
		filmacteur.setActeur(null);

		return filmacteur;
	}

	@Override
	public String toString() {
		return "Acteur [id=" + id + ", nom=" + nom + "]";
	}

	
}