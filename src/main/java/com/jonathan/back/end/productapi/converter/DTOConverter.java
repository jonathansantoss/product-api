package com.jonathan.back.end.productapi.converter;

import com.jonathan.back.end.productapi.model.Category;
import com.jonathan.back.end.productapi.model.Product;
import dto.CategoryDTO;
import dto.ProductDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    public static CategoryDTO convert(Category category) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static Category convert(CategoryDTO categoryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoryDTO, Category.class);
    }

    public static ProductDTO convert(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTO.class);
    }

    public static Product convert(ProductDTO productDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(productDTO, Product.class);
    }

    public static List<ProductDTO> convertToList(List<Product> products) {
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }
}
