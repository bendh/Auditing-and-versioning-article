package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Ben Ooms
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Product  extends AbstractAuditedEntity{
	@Id
	@GeneratedValue
	Long id;
	@Audited
	@NotNull
	String name;
	String description;
	@Audited
	@NotNull
	BigDecimal price;
}
