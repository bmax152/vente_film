package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Table(name="articles")
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal prix;

	private int quantite;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_commande")
	private Commande commande;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;

	public Article() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Article(int id, BigDecimal prix, int quantite,Commande commande, Film film) {
		super();
		this.id = id;
		this.prix = prix;
		this.quantite = quantite;
		this.commande = commande;
		this.film = film;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", commande=" + commande + ", film="
				+ film + "]";
	}
	
	

}