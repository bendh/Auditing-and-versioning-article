package nl.benooms.enverspringdataplayground.repository;

import nl.benooms.enverspringdataplayground.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ben Ooms
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
