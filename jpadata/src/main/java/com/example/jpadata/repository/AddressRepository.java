package com.example.jpadata.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jpadata.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
