package com.example.waterbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.waterbilling.model.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

}