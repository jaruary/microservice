package github.crazydais.data.entity;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class File extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @Column(name = "filedata", unique = false, nullable = true, length = 100000)
    @Lob
    private Blob fileData;

    @Column(name = "filename", unique = false, nullable = false)
    private String filename;

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

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer (Customer customer) {
        this.customer = customer;
    }

    public Blob getFileData () {
        return fileData;
    }

    public void setFileData (Blob fileData) {
        this.fileData = fileData;
    }

    public String getFilename () {
        return filename;
    }

    public void setFilename (String filename) {
        this.filename = filename;
    }

    public String getExtension () {
        return extension;
    }

    public void setExtension (String extension) {
        this.extension = extension;
    }

}
