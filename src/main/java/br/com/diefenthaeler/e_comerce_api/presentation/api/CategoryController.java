package br.com.diefenthaeler.e_comerce_api.presentation.api;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.CreateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.CreateCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.DeleteCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.GetCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = createCategoryUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String slug){
        deleteCategoryUseCase.execute(slug);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String slug){
        CategoryResponse response = getCategoryUseCase.execute(slug);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
