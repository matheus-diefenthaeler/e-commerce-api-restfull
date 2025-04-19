package br.com.diefenthaeler.e_comerce_api.domain.entity.category;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

public class Category {

    private Long id;
    private String name;
    private String slug;

    public Category(Long id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
