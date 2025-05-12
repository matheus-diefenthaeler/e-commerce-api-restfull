package br.com.diefenthaeler.e_comerce_api.presentation.api;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.CreateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.UpdateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.CreateProductUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.DeleteProductUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.GetProductByIdUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.UpdateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = createProductUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse response = getProductByIdUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        deleteProductUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProductRequest request) {
        ProductResponse response = updateProductUseCase.execute(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
