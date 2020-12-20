package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Ben Ooms
 */
@Data
@Entity
public class Category {
	@Id
	Long id;
	@NotNull
	String name;
	@OneToMany
	Set<Product> products;
}
