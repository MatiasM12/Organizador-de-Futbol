package organizador.futbol.domain;

import lombok.Data;
import organizador.futbol.infrastructure.entities.MatchEntity;
import organizador.futbol.infrastructure.entities.UserEntity;

@Data
public class MatchParticipants {

    private Long idMatchParticipant;
    private UserEntity user;
    private MatchEntity match;
    
	public Long getIdMatchParticipant() {
		return idMatchParticipant;
	}
	public void setIdMatchParticipant(Long idMatchParticipant) {
		this.idMatchParticipant = idMatchParticipant;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public MatchEntity getMatch() {
		return match;
	}
	public void setMatch(MatchEntity match) {
		this.match = match;
	}
    
    
    
}