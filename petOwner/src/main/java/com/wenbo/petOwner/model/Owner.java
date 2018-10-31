package com.wenbo.petOwner.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "owner")
public class Owner {
	private int id;
	private String first_name;
	private String last_name;
	private String city;
	private Pet pet;

	public Owner() {

	}

	public Owner(String first_name, String last_name, String city) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.city = city;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
	public Pet getPet() {
		return pet;
	}

	@JsonBackReference
	public void setPet(Pet pet) {
		this.pet = pet;
	}

}