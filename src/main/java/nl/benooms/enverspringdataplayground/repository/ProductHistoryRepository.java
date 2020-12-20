package nl.benooms.enverspringdataplayground.repository;

import nl.benooms.enverspringdataplayground.domain.Product;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * @author Ben Ooms
 */
public interface ProductHistoryRepository extends RevisionRepository<Product, Long, Integer> {
}
