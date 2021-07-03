package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="wh_user_type_tab")
public class WhUserType {

	@Id
	@GeneratedValue(generator="whusertype_seq")
	@SequenceGenerator(name="whusertype_seq",sequenceName = "whusertype_seq")
	@Column(name = "wh_usr_id")
	private Integer id;
	private String userType;
	private String userCode;
	private String userFor;
	private String userMail;
	private String userContact;
	private String userIdType;
	private String ifOther;
	private String idNumber;
	
	 
	
}
