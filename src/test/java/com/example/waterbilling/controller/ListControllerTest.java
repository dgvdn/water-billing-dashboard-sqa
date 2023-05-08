package com.example.waterbilling.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.repository.KhachHangRepository;
import com.example.waterbilling.service.HoaDonService;

public class ListControllerTest {

	@Mock
	private HoaDonService hoaDonService;

	@Mock
	private Model model;

	@Mock
	private KhachHangRepository khachHangRepository;

	@InjectMocks
	private ListController listController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetListHoaDonWithNullSearch() {
		// Setup
		String status = "Đã thanh toán";
		String search = null;
		List<HoaDon> expectedList = new ArrayList<>();
		expectedList.add(new HoaDon());
		when(hoaDonService.getListHoaDon(status, search)).thenReturn(expectedList);

		// Execute
		String viewName = listController.getListHoaDon(status, search, model);

		// Verify
		assertEquals("bill", viewName);
		verify(model).addAttribute("listHoaDon", expectedList);
	}

	@Test
	public void testGetListHoaDonWithEmptySearch() {
		// Setup
		String status = "Đã thanh toán";
		String search = "";
		List<HoaDon> expectedList = new ArrayList<>();
		expectedList.add(new HoaDon());
		when(hoaDonService.getListHoaDon(status, search)).thenReturn(expectedList);

		// Execution
		String viewName = listController.getListHoaDon(status, search, model);

		// Verification
		verify(hoaDonService).getListHoaDon(status, search);
		verify(model).addAttribute("listHoaDon", expectedList);
		assertEquals("bill", viewName);

	}

	@Test
	public void testGetListHoaDonWithInvalidSearch() {
		// Setup
		String status = "Đã thanh toán";
		String search = "abc"; // invalid search parameter
		List<HoaDon> expectedList = new ArrayList<>();
		when(hoaDonService.getListHoaDon(status, search)).thenReturn(expectedList);

		// Execute
		String viewName = listController.getListHoaDon(status, search, model);

		// Verify
		assertEquals("bill", viewName);
		verify(model).addAttribute("listHoaDon", expectedList);
	}

	@Test
	public void testListCustomersWithNullSearch() {
		String search = null;
		List<KhachHang> expectedCustomers = new ArrayList<>();
		expectedCustomers.add(new KhachHang());
		expectedCustomers.add(new KhachHang());
		when(khachHangRepository.findAll(Sort.by("ten"))).thenReturn(expectedCustomers);

		// Execute
		String viewName = listController.listCustomers(search, model);

		// Verify
		assertEquals("customer", viewName);
		verify(model).addAttribute("customers", expectedCustomers);
		verify(model).addAttribute("search", null);
	}

	@Test
	public void testListCustomersWithEmptySearch() {
		// Setup
		String search = "";
		List<KhachHang> expectedCustomers = new ArrayList<>();
		expectedCustomers.add(new KhachHang());
		when(khachHangRepository.findAll(Sort.by("ten"))).thenReturn(expectedCustomers);

		// Execution
		String viewName = listController.listCustomers(search, model);

		// Verification
		verify(khachHangRepository).findAll(Sort.by("ten"));
		verify(model).addAttribute("customers", expectedCustomers);
		verify(model).addAttribute("search", "");
		assertEquals("customer", viewName);
	}

	@Test
	public void testListCustomersWithValidSearch() {
		// Setup
		String search = "John";
		List<KhachHang> expectedCustomers = new ArrayList<>();
		KhachHang johnCustomer = new KhachHang();
		johnCustomer.setTen("John Smith");
		expectedCustomers.add(johnCustomer);
		when(khachHangRepository.findByTenContainingOrSoHopDongContaining(search, search))
				.thenReturn(expectedCustomers);

		// Execution
		String viewName = listController.listCustomers(search, model);

		// Verification
		verify(khachHangRepository).findByTenContainingOrSoHopDongContaining(search, search);
		verify(model).addAttribute("customers", expectedCustomers);
		verify(model).addAttribute("search", "John");
		assertEquals("customer", viewName);
	}

	@Test
	public void testGetListHoaDonWithValidSearch() {
		// Setup
		String status = "Đã thanh toán";
		String search = "John";
		List<HoaDon> expectedList = new ArrayList<>();
		KhachHang johnCustomer = new KhachHang();
		johnCustomer.setTen("John Smith");
		johnCustomer.setSoHopDong("HD-01");
		HoaDon johnHoaDon = new HoaDon();
		johnHoaDon.setKhachHang(johnCustomer);
		johnHoaDon.setTrangThaiThanhToan("Đã thanh toán");
		KhachHang janeCustomer = new KhachHang();
		janeCustomer.setTen("Jane Smith");
		janeCustomer.setSoHopDong("HD-02");
		HoaDon janeHoaDon = new HoaDon();
		janeHoaDon.setKhachHang(janeCustomer);
		janeHoaDon.setTrangThaiThanhToan("Đã thanh toán");
		expectedList.add(johnHoaDon);
		expectedList.add(janeHoaDon);
		when(hoaDonService.getListHoaDon(status, search)).thenReturn(expectedList);

		// Execute
		String viewName = listController.getListHoaDon(status, search, model);

		// Verify
		assertEquals("bill", viewName);
		verify(model).addAttribute("listHoaDon", expectedList.stream()
				.filter(hoaDon -> hoaDon.getKhachHang().getTen().contains(search)).collect(Collectors.toList()));
	}

}
