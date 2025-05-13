package br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.PagedResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CustomerMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.ListCustomersUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
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
public class ListCustomersUseCaseImpl implements ListCustomersUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public PagedResponse<CustomerResponse> execute(int page, int size) {
        Page<Customer> customerPage = customerRepository.findAllPaged(page, size);

        List<CustomerResponse> content = customerPage.getContent().stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());

        return buildPagedResponse(content, customerPage, page, size, "/api/customers");
    }

    private PagedResponse<CustomerResponse> buildPagedResponse(
            List<CustomerResponse> content,
            Page<Customer> page,
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

        return PagedResponse.<CustomerResponse>builder()
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