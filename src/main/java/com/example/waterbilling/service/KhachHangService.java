package com.example.waterbilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.repository.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;

	public KhachHang findCustomerByEmail(String email) {
		return khachHangRepository.findByEmail(email);
	}
}
