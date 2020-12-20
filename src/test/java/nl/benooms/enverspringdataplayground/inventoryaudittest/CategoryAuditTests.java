package nl.benooms.enverspringdataplayground.inventoryaudittest;

import nl.benooms.enverspringdataplayground.domain.Category;
import nl.benooms.enverspringdataplayground.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
/**
 *
 * @author Ben Ooms
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CategoryAuditTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@BeforeEach
	private void clearDatabase() {
		categoryRepository.deleteAllInBatch();
	}

	@Test
	public void testAUditingInformationIsSetWhenSavingNewCategory() {
		var testCategory = new Category();
		testCategory.setName("test category");

		var result = categoryRepository.save(testCategory);

		var user = "Gebruiker";
		assertThat(result.getCreatedByUser()).isEqualTo(user);
		assertThat(result.getCreatedDate()).isNotNull();
		assertThat(result.getLastModifiedByUser()).isEqualTo(user);
		assertThat(result.getLastModifiedDate()).isEqualTo(result.getCreatedDate());
	}

	@Test
	public void testAuditingInformationIsSetWhenUpdatingCategory() throws InterruptedException {
		var testCategory = new Category();
		testCategory.setName("test category");
		testCategory = categoryRepository.save(testCategory);
		Thread.sleep(1000L);
		testCategory.setName("test category modified");

		var result = categoryRepository.save(testCategory);

		var user = "Gebruiker";
		assertThat(result.getCreatedByUser()).isEqualTo(user);
		assertThat(result.getCreatedDate()).isNotNull();
		assertThat(result.getLastModifiedByUser()).isEqualTo(user);
		assertThat(result.getLastModifiedDate()).isAfter(result.getCreatedDate());
	}
}
