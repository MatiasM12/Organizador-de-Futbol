package organizador.futbol.domain;

import lombok.Data;

@Data
public class Notification {

    private Long idNotification;
    private String message;
    private Boolean isSeen;
    private String date;
    private User user;
    
	public Long getIdNotification() {
		return idNotification;
	}
	
	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}


	public Boolean getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(Boolean isSeen) {
		this.isSeen = isSeen;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    
}

