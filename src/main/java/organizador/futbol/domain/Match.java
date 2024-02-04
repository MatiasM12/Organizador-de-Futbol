package organizador.futbol.domain;


import java.util.List;

import lombok.Data;


@Data
public class Match {

    private Long idMatch;
    private String date;
    private String hour;
    private String title;
    private List<MatchParticipants> matchParticipants;
    private Field fieldId;
    
	public Long getIdMatch() {
		return idMatch;
	}
	
	public void setIdMatch(Long idMatch) {
		this.idMatch = idMatch;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}

	public Field getFieldId() {
		return fieldId;
	}

	public void setFieldId(Field fieldId) {
		this.fieldId = fieldId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MatchParticipants> getMatchParticipants() {
		return matchParticipants;
	}

	public void setMatchParticipants(List<MatchParticipants> matchParticipants) {
		this.matchParticipants = matchParticipants;
	}
    
    
}
