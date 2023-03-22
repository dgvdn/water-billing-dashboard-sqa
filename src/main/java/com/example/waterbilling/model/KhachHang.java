package com.example.waterbilling.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "khach_hang")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sdt")
	private String sdt;

	@Column(name = "ten")
	private String ten;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "so_hop_dong")
	private String soHopDong;

	@Column(name = "email")
	private String email;

	// constructors, getters and setters
}
