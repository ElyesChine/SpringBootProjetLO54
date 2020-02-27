package fr.utbm.lo54.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Client")
public class Client implements Serializable
{
	// ----------------- CLIENT PARAMETERS ----------------//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Client_Id")
	private int id;

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Address")
	private String address;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Enroll", joinColumns = { @JoinColumn(name = "Client_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "Course_Session_Id") })
	private List<CourseSession> listCourseSessions = new ArrayList<>();


	// ------------------------- CONSTRUCTORS ----------------------------//

	public Client()
	{
		super();
	}

	public Client(int id, String firstName, String lastName, String address, String phone, String email, String password)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Client(String firstName, String lastName, String address, String phone, String email, String password)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	// ----------------------- GETTERS AND SETTERS -----------------------//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public List<CourseSession> getListCourseSessions() {
		return listCourseSessions;
	}

	public void setListCourseSessions(List<CourseSession> listCourseSessions) {
		this.listCourseSessions = listCourseSessions;
	}

	// ------------------------ OTHERS ----------------------------//

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.firstName);
		hash = 37 * hash + Objects.hashCode(this.lastName);
		hash = 37 * hash + Objects.hashCode(this.address);
		hash = 37 * hash + Objects.hashCode(this.phone);
		hash = 37 * hash + Objects.hashCode(this.email);
		hash = 37 * hash + Objects.hashCode(this.listCourseSessions);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Client other = (Client) obj;
		if (!Objects.equals(this.firstName, other.firstName)) {
			return false;
		}
		if (!Objects.equals(this.lastName, other.lastName)) {
			return false;
		}
		if (!Objects.equals(this.address, other.address)) {
			return false;
		}
		if (!Objects.equals(this.phone, other.phone)) {
			return false;
		}
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return Objects.equals(this.listCourseSessions, other.listCourseSessions);
	}

	@Override
	public String toString()
	{
		return "Client{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", password=" + password + '}';
	}
}
