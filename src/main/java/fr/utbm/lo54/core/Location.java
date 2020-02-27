package fr.utbm.lo54.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Location")
public class Location implements Serializable 
{	
	// ----------------- LOCATION PARAMETERS ----------------//

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Location_Id")
    private int id;

    @Column(name = "City")
    private String city;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<CourseSession> listCourseSession = new ArrayList<>();

	// ------------------------- CONSTRUCTORS ----------------------------//
    
    public Location() 
    {
        super();
    }

    public Location(int id, String city) 
    {
        this.id = id;
        this.city = city;
    }

    public Location(String city) 
    {
        this.city = city;
    }
    
    // ----------------------- GETTERS AND SETTERS -----------------------//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CourseSession> getListCourseSession() {
        return listCourseSession;
    }

    public void setListCourseSession(List<CourseSession> listCourseSession) {
        this.listCourseSession = listCourseSession;
    }
    
    // ------------------------ OTHERS ----------------------------//

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.city);
        hash = 59 * hash + Objects.hashCode(this.listCourseSession);
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
        final Location other = (Location) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.listCourseSession, other.listCourseSession);
    }

    @Override
    public String toString() 
    {
        return "Location{" + "id=" + id + ", city=" + city + '}';
    }

}
