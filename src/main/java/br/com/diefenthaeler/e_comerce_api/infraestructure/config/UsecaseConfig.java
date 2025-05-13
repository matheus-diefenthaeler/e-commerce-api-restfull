package br.com.diefenthaeler.e_comerce_api.infraestructure.config;

import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.CreateCustomerUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl.CreateCustomerUseCaseImpl;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.CreateProductUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.impl.CreateProductUseCaseImpl;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import br.com.diefenthaeler.e_comerce_api.domain.service.CartService;
import br.com.diefenthaeler.e_comerce_api.domain.service.impl.CartServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsecaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        return new CreateProductUseCaseImpl(productRepository, categoryRepository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder) {
        return new CreateCustomerUseCaseImpl(customerRepository, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CartService cartService(CartRepository cartRepository) {
        return new CartServiceImpl(cartRepository);
    }
}
