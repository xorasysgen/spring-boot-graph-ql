package api.graph.ql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Stock {
	
	
	@Id
	private Integer id;
	private String name; 
	private Integer open;
	private Integer high;
	private Integer low;
	private Integer close;
	private Integer ltp;

}
