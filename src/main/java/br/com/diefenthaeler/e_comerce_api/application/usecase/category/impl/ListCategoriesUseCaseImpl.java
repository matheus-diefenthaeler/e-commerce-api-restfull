package br.com.diefenthaeler.e_comerce_api.application.usecase.category.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.PagedResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CategoryMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.ListCategoriesUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListCategoriesUseCaseImpl implements ListCategoriesUseCase {

    private final CategoryRepository categoryRepository;


    @Override
    public PagedResponse<CategoryResponse> execute(int page, int size) {
        Page<Category> categoryPage = categoryRepository.findAllPaged(page, size);

        List<CategoryResponse> content = categoryPage.getContent().stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toList());

        return buildPagedResponse(content, categoryPage, page, size, "/api/categories");
    }

    private PagedResponse<CategoryResponse> buildPagedResponse(
            List<CategoryResponse> content,
            Page<Category> page,
            int pageNumber,
            int pageSize,
            String basePath) {

        Map<String, String> links = new HashMap<>();

        // Self link
        links.put("self", buildPageLink(basePath, pageNumber, pageSize));

        // First page
        links.put("first", buildPageLink(basePath, 0, pageSize));

        // Last page
        int lastPage = page.getTotalPages() > 0 ? page.getTotalPages() - 1 : 0;
        links.put("last", buildPageLink(basePath, lastPage, pageSize));

        // Previous page
        if (pageNumber > 0) {
            links.put("prev", buildPageLink(basePath, pageNumber - 1, pageSize));
        }

        // Next page
        if (pageNumber < lastPage) {
            links.put("next", buildPageLink(basePath, pageNumber + 1, pageSize));
        }

        return PagedResponse.<CategoryResponse>builder()
                .content(content)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .links(links)
                .build();
    }

    private String buildPageLink(String basePath, int page, int size) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath)
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();
    }
}
