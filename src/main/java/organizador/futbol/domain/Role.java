package organizador.futbol.domain;

import java.util.List;

import lombok.Data;

@Data
public class Role {
	
    private Long idRole;
    private String name;
    private List<User> users;
    
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
    
}
