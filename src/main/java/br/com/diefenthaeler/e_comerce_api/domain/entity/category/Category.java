package br.com.diefenthaeler.e_comerce_api.domain.entity.category;

import java.util.Objects;

public class Category {

    private Integer id;
    private String name;
    private String slug;

    private Category() {}

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Category category;

        private Builder() {
            category = new Category();
        }

        public Builder withId(Integer id) {
            category.id = id;
            return this;
        }

        public Builder withName(String name) {
            category.name = name;
            return this;
        }

        public Builder withSlug(String slug) {
            category.slug = slug;
            return this;
        }

        public Category build() {
            validateCategory();
            return category;
        }

        private void validateCategory() {
            if (category.name == null || category.name.trim().isEmpty()) {
                throw new IllegalArgumentException("Category name cannot be empty");
            }

            if (category.slug == null || category.slug.trim().isEmpty()) {
                throw new IllegalArgumentException("Category slug cannot be empty");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
