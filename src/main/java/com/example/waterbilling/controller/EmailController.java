package com.example.waterbilling.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.repository.HoaDonRepository;
import com.example.waterbilling.repository.KhachHangRepository;
import com.example.waterbilling.service.EmailService;
import com.example.waterbilling.service.HoaDonService;
import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	private HoaDonService donService;

	@Autowired
	private HoaDonRepository donRepository;

	@Autowired
	private KhachHangRepository hangRepository;

	@Autowired
	private EmailService emailService;

	@GetMapping("")
	public String mail(Model model) {
		return "mail";
	}

	@GetMapping("/send-reminder-emails")
	public String sendRemiderEmail(Model model) {
		List<HoaDon> list = donRepository.findEmailsByTrangThaiThanhToan("Chưa thanh toán");
		List<KhachHang> hangs = new ArrayList<>();
		for (HoaDon i : list) {
			hangs.add(i.getKhachHang());
		}
		model.addAttribute("khachHangs", hangs);
		return "send-reminder-email";
	}

	@GetMapping("/send-reminder")
	public String sendReminderEmails(Model model) {
		try {
			donService.sendReminderEmails();
			return "success-page";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "redirect:/error-page";
		}

	}

	@GetMapping("/send-support-email")
	public String getSendSupportEmail(ModelMap model) {
		List<KhachHang> customers = hangRepository.findAll();
		List<String> customerEmails = customers.stream().map(KhachHang::getEmail).collect(Collectors.toList());
		model.addAttribute("customerEmails", customerEmails); // Đưa danh sách vào ModelMap để sử dụng ở view
		return "send-support-email";
	}

	@PostMapping("/send-support")
	public String sendSupportEmail(@RequestParam String email, @RequestParam String subject,
			@RequestParam String content, Model model) {
		try {
			emailService.sendEmail(email, subject, content);
			return "success-page";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/error-page";
		}

	}

}
