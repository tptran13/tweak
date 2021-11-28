package edu.neiu.tweak.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "HACKPOST")
public class CreateHackPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id; //keep id on top!!!!

    @NotBlank(message = "Title is required")
    @Size(min = 2, message = "Title must be 2 or more characters")
    private String title;

    @NotBlank(message = "Date is required")
    private String date;

    private String description;

//    @Lob
//    @Column(name = "image", columnDefinition="BLOB")
//    private byte[] image;

    private LocalDateTime created;
    private LocalDateTime modified;

    public CreateHackPost(){}

    public CreateHackPost(String title, String date, String description)
    {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }

    public LocalDateTime getModified()
    {
        return modified;
    }

    public void setModified(LocalDateTime modified)
    {
        this.modified = modified;
    }

    @PrePersist
    public void onCreate()
    {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate()
    {
        this.setModified(LocalDateTime.now());
    }
}
