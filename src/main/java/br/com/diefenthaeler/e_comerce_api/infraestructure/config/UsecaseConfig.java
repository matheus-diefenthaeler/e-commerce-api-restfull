package br.com.diefenthaeler.e_comerce_api.infraestructure.config;

import br.com.diefenthaeler.e_comerce_api.application.usecase.product.CreateProductUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.impl.CreateProductUseCaseImpl;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsecaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        return new CreateProductUseCaseImpl(productRepository, categoryRepository);
    }
}
