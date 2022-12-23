package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "select p.name " +
            "from products p " +
            "inner join providers p2 on p.id_providers = p2.id " +
            "where p.amount between :min and :max " +
            "and p2.name like CONCAT(:beginName, '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String beginName);

    @Query("select new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " +
            "from Product obj " +
            "where obj.amount between :min and :max " +
            "and obj.provider.name like CONCAT(:beginName, '%')")
    List<ProductMinDTO> search2(Integer min, Integer max, String beginName);




}
