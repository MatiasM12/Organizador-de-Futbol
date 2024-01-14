package organizador.futbol.domain;

import lombok.Data;

@Data
public class Field {

    private Long idField;
    private String name;
    private String photo;
    private String mail;
    private String phone;
    private Long userId;
    
	public Long getIdField() {
		return idField;
	}
	
	public void setIdField(Long idField) {
		this.idField = idField;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
