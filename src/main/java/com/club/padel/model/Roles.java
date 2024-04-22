package com.club.padel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.club.padel.view.View;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "roles",uniqueConstraints =  
	{@UniqueConstraint(
		name="roles_unique_key",
        columnNames = {"rol_type"})
	})
public class Roles {
	
	@Id
	@Column(name = "rol_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({View.Basic.class})
    private int id;
	
	@Column(name = "rol_type", unique = false, nullable = false)
	@JsonView({View.Basic.class})
    private String rolType;
    

    public Roles() {
    }

    public Roles(int id, String firstName, String lastName) {
        this.id = id;

    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Roles)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Roles Roles = (Roles) o;
         
        // Compare the data members and return accordingly
        return Roles.compare(this,Roles);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) id;
        return hash;
    }    

	private static boolean compare(Roles role, Roles role2) {
		if ( role!=null && role2!=null &&
			    role!=null &&  role.getRolType().equals(role2.getRolType())) 
			 {
			return true;
		}
		return false;
	}

	public String getRolType() {
		return rolType;
	}

	public void setRolType(String rolType) {
		this.rolType = rolType;
	}

}
