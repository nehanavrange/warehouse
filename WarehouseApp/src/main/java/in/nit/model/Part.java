package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="part_tab")
public class Part {
	
	@Id
	@GeneratedValue
	private  Integer id;
	
	private String partCode;
	private String partWidth;
	private String partLen;
	private String partHgh;
	private String baseCost;
	private String baseCurr;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="uom_id_col_fk")
	private UOMType uomFk;  //has-a
	

	
}
