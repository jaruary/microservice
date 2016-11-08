package github.crazydais.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "file", catalog = "microservice")
public class FileEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", referencedColumnName = "id", unique = false, nullable = false)
    private CustomerEntity customer;

    @Column(name = "filedata", unique = false, nullable = true)
    @Lob
    private Blob fileData;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "extension", unique = false, nullable = false)
    private String extension;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", unique = false, nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    @PrePersist
    protected void onCreate () {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate () {
        updated = new Date();
    }

    public CustomerEntity getCustomer () {
        return customer;
    }

    public void setCustomer (CustomerEntity customer) {
        this.customer = customer;
    }

    public Blob getFileData () {
        return fileData;
    }

    public void setFileData (Blob fileData) {
        this.fileData = fileData;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getExtension () {
        return extension;
    }

    public void setExtension (String extension) {
        this.extension = extension;
    }

    public String getFileName () {
        return this.name + "." + this.extension;
    }

    public Date getCreated () {
        return created;
    }

    public void setCreated (Date created) {
        this.created = created;
    }

    public Date getUpdated () {
        return updated;
    }

    public void setUpdated (Date updated) {
        this.updated = updated;
    }


}
