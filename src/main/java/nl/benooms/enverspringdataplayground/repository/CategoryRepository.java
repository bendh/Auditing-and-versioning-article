package nl.benooms.enverspringdataplayground.repository;

import nl.benooms.enverspringdataplayground.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ben Ooms
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
