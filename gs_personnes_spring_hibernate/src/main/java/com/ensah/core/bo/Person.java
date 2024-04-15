package com.ensah.core.bo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Person {

	/*
	 * Documentation : 
	 * @NotNull: a constrained CharSequence, Collection, Map, or Array is valid as
	 * long as it's not null, but it can be empty
	 * 
	 * @NotEmpty: a constrained CharSequence, Collection, Map, or Array is valid as
	 * long as it's not null and its size/length is greater than zero
	 * 
	 * @NotBlank: a constrained String is valid as long as it's not null and the
	 * trimmed length is greater than zero
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;

	@NotBlank(message = "This field is required")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{8}", message= "The National ID must be 2 upper letters followed by  8 digits")
	@Column(unique = true)
	private String nationalIdNumber;

	@NotBlank(message = "This field is required")
	private String firstName;

	@NotBlank(message = "This field is required")
	private String lastName;

	@Min(value = 20, message = "Age must be > 19")
	@Max(value = 90, message = "Age must be < 91")
	@NotNull(message = "This field is required")
	private int age;

	@Email(message = "Enter a valid email")
	@NotBlank(message = "This field is required")
	private String email;

	@NotBlank(message = "This field is required")
	@Size(min = 10, message = "The password is too short")
	@Size(max = 20, message = "The password is too big")
	private String password;

	@NotBlank(message = "This field is required")
	private String address;

	@NotBlank(message = "This field is required")
	private String state;

	@NotEmpty(message = "Choose at least one community")
	@ElementCollection
	@OrderColumn(name = "pos")
	private String[] community;

	@NotBlank(message = "This field is required")
	private String gender;

	public Long getIdPersonne() {
		return idPersonne;
	}

	@Override
	public String toString() {
		return "Person [idPersonne=" + idPersonne + ", nationalIdNumber=" + nationalIdNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", state=" + state + ", community=" + community + ", gender=" + gender + "]";
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getCommunity() {
		return community;
	}

	public void setCommunity(String[] community) {
		this.community = community;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
