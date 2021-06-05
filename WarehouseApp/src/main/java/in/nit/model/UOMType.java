package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UOMType {
	@Id
	@GeneratedValue
	private Integer id;
	private String uomType;
	private String uomModel;
	private String description;

}
