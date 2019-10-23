package com.thefauxpho.The.Faux.Pho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thefauxpho.The.Faux.Pho.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
