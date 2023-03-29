package com.example.waterbilling.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.waterbilling.model.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

	@Query("SELECT k.email FROM KhachHang k WHERE k.email LIKE %:query%")
	List<String> findEmail(@Param("query") String query);

	KhachHang findByEmail(String email);

	List<KhachHang> findByTenContainingOrSoHopDongContaining(String search, String search2);

	List<KhachHang> findByTenStartingWithIgnoreCase(String filter, Sort by);

}
