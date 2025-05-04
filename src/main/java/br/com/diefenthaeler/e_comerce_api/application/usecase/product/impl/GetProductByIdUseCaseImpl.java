package br.com.diefenthaeler.e_comerce_api.application.usecase.product.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.ProductMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.GetProductByIdUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdUseCaseImpl implements GetProductByIdUseCase {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse execute(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductException.ProductNotFoundException("Product with id '" + id + "' not found"));

        return ProductMapper.toResponse(product);
    }
}
