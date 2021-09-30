package mb.film.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
	@NamedQuery(name="Role.find", query="SELECT r FROM Role r WHERE r.id = :id")
})

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String label;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="roleBean")
	private List<User> users;

	public Role() {
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRoleBean(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRoleBean(null);

		return user;
	}

	@Override
	public String toString() {
		return "Role [label=" + label + "]";
	}

	
}