package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;

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
public class Product {
	@Id
	@GeneratedValue
	Long id;
	@NotNull
	String name;
	String description;
	@NotNull
	BigDecimal price;
}
