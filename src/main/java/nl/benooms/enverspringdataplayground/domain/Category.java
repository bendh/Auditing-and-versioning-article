package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Ben Ooms
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Category extends AbstractAuditedEntity{
	@Id
	@GeneratedValue
	Long id;
	@NotNull
	String name;
	@OneToMany
	Set<Product> products;
}
