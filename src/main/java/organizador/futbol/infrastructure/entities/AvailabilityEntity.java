package organizador.futbol.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "availability")
public class AvailabilityEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvailability;

    private String month;
    private String day;
    private String hour;
    private Boolean isRented;

    @ManyToOne
    @JoinColumn(name = "idField")
    private FieldEntity field;
    
    
	public Long getIdAvailability() {
		return idAvailability;
	}

	public void setIdAvailability(Long idAvailability) {
		this.idAvailability = idAvailability;
	}

	public FieldEntity getField() {
		return field;
	}

	public void setField(FieldEntity field) {
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
