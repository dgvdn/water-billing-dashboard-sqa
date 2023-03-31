package com.example.waterbilling.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.waterbilling.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	@Query("SELECT s FROM Setting s")
	List<Setting> findAll();
	
	@Query("INSERT INTO Setting s (id, fromLevel, toLevel, price) VALUES (:id, :fromLevel, :toLevel, :price)")
    void insert(Long id, Double fromLevel, Double toLevel, Double price);
	
	@Query("update Setting s set s.fromLevel = :fromLevel, s.toLevel = :toLevel, s.price = :price where s.id = :id")
	void update(Long id, Double fromLevel, Double toLevel, Double price);
}
