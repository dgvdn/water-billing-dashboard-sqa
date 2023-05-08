package com.example.waterbilling.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.waterbilling.model.Setting;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
class SettingRepositoryTest {
	@Autowired
	private SettingRepository settingRepository;

	@Test
	public void testFindAllWithEmptyDatabase() {
		List<Setting> settings = settingRepository.findAll();
		assertThat(settings).hasSize(0);
	}

	@Test
	public void testFindAllWithData() {
		Setting setting1 = new Setting(null, 1.0, 10.0, 1000.0, LocalDate.now());
		Setting setting2 = new Setting(null, 11.0, 20.0, 2000.0, LocalDate.now());
		settingRepository.saveAll(List.of(setting1, setting2));

		// Call the repository method to retrieve all data
		List<Setting> settings = settingRepository.findAll();

		// Verify that the correct number of records are returned
		assertEquals(2, settings.size());

		// Verify that each record has the correct values
		assertTrue(settings.contains(setting1));
		assertTrue(settings.contains(setting2));
	}

}
