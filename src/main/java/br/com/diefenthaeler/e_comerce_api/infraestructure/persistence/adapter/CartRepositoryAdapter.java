package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.adapter;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper.CartEntityMapper;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaCartItemRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartRepositoryAdapter implements CartRepository {

    private final JpaCartRepository jpaCartRepository;
    private final JpaCartItemRepository jpaCartItemRepository;

    @Override
    public Optional<Cart> findByUuid(String uuid) {
        return jpaCartRepository.findByUuid(uuid)
                .map(CartEntityMapper::toDomain);
    }

    @Override
    public Optional<Cart> findByCustomerId(Long customerId) {
        return jpaCartRepository.findByCustomerId(customerId)
                .map(CartEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Cart save(Cart cart) {
        CartEntity entity = CartEntityMapper.toEntity(cart);
        CartEntity savedEntity = jpaCartRepository.save(entity);
        return CartEntityMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public void deleteByUuid(String uuid) {
        jpaCartRepository.deleteByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {
        jpaCartItemRepository.deleteById(cartItemId);
    }
}
