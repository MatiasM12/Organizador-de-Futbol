package organizador.futbol.infrastructure.entities;

import java.time.LocalDate;
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

    private LocalDate date;
    private String hour;

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

}
