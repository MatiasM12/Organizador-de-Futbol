package organizador.futbol.domain;

import lombok.Data;

@Data
public class Role {
	
    private Long idRole;
    private String name;
    
	public Long getIdRole() {
		return idRole;
	}
	
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
    
}
