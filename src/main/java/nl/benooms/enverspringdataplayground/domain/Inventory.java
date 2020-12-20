package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Ben Ooms
 */
@Data
@Entity
public class Inventory {
	@Id
	Long id;
	int quantityAvailable;
	int quantityBackorder;
	@NotNull
	@OneToOne
	Product product;

}
