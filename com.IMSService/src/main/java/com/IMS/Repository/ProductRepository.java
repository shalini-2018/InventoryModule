package com.IMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMS.beans.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
