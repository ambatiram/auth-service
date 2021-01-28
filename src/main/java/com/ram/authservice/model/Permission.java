package com.ram.authservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

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
	
	
}

