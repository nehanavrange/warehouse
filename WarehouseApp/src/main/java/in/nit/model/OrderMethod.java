package in.nit.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OrderMethod {
	@Id
	@GeneratedValue
	private Integer id;
	private String orderMode;
	private String orderCode;
	private String orderType;
	@ElementCollection
	private List<String> orderAccept;
	private String description;

}
