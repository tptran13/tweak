package edu.neiu.tweak.model;

import org.aspectj.bridge.Message;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime created;
    private LocalDateTime modified;

    public CreateHackPost(){}

    public CreateHackPost(String title, String date, String description)
    {
        this.title = title;
        this.date = date;
        this.description = description;
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
