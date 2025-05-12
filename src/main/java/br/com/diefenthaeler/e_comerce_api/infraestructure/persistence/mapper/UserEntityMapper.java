package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.user.User;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.UserEntity;

public class UserEntityMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}