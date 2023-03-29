package com.example.waterbilling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.repository.HoaDonRepository;

import jakarta.mail.MessagingException;

@Service
public class HoaDonService {
	@Autowired
	private EmailService emailService;

	@Autowired
	private HoaDonRepository hoaDonRepository;

	public void sendReminderEmails() throws MessagingException {
		List<HoaDon> unpaidBills = hoaDonRepository.findEmailsByTrangThaiThanhToan("Chưa thanh toán");
		for (HoaDon hoaDon : unpaidBills) {
			String to = hoaDon.getKhachHang().getEmail();
			String subject = "Thông báo thanh toán tiền nước";
			String body = "Xin chào " + hoaDon.getKhachHang().getTen() + ",<br>"
					+ "Bạn có một hóa đơn nước chưa thanh toán. Mã hóa đơn: " + hoaDon.getId()
					+ "<br>Xin vui lòng thanh toán trước ngày " + hoaDon.getNgayTao().plusDays(7) + ".<br>Xin cảm ơn!";
			emailService.sendEmail(to, subject, body);
		}
	}

	public List<HoaDon> getListHoaDon(String status, String search) {
		if (status == null || status.isBlank()) {
			return hoaDonRepository.findAll();
		} else {
			return hoaDonRepository.findByTrangThaiThanhToan(status);
		}
	}
}