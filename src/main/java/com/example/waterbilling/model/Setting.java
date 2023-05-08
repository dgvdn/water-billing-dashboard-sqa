package com.example.waterbilling.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settings")

public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_level")
	private Double fromLevel;

	@Column(name = "to_level")
	private Double toLevel;

	@Column(name = "price")
	private Double price;

	@Column(name = "ngay_tao")
	private LocalDate ngayTao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFromLevel() {
		return fromLevel;
	}

	public void setFromLevel(Double fromLevel) {
		this.fromLevel = fromLevel;
	}

	public Double getToLevel() {
		return toLevel;
	}

	public void setToLevel(Double toLevel) {
		this.toLevel = toLevel;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

}
