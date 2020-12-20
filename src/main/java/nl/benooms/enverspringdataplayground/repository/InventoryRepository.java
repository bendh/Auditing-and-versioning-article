package nl.benooms.enverspringdataplayground.repository;

import nl.benooms.enverspringdataplayground.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
