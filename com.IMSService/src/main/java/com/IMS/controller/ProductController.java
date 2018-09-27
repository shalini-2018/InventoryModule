package com.IMS.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.IMS.Repository.ProductRepository;
import com.IMS.beans.Product;


@RestController
@RequestMapping(value={"/product"})
public class ProductController {
	

	
	@Autowired
	ProductRepository productRepository;
	 // create product
	 @PostMapping("/create")
	 public ResponseEntity<Object> create(@RequestBody Product product) {
		 Product newProduct = productRepository.save(product);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		 			.buildAndExpand(newProduct.getId()).toUri();
	 	return ResponseEntity.created(location).build();

	 }
	 
	 //find all product
	 @GetMapping("/findAll")
	 public List<Product> retrieveAllproduct() {
	 	return productRepository.findAll();
	 }
	 
	 // find product using id
	 @GetMapping("/find/{id}")
	 public Product getProductById(@PathVariable long id) throws Exception {
	 	Optional<Product> product = productRepository.findById(id);

	 	if (!product.isPresent())
	 		throw new Exception("id-" + "does not exixt");

	 	return product.get();
	 }
	 
	 // delete product on basis of id
	 @DeleteMapping("/delete/{id}")
	 public void deleteProduct(@PathVariable long id) {
		 productRepository.deleteById(id);
	 }
	 
	 // update product on the basis of id
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

	 	Optional<Product> productOptional = productRepository.findById(id);

	 	if (!productOptional.isPresent())
	 		return ResponseEntity.notFound().build();

	 	product.setId(id);
	 	
	 	productRepository.save(product);

	 	return ResponseEntity.noContent().build();
	 }

	 
	/* @GetMapping("/updateQuantity/{id}")
	 public ResponseEntity<Object> updateProductQuantity(@RequestBody Product product, @PathVariable long id) {
		 
		 Boolean saleFlag=true;
		 if (saleFlag==true) {
			Optional<Product> productOptional = productRepository.findById(id);
			if (!productOptional.isPresent())
		 		return ResponseEntity.notFound().build();
		 	long quantityinDB= productOptional.get().getQuantity(); 	
		 	
		 	long newquantity=0;
		 	if(quantityinDB>1)
		 	newquantity=quantityinDB-1;
		 	productOptional.get().setQuantity(newquantity); 			 	
		 	

		 	return ResponseEntity.noContent().build();
		 }
		 else throw new E
		 }*/


}
