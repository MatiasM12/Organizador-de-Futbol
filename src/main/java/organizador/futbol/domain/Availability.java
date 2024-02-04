package organizador.futbol.domain;

import lombok.Data;

@Data
public class Availability{

    private Long idAvailability;
    private String month;
    private String day;
    private String hour;
    private Boolean isRented;
    private Field field;
    
    
	public Long getIdAvailability() {
		return idAvailability;
	}

	public void setIdAvailability(Long idAvailability) {
		this.idAvailability = idAvailability;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Boolean getIsRented() {
		return isRented;
	}

	public void setIsRented(Boolean isRented) {
		this.isRented = isRented;
	}

}
