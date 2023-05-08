package com.example.waterbilling.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.repository.HoaDonRepository;
import com.example.waterbilling.repository.KhachHangRepository;
import com.example.waterbilling.service.HoaDonService;

public class StatisticalControllerTest {

	@Mock
	private KhachHangRepository khachHangRepository;

	@Mock
	private HoaDonService hoaDonService;

	@Mock
	private HoaDonRepository hoaDonRepository;

	@Mock
	private Model model;

	@InjectMocks
	private StatisticalController statisticalController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetListWaterWithNoDates() {
		// Setup
		List<HoaDon> expectedHoaDons = new ArrayList<>();
		when(hoaDonService.getListHoaDon(null, "")).thenReturn(expectedHoaDons);

		// Execution
		String viewName = statisticalController.getListWater(null, null, model);

		// Verification
		verify(hoaDonService).getListHoaDon(null, "");
		verify(model).addAttribute("listHoaDon", expectedHoaDons);
		assert (viewName.equals("statistical-water"));
	}

	@Test
	public void testGetListWaterWithDates() {
		// Setup
		LocalDate from = LocalDate.of(2022, 1, 1);
		LocalDate to = LocalDate.of(2022, 12, 31);
		String fromDate = from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String toDate = to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		List<HoaDon> expectedHoaDons = new ArrayList<>();
		when(hoaDonRepository.findDonsOrderByNuoc(from, to)).thenReturn(expectedHoaDons);

		// Execution
		String viewName = statisticalController.getListWater(fromDate, toDate, model);

		// Verification
		verify(hoaDonRepository).findDonsOrderByNuoc(from, to);
		verify(model).addAttribute("listHoaDon", expectedHoaDons);
		assert (viewName.equals("statistical-water"));
	}

	@Test
	public void testGetListPriceWithNoDates() {
		// Setup
		List<HoaDon> expectedHoaDons = new ArrayList<>();
		when(hoaDonService.getListHoaDon(null, "")).thenReturn(expectedHoaDons);

		// Execution
		String viewName = statisticalController.getListPrice(null, null, model);

		// Verification
		verify(hoaDonService).getListHoaDon(null, "");
		verify(model).addAttribute("listHoaDon", expectedHoaDons);
		assert (viewName.equals("statistical-price"));
	}

	@Test
	public void testGetListPriceWithDates() {
		// Setup
		LocalDate from = LocalDate.of(2022, 1, 1);
		LocalDate to = LocalDate.of(2022, 12, 31);
		String fromDate = from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String toDate = to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		List<HoaDon> expectedHoaDons = new ArrayList<>();
		when(hoaDonRepository.findDonsOrderByTien(from, to)).thenReturn(expectedHoaDons);

		// Execution
		String viewName = statisticalController.getListPrice(fromDate, toDate, model);

		// Verification
		verify(hoaDonRepository).findDonsOrderByTien(from, to);
		verify(model).addAttribute("listHoaDon", expectedHoaDons);
		assertEquals("statistical-price", viewName);
	}

}