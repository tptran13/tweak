//******************* This the USER class *******************
package edu.neiu.tweak.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CREATE_PROFILE_USER")
public class CreateProfile implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "DOB is required")
    private String dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min=4, message = "Username must have at least 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "Password does not meet requirements")
    @Column(nullable = false)
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;

    //mapping relationship, getter & setter have been created
    @OneToMany(mappedBy = "create_profile_user", cascade = CascadeType.ALL)
    private Set<CreateHackPost> posts = new HashSet<>();

    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles;

    public enum Role { ROLE_ADMIN, ROLE_USER }

    public CreateProfile()
    {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
    }

    public CreateProfile(String firstName, String lastName, String dateOfBirth, String email, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Set<CreateHackPost> getPosts()
    {
        return posts;
    }

    public void setPosts(Set<CreateHackPost> posts)
    {
        this.posts = posts;

        for(CreateHackPost post : posts)
            post.setCreate_profile_user(this);
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> authoritySet = new HashSet<>();

        for(Role role : roles)
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.toString());
            authoritySet.add(grantedAuthority);
        }

        return authoritySet;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
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
