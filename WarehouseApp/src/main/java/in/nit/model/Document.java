package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Document {
	@Id
	private Integer docId;
	private String docName;
	
	@Lob     // byte[]+@Lob=BLOB
	private byte[] docData;

}
