package com.ram.authservice.dto;

import java.util.List;

public class RoleRequest {
	
	private String roleName;
	private List<PermissionRequest> permissions;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<PermissionRequest> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionRequest> permissions) {
		this.permissions = permissions;
	}
	
	
}
