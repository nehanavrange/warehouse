package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="shipment_type_tab")
public class ShipmentType {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String shipmentMode;
	private String shipmentCode;
	private String enableShipment;
	private String shipmentGrade;
	private String description;

}
