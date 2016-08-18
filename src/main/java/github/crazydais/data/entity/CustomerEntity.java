package github.crazydais.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer", catalog = "microservice")
public class CustomerEntity extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnore
    private List<FileEntity> files;

    @Column(name = "firstname", unique = false, nullable = false)
    private String firstName;

    @Column(name = "lastname", unique = false, nullable = false)
    private String lastName;

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public List<FileEntity> getFiles () {
        return this.files;
    }
    
    public void setFiles (List<FileEntity> files) {
        this.files = files;
    }
    

}
