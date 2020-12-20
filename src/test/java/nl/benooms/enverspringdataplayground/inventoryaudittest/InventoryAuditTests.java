package nl.benooms.enverspringdataplayground.inventoryaudittest;

import nl.benooms.enverspringdataplayground.domain.Inventory;
import nl.benooms.enverspringdataplayground.domain.Product;
import nl.benooms.enverspringdataplayground.repository.InventoryHistoryRepository;
import nl.benooms.enverspringdataplayground.repository.InventoryRepository;
import nl.benooms.enverspringdataplayground.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.RevisionMetadata;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Ben Ooms
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class InventoryAuditTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private InventoryHistoryRepository inventoryHistoryRepository;

	@BeforeEach
	private void clearDB() {
		inventoryRepository.deleteAllInBatch();
		productRepository.deleteAllInBatch();
	}

	@Test
	public void testNewlyCreatedInventoryIsAudited() {
		// We need a saved product and inventory for this testcase
		var testProduct = createTestProduct();
		int quantityAvailable = 10;
		var testInventory = new Inventory();
		testInventory.setProduct(testProduct);
		testInventory.setQuantityAvailable(quantityAvailable);

		var result = inventoryRepository.save(testInventory);
		var historyResult = inventoryHistoryRepository.findLastChangeRevision(result.getId());

		assertThat(result.getId()).isNotNull();
		historyResult.ifPresentOrElse(inventoryRevision -> {
			var revisionType = inventoryRevision.getMetadata().getRevisionType();
			assertThat(revisionType).isEqualTo(RevisionMetadata.RevisionType.INSERT);
			assertThat(inventoryRevision.getEntity()).isEqualTo(result);
		}, () -> fail("Should have found history record"));
	}

	@Test
	public void testProductChangedInInventoryIsAudited() {
		// We need a saved product and inventory for this testcase
		var testProduct1 = createTestProduct();
		var testProduct2 = createTestProduct();
		int quantityAvailable = 10;
		var testInventory = new Inventory();
		testInventory.setProduct(testProduct1);
		var input = inventoryRepository.save(testInventory);

		input.setProduct(testProduct2);
		var result = inventoryRepository.save(input);
		var historyResult = inventoryHistoryRepository.findLastChangeRevision(result.getId());
		var allHistoryResult = inventoryHistoryRepository.findRevisions(result.getId());

		assertThat(result.getId()).isNotNull();
		historyResult.ifPresentOrElse(inventoryRevision -> {
			var revisionType = inventoryRevision.getMetadata().getRevisionType();
			assertThat(revisionType).isEqualTo(RevisionMetadata.RevisionType.UPDATE);
			assertThat(inventoryRevision.getEntity()).isEqualTo(result);
			var productSubject = inventoryRevision.getEntity().getProduct();
			assertThat(productSubject).isEqualTo(testProduct2);
		}, () -> fail("Should have found history record"));
		assertThat(allHistoryResult).hasSize(2);
	}

	@Test
	public void testInventoryHistoryIsFoundAfterInventoryIsDeleted() {
		// We need a saved product and inventory for this testcase
		var testProduct1 = createTestProduct();
		int quantityAvailable = 10;
		var testInventory = new Inventory();
		testInventory.setProduct(testProduct1);
		testInventory.setQuantityAvailable(quantityAvailable);

		var result = inventoryRepository.save(testInventory);
		inventoryRepository.delete(result);
		var historyResult = inventoryHistoryRepository.findLastChangeRevision(result.getId());
		var allHistoryResult = inventoryHistoryRepository.findRevisions(result.getId());

		historyResult.ifPresentOrElse(inventoryRevision -> {
			var revisionType = inventoryRevision.getMetadata().getRevisionType();
			assertThat(revisionType).isEqualTo(RevisionMetadata.RevisionType.DELETE);
			// No changes are made in a deletion so all fields are null or 0;
			assertThat(inventoryRevision.getEntity().getProduct()).isNull();
			assertThat(inventoryRevision.getEntity().getQuantityAvailable()).isEqualTo(0);
		}, () -> fail("Should have found history record"));
		assertThat(allHistoryResult).hasSize(2);
	}

	private Product createTestProduct() {
		var testProduct = new Product();
		testProduct.setName("Test product name");
		testProduct.setDescription("Test product description");
		testProduct.setPrice(new BigDecimal("2.50"));
		return productRepository.save(testProduct);
	}

}
