package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UOMType {
	@Id
	@GeneratedValue
	@Column(name="uom_id_col")
	private Integer id;
	private String uomType;
	private String uomModel;
	private String description;

}
