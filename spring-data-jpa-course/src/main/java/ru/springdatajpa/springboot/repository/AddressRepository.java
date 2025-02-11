package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {


}
