package com.example.waterbilling.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.waterbilling.model.Setting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingDTO {
	List<Setting> settings;
}
