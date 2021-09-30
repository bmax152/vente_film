package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the films database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQueries({
	@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f"),
	@NamedQuery(name="Film.find", query="SELECT f FROM Film f WHERE f.id=:id")
})
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String nom;

	private BigDecimal prix;

	private int quantite;

	private String resumer;

	//bi-directional many-to-one association to Article
	@OneToMany(mappedBy="film")
	private List<Article> articles;

	//bi-directional many-to-one association to Filmacteur
	@OneToMany(mappedBy="film")
	private List<Filmacteur> filmacteurs;

	//bi-directional many-to-one association to Filmphoto
	@OneToMany(mappedBy="film")
	private List<Filmphoto> filmphotos;

	//bi-directional many-to-one association to Filmrealisateur
	@OneToMany(mappedBy="film")
	private List<Filmrealisateur> filmrealisateurs;

	public Film() {
	}

	
	
	public Film(int id, String description, String nom, BigDecimal prix, int quantite, String resumer) {
		super();
		this.id = id;
		this.description = description;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.resumer = resumer;
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

	public BigDecimal getPrix() {
		return this.prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getResumer() {
		return this.resumer;
	}

	public void setResumer(String resumer) {
		this.resumer = resumer;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setFilm(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setFilm(null);

		return article;
	}

	public List<Filmacteur> getFilmacteurs() {
		return this.filmacteurs;
	}

	public void setFilmacteurs(List<Filmacteur> filmacteurs) {
		this.filmacteurs = filmacteurs;
	}

	public Filmacteur addFilmacteur(Filmacteur filmacteur) {
		getFilmacteurs().add(filmacteur);
		filmacteur.setFilm(this);

		return filmacteur;
	}

	public Filmacteur removeFilmacteur(Filmacteur filmacteur) {
		getFilmacteurs().remove(filmacteur);
		filmacteur.setFilm(null);

		return filmacteur;
	}

	public List<Filmphoto> getFilmphotos() {
		return this.filmphotos;
	}

	public void setFilmphotos(List<Filmphoto> filmphotos) {
		this.filmphotos = filmphotos;
	}

	public Filmphoto addFilmphoto(Filmphoto filmphoto) {
		getFilmphotos().add(filmphoto);
		filmphoto.setFilm(this);

		return filmphoto;
	}

	public Filmphoto removeFilmphoto(Filmphoto filmphoto) {
		getFilmphotos().remove(filmphoto);
		filmphoto.setFilm(null);

		return filmphoto;
	}

	public List<Filmrealisateur> getFilmrealisateurs() {
		return this.filmrealisateurs;
	}

	public void setFilmrealisateurs(List<Filmrealisateur> filmrealisateurs) {
		this.filmrealisateurs = filmrealisateurs;
	}

	public Filmrealisateur addFilmrealisateur(Filmrealisateur filmrealisateur) {
		getFilmrealisateurs().add(filmrealisateur);
		filmrealisateur.setFilm(this);

		return filmrealisateur;
	}

	public Filmrealisateur removeFilmrealisateur(Filmrealisateur filmrealisateur) {
		getFilmrealisateurs().remove(filmrealisateur);
		filmrealisateur.setFilm(null);

		return filmrealisateur;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", nom=" + nom + ", filmacteurs=" + filmacteurs + "]";
	}

	
}