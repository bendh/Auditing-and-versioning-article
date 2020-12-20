package nl.benooms.enverspringdataplayground.inventoryaudittest;

import nl.benooms.enverspringdataplayground.domain.Product;
import nl.benooms.enverspringdataplayground.repository.ProductHistoryRepository;
import nl.benooms.enverspringdataplayground.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.RevisionMetadata;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
/**
 *
 * @author Ben Ooms
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductAuditTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductHistoryRepository productHistoryRepository;

	@Test
	public void testNewProductHistoryIsSaved() {
		var testProduct = new Product();
		testProduct.setName("test product 1");
		testProduct.setDescription("Some nice test product 1");
		testProduct.setPrice(new BigDecimal("1.00"));

		var result = productRepository.save(testProduct);
		var latestRevision = productHistoryRepository.findLastChangeRevision(result.getId());

		latestRevision.ifPresentOrElse(inventoryRevision -> {
			var revisionType = inventoryRevision.getMetadata().getRevisionType();
			assertThat(revisionType).isEqualTo(RevisionMetadata.RevisionType.INSERT);
			var entityInformation = inventoryRevision.getEntity();
			assertThat(entityInformation.getId()).isEqualTo(result.getId());
			assertThat(entityInformation.getName()).isEqualTo(result.getName());
			assertThat(entityInformation.getPrice()).isEqualTo(result.getPrice());
			assertThat(entityInformation.getDescription()).isNull();
		}, () -> fail("Should have found history record"));
	}
}
