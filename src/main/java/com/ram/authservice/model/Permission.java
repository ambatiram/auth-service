package com.ram.authservice.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "permission",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "permission_code"
            })
})
public class Permission {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
//	@Enumerated(EnumType.STRING)
//    @NaturalId
//    @Column(length = 45, name="permission_code")
//	private PermissionCode name;
	
	@Column(length = 45, name="permission_code")
	private String permissionCode;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "permissions")
	@JsonIgnoreProperties("permissions")
	private Set<Role> roles = new HashSet<Role>();
	
	public Permission() {

    }

//    public Permission(PermissionCode name) {
//        this.name = name;
//    }
	public Permission(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}

