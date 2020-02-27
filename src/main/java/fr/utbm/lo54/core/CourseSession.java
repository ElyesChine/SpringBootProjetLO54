/*
 *
 * @authors "Hassan HAJJAR" and "Linda JEMNI"
 *
*/
package fr.utbm.lo54.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "Course_Session")
public class CourseSession implements Serializable
{
	// ----------------- CourseSession PARAMETERS ----------------//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Course_Session_Id")
	private int id;

	@Column(name = "Start_Date")
	private LocalDate startDate;

	@Column(name = "End_Date")
	private LocalDate endDate;


	@Column(name = "Max")
	private Integer max;

	@ManyToMany(mappedBy = "listCourseSessions", fetch = FetchType.LAZY)
	private List<Client> listClients = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "COURSE_CODE")
	private Course course;

	@Transient
	private Integer size;

	// ------------------------- CONSTRUCTORS ----------------------------//

	public CourseSession()
	{
		super();
	}

	public CourseSession(int id, LocalDate startDate, LocalDate endDate, Integer max)
	{
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.max = max;
	}

	public CourseSession(LocalDate startDate, LocalDate endDate,  Integer max, int size)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.max = max;
		this.size = size;
	}

	public CourseSession(LocalDate startDate, LocalDate endDate, Integer max, Location location, Course course)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.max = max;
		this.location = location;
		this.course = course;
	}

	public CourseSession(int id, LocalDate startDate, LocalDate endDate, Integer max, Location location, Course course)
	{
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.max = max;
		this.location = location;
		this.course = course;
	}

	// ----------------------- GETTERS AND SETTERS ----------------------- //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Client> getListClients() {
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	@Transient
	public Integer getSize() {
		return size;
	}

	@Transient
	public void setSize(Integer size) {
		this.size = size;
	}

	// ------------------------ OTHERS ----------------------------//

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.id);
		hash = 97 * hash + Objects.hashCode(this.startDate);
		hash = 97 * hash + Objects.hashCode(this.endDate);
		hash = 97 * hash + Objects.hashCode(this.max);
		hash = 97 * hash + Objects.hashCode(this.listClients);
		hash = 97 * hash + Objects.hashCode(this.location);
		hash = 97 * hash + Objects.hashCode(this.course);
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
		final CourseSession other = (CourseSession) obj;
		if (!Objects.equals(this.startDate, other.startDate)) {
			return false;
		}
		if (!Objects.equals(this.endDate, other.endDate)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.max, other.max)) {
			return false;
		}
		if (!Objects.equals(this.listClients, other.listClients)) {
			return false;
		}
		if (!Objects.equals(this.location, other.location)) {
			return false;
		}
		return Objects.equals(this.course, other.course);
	}

	@Override
	public String toString()
	{
		return "CourseSession{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", max=" + max + ", location="
				+ location + ", course=" + course + '}';
	}
}
