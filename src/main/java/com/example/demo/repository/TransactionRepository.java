package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startdate, LocalDate enddate);
//	@Query(value = "select  from Transaction  where customerId =?1",nativeQuery = true)
//	String findBycustomerId(String customerId);

}
