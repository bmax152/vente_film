package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String label;

	//bi-directional many-to-one association to Filmphoto
	@OneToMany(mappedBy="photo")
	private List<Filmphoto> filmphotos;

	public Photo() {
	}

	
	public Photo(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Filmphoto> getFilmphotos() {
		return this.filmphotos;
	}

	public void setFilmphotos(List<Filmphoto> filmphotos) {
		this.filmphotos = filmphotos;
	}

	public Filmphoto addFilmphoto(Filmphoto filmphoto) {
		getFilmphotos().add(filmphoto);
		filmphoto.setPhoto(this);

		return filmphoto;
	}

	public Filmphoto removeFilmphoto(Filmphoto filmphoto) {
		getFilmphotos().remove(filmphoto);
		filmphoto.setPhoto(null);

		return filmphoto;
	}

	@Override
	public String toString() {
		return label;
	}

	
}