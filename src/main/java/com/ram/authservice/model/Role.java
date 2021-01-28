package com.ram.authservice.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "role_name"
            })
})
public class Role {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
		
//	@Enumerated(EnumType.STRING)
//    @NaturalId
//    @Column(length = 45, name="role_name")
//	private RoleName name;
	
	@Column(length = 45, name="role_name")
	private String roleName;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@JsonIgnoreProperties("roles")
    private Set<Permission> permissions = new HashSet<>();
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
//            mappedBy = "roles")
//	@JsonIgnoreProperties("roles")
//	private Set<User> users = new HashSet<User>();
	
	public Role() {

    }

//    public Role(RoleName name, Set<Permission> permissions) {
//        this.name = name;
//        this.permissions = permissions;
//    }
	
	public Role(String roleName, Set<Permission> permissions) {
		this.roleName = roleName;
		this.permissions = permissions;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

//	public RoleName getName() {
//		return name;
//	}
//
//	public void setName(RoleName name) {
//		this.name = name;
//	}
	
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
	
	
}
