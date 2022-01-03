package com.switchfully.item;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
public class ItemRepositoryJpa implements ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addItem(Item item) {
        entityManager.persist(item);
    }

    @Override
    public Optional<Item> findItemById(UUID id) {
        Item item = entityManager.find(Item.class, id);
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }

    @Override
    public List<Item> getAllItems() {
        return entityManager.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
