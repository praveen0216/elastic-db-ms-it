package com.poc.elastic_db_ms_it.repository;

import com.poc.elastic_db_ms_it.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
