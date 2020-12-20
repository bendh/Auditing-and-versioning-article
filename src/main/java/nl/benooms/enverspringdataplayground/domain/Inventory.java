package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Ben Ooms
 */
@Data
@Entity @Audited
public class Inventory {
	@Id
	@GeneratedValue
	Long id;
	int quantityAvailable;
	int quantityBackorder;
	@NotNull
	@OneToOne @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	Product product;

}
