package com.poc.elastic_db_ms_it;

import com.poc.elastic_db_ms_it.model.Product;
import com.poc.elastic_db_ms_it.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ElasticDbMsItApplicationTests  {

	@Container
private  static final ElasticsearchContainer elasticSearchContiner =
			new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.17.10"))
					.withEnv("discovery.type", "single-node") // Required for standalone mode
					.withEnv("xpack.security.enabled", "false") // Disable security for testing
					.withEnv("ES_JAVA_OPTS", "-Xms512m -Xmx512m") // Reduce memory usage
					.waitingFor(org.testcontainers.containers.wait.strategy.Wait.forLogMessage(".*started.*", 1));
	@Autowired
	private ProductService productService;

	@BeforeAll
	public static void startUp() {
		elasticSearchContiner.start();
		System.setProperty("spring.elasticsearch.uris", elasticSearchContiner.getHttpHostAddress());
	}

	@AfterAll
	public static void tearDown() {
		elasticSearchContiner.stop();
	}
	@Test
	void contextLoads() {
	}

	@Test
	public void testElasticSearchContainer() {
		assertNotNull(elasticSearchContiner.getHttpHostAddress());
	}

	@Test
	public void testProductService() {
		Product product = new Product() ;
		product.setName("test product");
		product.setPrice(10.99);

		Product savedProduct = productService.saveProduct(product);
		assertNotNull(savedProduct.getId());

		Product fromDb = productService.getProductById(savedProduct.getId()).orElse(null);
		assertNotNull(fromDb);

	}
}
