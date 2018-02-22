package com.me.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long roleId;

	    @Column(name = "role")
	    private String role;

	    public Role() {
	    }

	    public Role(String role) {
			super();
			this.role = role;
		}

		public Long getRoleId() {
	        return roleId;
	    }

	    public void setRoleId(Long roleId) {
	        this.roleId = roleId;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
	
}
