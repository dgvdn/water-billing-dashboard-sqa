package com.example.waterbilling.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoa_don")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public int getSoNuocTieuThu() {
		return soNuocTieuThu;
	}

	public void setSoNuocTieuThu(int soNuocTieuThu) {
		this.soNuocTieuThu = soNuocTieuThu;
	}

	public String getTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(String trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

}
