package br.com.diefenthaeler.e_comerce_api.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Integer id;
    private String name;
    private String slug;
}
