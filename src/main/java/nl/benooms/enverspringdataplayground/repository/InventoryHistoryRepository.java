package nl.benooms.enverspringdataplayground.repository;

import nl.benooms.enverspringdataplayground.domain.Inventory;
import org.springframework.data.repository.history.RevisionRepository;

/**
 *
 * @author Ben Ooms
 */
public interface InventoryHistoryRepository extends RevisionRepository<Inventory, Long, Integer> {
}
