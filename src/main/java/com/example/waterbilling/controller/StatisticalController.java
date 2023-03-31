package com.example.waterbilling.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.repository.HoaDonRepository;
import com.example.waterbilling.repository.KhachHangRepository;
import com.example.waterbilling.service.HoaDonService;

@Controller
@RequestMapping("/statistical")
public class StatisticalController {

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private HoaDonService donService;

	@Autowired
	private HoaDonRepository donRepository;

	@GetMapping("")
	public String list() {
		return "statistical-menu";
	}

	@GetMapping("/byWater")
	public String getListWater(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to, Model model) {
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		if(from != null && to != null) {
			LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			listHoaDon = donRepository.findDonsOrderByNuoc(dateFrom, dateTo);
		}
		model.addAttribute("listHoaDon", listHoaDon);
		return "statistical-water";
	}

	@GetMapping("/byPrice")
	public String getListPrice(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to, Model model) {
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		if(from != null && to != null) {
			LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			listHoaDon = donRepository.findDonsOrderByTien(dateFrom, dateTo);
		}
		model.addAttribute("listHoaDon", listHoaDon);
		return "statistical-price";
	}

	@GetMapping("/customer/{customerId}")
	public String getCustomerAndBills(@PathVariable Long customerId, Model model) {
		KhachHang khachHang = khachHangRepository.findById(customerId).orElseThrow();
		List<HoaDon> hoaDonList = donRepository.findByKhachHangId(customerId);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("hoaDonList", hoaDonList);
		return "customer-bills";
	}
}
