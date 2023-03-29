package com.example.waterbilling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.waterbilling.model.HoaDon;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findEmailsByTrangThaiThanhToan(String trangThaiThanhToan);

	List<HoaDon> findByTrangThaiThanhToan(String status);

	List<HoaDon> findByKhachHangId(Long customerId);
}
