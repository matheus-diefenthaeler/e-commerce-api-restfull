package br.com.diefenthaeler.e_comerce_api.presentation.api;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.CreateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.CreateCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponse> createProduct(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = createCategoryUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
