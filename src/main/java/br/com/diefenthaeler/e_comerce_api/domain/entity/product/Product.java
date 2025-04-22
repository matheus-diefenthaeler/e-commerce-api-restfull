package br.com.diefenthaeler.e_comerce_api.domain.entity.product;

import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private List<Category> categories;

    private Product() {
        this.categories = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //defensive copy
    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public static class Builder {
        private final Product product;

        private Builder() {
            product = new Product();
        }

        public Builder withName(String name) {
            product.name = name;
            return this;
        }

        public Builder withSlug(String slug) {
            product.slug = slug;
            return this;
        }

        public Builder withDescription(String description) {
            product.description = description;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            product.price = price;
            return this;
        }

        public Builder withCategories(List<Category> categories) {
            product.categories = new ArrayList<>(categories);
            return this;
        }

        public Product build() {
            validateProduct();
            return product;
        }

        private void validateProduct() {
            if (product.name == null || product.name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty");
            }

            if (product.slug == null || product.slug.trim().isEmpty()) {
                throw new IllegalArgumentException("Product slug cannot be empty");
            }

            if (product.price == null || product.price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Product price must be greater than zero");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
