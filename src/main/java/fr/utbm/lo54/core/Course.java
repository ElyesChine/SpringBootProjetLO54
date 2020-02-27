package fr.utbm.lo54.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Course")
public class Course implements Serializable
{
	// ----------------- COURSE PARAMETERS ----------------//
    @Id
    @Column(name = "Course_Code")
    private String code;

    @Column(name = "Title")
    private String title;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseSession> listCourseSessions = new ArrayList<>();


    // ------------------------- CONSTRUCTORS ----------------------------//
    public Course()
    {
        super();
    }

    public Course(String code, String title)
    {
        this.code = code;
        this.title = title;
    }

    // ----------------------- GETTERS AND SETTERS -----------------------//

    public Course(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.code);
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.listCourseSessions);
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
        final Course other = (Course) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.listCourseSessions, other.listCourseSessions);
    }

    @Override
    public String toString()
    {
        return "Course{ " + "code=" + code + ", title=" + title + " }";
    }
}
