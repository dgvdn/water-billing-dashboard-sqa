package com.example.waterbilling.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoa_don")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tong_tien")
	private double tongTien;

	@Column(name = "so_nuoc_tieu_thu")
	private int soNuocTieuThu;

	@Column(name = "trang_thai_thanh_toan")
	private String trangThaiThanhToan;

	@Column(name = "ngay_tao")
	private LocalDate ngayTao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khach_hang_id")
	private KhachHang khachHang;
}
