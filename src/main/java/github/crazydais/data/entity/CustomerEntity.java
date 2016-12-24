package github.crazydais.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer", catalog = "microservice")
public class CustomerEntity extends BaseEntity {

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
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


}
