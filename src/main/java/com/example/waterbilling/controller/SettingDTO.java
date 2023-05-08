package com.example.waterbilling.controller;

import java.util.List;

import com.example.waterbilling.model.Setting;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SettingDTO {
	List<Setting> settings;

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

}
