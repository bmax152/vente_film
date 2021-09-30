package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the filmphoto database table.
 * 
 */
@Entity
@NamedQuery(name="Filmphoto.findAll", query="SELECT f FROM Filmphoto f")
public class Filmphoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Photo
	@ManyToOne
	@JoinColumn(name="id_photo")
	private Photo photo;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;

	public Filmphoto() {
	}

	
	
	public Filmphoto(int id, Photo photo, Film film) {
		super();
		this.id = id;
		this.photo = photo;
		this.film = film;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}