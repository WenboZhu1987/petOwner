package com.wenbo.petOwner.model;



import javax.persistence.*;


import java.util.Date;

@Entity
@Table(name = "pet")
public class Pet {
	
	
	private int id;
	private String name;
	private Date birthday;
	private Owner owner;

	public Pet() {

	}
	
	

	public Pet(String name, Date birthday, Owner owner) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.owner = owner;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	@OneToOne
	@JoinColumn(name = "owner_id")
	public Owner getOwner() {
		return owner;
	}



	public void setOwner(Owner owner) {
		this.owner = owner;
	}



	

	






	

}
