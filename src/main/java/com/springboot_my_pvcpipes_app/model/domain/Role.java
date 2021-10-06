/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.domain;

import javax.persistence.*;

/**
 * @author Dcruz
 *
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, length = 45)
    private String name;
 
    public Role() { }
     
    public Role(String name) {
        this.name = name;
    }
     
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
 
    public Role(Long id) {
        this.id = id;
    }
     
 
    @Override
	public String toString() {
		return this.name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
