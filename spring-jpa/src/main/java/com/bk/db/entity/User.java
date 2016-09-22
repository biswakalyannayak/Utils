package com.bk.db.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	// The entity fields (private)  
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="id")
	  private long id;
	  
	  @NotNull
	  @Column(name="email")
	  private String email;
	  
	  @NotNull
	  @Column(name="name")
	  private String name;
	  
	  @OneToMany(cascade=CascadeType.ALL)
	  @JoinColumn(name="user_id",referencedColumnName="id")
	  private List<Phone> phoneNumber;
	  


	  // Public methods
	  
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }

	  public User(String email, String name) {
	    this.email = email;
	    this.name = name;
	  }
	  
	 


	public List<Phone> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<Phone> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
