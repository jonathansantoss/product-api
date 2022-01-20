package com.jonathan.back.end.productapi.service;

import com.jonathan.back.end.productapi.converter.DTOConverter;
import com.jonathan.back.end.productapi.exception.CategoryNotFoundException;
import com.jonathan.back.end.productapi.exception.ProductNotFoundException;
import com.jonathan.back.end.productapi.model.Product;
import com.jonathan.back.end.productapi.repository.CategoryRepository;
import com.jonathan.back.end.productapi.repository.ProductRepository;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return DTOConverter.convertToList(products);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {
        boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }

        Product product = productRepository.save(DTOConverter.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public void delete(long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(value -> productRepository.delete(value));
    }

}
