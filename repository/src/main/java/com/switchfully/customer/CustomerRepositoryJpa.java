package com.switchfully.customer;

import com.switchfully.user.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer findCustomerById(UUID id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return entityManager.createQuery("select c from Customer c where c.email = :email", Customer.class)
                .setParameter("email", email)
                .getResultStream().findAny();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }
}
