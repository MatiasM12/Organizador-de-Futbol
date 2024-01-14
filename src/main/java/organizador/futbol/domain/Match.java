package organizador.futbol.domain;

import java.time.LocalDate;
import lombok.Data;


@Data
public class Match {

    private Long idMatch;
    private LocalDate date;
    private String hour;
    private Long fieldId;
    
	public Long getIdMatch() {
		return idMatch;
	}
	
	public void setIdMatch(Long idMatch) {
		this.idMatch = idMatch;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
    
    
}
