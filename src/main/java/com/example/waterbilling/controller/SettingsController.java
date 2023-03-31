package com.example.waterbilling.controller;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;
import com.example.waterbilling.model.Setting;
import com.example.waterbilling.repository.HoaDonRepository;
import com.example.waterbilling.repository.KhachHangRepository;
import com.example.waterbilling.repository.SettingRepository;
import com.example.waterbilling.service.HoaDonService;

@Controller
@RequestMapping("settings")
public class SettingsController {

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private HoaDonRepository donRepository;

	@GetMapping("")
	public String settings(Model model) {
		List<Setting> settings = settingRepository.findAll();
		model.addAttribute("settings", settings);
		return "settings";
	}
	
	@GetMapping("/update")
	public String settingsUpdate(Model model) {
		List<Setting> settings = settingRepository.findAll();
		model.addAttribute("settings", settings);
		return "settings-update";
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseDTO putUpdate(@RequestBody(required = false) SettingDTO settings) {
		settingRepository.deleteAll();
		settingRepository.saveAll(settings.getSettings());
		ResponseDTO res = new ResponseDTO(200);
		return res;
	}
}
