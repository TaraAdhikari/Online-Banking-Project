package com.userFront.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*@Entity--- this annotation will make this class will be consider as a entity and when hibernate started to run this
 *  entity will be persisted into the db. And in order to do that, we need to specify the primary key.*/
@Entity
public class Appointment {
	
	/*@Id---this indicate that this field 'private Long id;' will be the primary key. and this primary key(id) will be 
	 * automatically generated the annotaion @GeneratedValue(strategy = GenerationType.AUTO). This means every time just 
	 * the number will be increased from the previous one like 0,1,2,3,4... */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private String location;
	private String description;
	private boolean confirmed;//if we dont define any value for boolean then it will default be false.
	
	/*@ManyToOne-- its basically refering the relationship between Appiontment and User. One user can have multiple
	 *  appointments. so the relation is many to one from appiontment to user. */
	@ManyToOne
	/*@JoinColumn(name="user_id")--in order to reference appiontment to user, we need to add a cloumn, and this column is 
	 * primary to the user.*/
	@JoinColumn(name="user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", location=" + location + ", description=" + description
				+ ", confirmed=" + confirmed + ", user=" + user + "]";
	}

	
}
