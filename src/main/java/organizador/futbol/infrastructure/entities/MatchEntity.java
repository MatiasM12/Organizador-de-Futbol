package organizador.futbol.infrastructure.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "football_match")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;
    
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<MatchParticipantsEntity> matchParticipants;
    
    private String date;
    private String hour;
    private String title;
    @ManyToOne
    @JoinColumn(name = "fieldId")
    private FieldEntity fieldId;


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

	public FieldEntity getFieldId() {
		return fieldId;
	}

	public void setFieldId(FieldEntity fieldId) {
		this.fieldId = fieldId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

