package com.example.waterbilling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.repository.HoaDonRepository;
import com.example.waterbilling.repository.KhachHangRepository;

import jakarta.mail.MessagingException;

@ExtendWith(MockitoExtension.class)
public class HoaDonServiceTest {

	@InjectMocks
	private HoaDonService hoaDonService;

	@Mock
	private HoaDonRepository hoaDonRepository;

	@Mock
	private KhachHangRepository khachHangRepository;

	@Mock
	private EmailService emailService;

	@Test
	public void testSendReminderEmails() throws MessagingException {
		KhachHang khachHang = new KhachHang();
		khachHang.setId(1L);
		khachHang.setTen("Nguyen Van A");
		khachHang.setEmail("nguyenvana@gmail.com");

		HoaDon hoaDon = new HoaDon();
		hoaDon.setId(1L);
		hoaDon.setTongTien(500000);
		hoaDon.setSoNuocTieuThu(50);
		hoaDon.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon.setNgayTao(LocalDate.now().minusDays(10));
		hoaDon.setKhachHang(khachHang);

		List<HoaDon> unpaidBills = Arrays.asList(hoaDon);

		when(hoaDonRepository.findEmailsByTrangThaiThanhToan("Chưa thanh toán")).thenReturn(unpaidBills);

		hoaDonService.sendReminderEmails();

		verify(emailService).sendEmail(eq("nguyenvana@gmail.com"), eq("Thông báo thanh toán tiền nước"), eq(
				"Xin chào Nguyen Van A,<br>Bạn có một hóa đơn nước chưa thanh toán. Mã hóa đơn: 1<br>Xin vui lòng thanh toán trước ngày "
						+ hoaDon.getNgayTao().plusDays(7) + ".<br>Xin cảm ơn!"));
	}

	@Test
	public void testGetListHoaDon_WithStatusNull() {
		when(hoaDonRepository.findAll()).thenReturn(Arrays.asList(new HoaDon(), new HoaDon()));

		List<HoaDon> listHoaDon = hoaDonService.getListHoaDon(null, null);

		assertEquals(2, listHoaDon.size());
	}

	@Test
	public void testGetListHoaDon_WithStatusNotBlank() {
		when(hoaDonRepository.findByTrangThaiThanhToan("Chưa thanh toán"))
				.thenReturn(Arrays.asList(new HoaDon(), new HoaDon()));

		List<HoaDon> listHoaDon = hoaDonService.getListHoaDon("Chưa thanh toán", null);

		assertEquals(2, listHoaDon.size());
	}

	@Test
	public void testGetListHoaDon_WithStatusBlank() {
		when(hoaDonRepository.findAll()).thenReturn(Arrays.asList(new HoaDon(), new HoaDon()));

		List<HoaDon> listHoaDon = hoaDonService.getListHoaDon("", null);

		assertEquals(2, listHoaDon.size());
	}
}
