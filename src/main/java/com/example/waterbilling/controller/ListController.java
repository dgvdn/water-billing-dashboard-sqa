package com.example.waterbilling.controller;

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
@RequestMapping("/list")
public class ListController {

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private HoaDonService donService;

	@Autowired
	private HoaDonRepository donRepository;

	@GetMapping("")
	public String list() {
		return "list";
	}

	@GetMapping("/bill")
	public String getListHoaDon(@RequestParam(required = false) String status,
			@RequestParam(required = false) String search, Model model) {
		List<HoaDon> listHoaDon = donService.getListHoaDon(status, search);
		if (search != null && !search.isEmpty()) {
			listHoaDon = listHoaDon.stream().filter(hoaDon -> hoaDon.getKhachHang().getTen().contains(search)
					|| hoaDon.getKhachHang().getSoHopDong().contains(search)).collect(Collectors.toList());
		}
		model.addAttribute("listHoaDon", listHoaDon);
		return "bill";
	}

	@GetMapping("/customer")
	public String listCustomers(@RequestParam(name = "search", required = false) String search, Model model) {

		List<KhachHang> customers;

		if (search != null && !search.isBlank()) {
			// search by name or contract number
			customers = khachHangRepository.findByTenContainingOrSoHopDongContaining(search, search);

		} else {
			// show all customers sorted by name
			customers = khachHangRepository.findAll(Sort.by("ten"));
		}

		model.addAttribute("customers", customers);
		model.addAttribute("search", search);

		return "customer";
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
