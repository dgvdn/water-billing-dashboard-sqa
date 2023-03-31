package com.example.waterbilling.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.waterbilling.model.HoaDon;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findEmailsByTrangThaiThanhToan(String trangThaiThanhToan);

	List<HoaDon> findByTrangThaiThanhToan(String status);

	List<HoaDon> findByKhachHangId(Long customerId);
	
	@Query("SELECT h FROM HoaDon h WHERE h.ngayTao >= :dateFrom and h.ngayTao <= :dateTo ORDER BY h.soNuocTieuThu")
	List<HoaDon> findDonsOrderByNuoc(LocalDate dateFrom, LocalDate dateTo);
	
	@Query("SELECT h FROM HoaDon h WHERE h.ngayTao >= :dateFrom and h.ngayTao <= :dateTo ORDER BY h.tongTien")
	List<HoaDon> findDonsOrderByTien(LocalDate dateFrom, LocalDate dateTo);
}
