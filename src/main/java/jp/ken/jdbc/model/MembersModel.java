package jp.ken.jdbc.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import jp.ken.jdbc.annotation.DateFormat;
import jp.ken.jdbc.group.Group1;

public class MembersModel implements Serializable {
	@NotEmpty(message = "氏名が未入力です")
	private String name;
	@NotEmpty(message = "Emailが未入力です")
	@Email(message = "Emailを正しく表示してください",groups = Group1.class)
	private String email;
	@NotEmpty(message = "電話番号が未入力です")
	@Pattern(regexp = "0[0-9]{9,10}",message = "電話番号を正しく入力してください",groups = Group1.class)
	private String phoneNumber;
	@NotEmpty(message = "誕生日が未入力です")
	@DateFormat(message ="誕生日を正しく入力してください", groups = Group1.class)
	private String birthday;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
