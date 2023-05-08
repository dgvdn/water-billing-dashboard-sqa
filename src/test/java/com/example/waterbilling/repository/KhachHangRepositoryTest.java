package com.example.waterbilling.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.waterbilling.model.KhachHang;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
class KhachHangRepositoryTest {
	@Autowired
	private KhachHangRepository hangRepository;
	private KhachHang khachHang1;
	private KhachHang khachHang2;

	@Test
	void testFindEmail() {
		khachHang1 = new KhachHang();
		khachHang1.setTen("Nguyen Van A");
		khachHang1.setEmail("nguyenvana@example.com");
		khachHang1.setSdt("0123456789");
		khachHang1.setDiaChi("Quan 1, TP HCM");
		khachHang1.setSoHopDong("HD0123456789");
		hangRepository.save(khachHang1);

		khachHang2 = new KhachHang();
		khachHang2.setTen("Nguyen Van B");
		khachHang2.setEmail("nguyenvanb@example.com");
		khachHang2.setSdt("0234567890");
		khachHang2.setDiaChi("Quan 2, TP HCM");
		khachHang2.setSoHopDong("HD1234567890");
		hangRepository.save(khachHang2);

		List<String> foundEmails = hangRepository.findEmail("nguyenvan");
		assertNotNull(foundEmails);
		assertEquals(2, foundEmails.size());
		assertTrue(foundEmails.contains("nguyenvana@example.com"));
		assertTrue(foundEmails.contains("nguyenvanb@example.com"));

		List<String> foundEmails2 = hangRepository.findEmail("nguyenvana");
		assertNotNull(foundEmails2);
		assertEquals(1, foundEmails2.size());
		assertTrue(foundEmails2.contains("nguyenvana@example.com"));

		List<String> foundEmails3 = hangRepository.findEmail("d");
		assertNotNull(foundEmails3);
		assertEquals(0, foundEmails3.size());

		List<String> foundEmails4 = hangRepository.findEmail("");
		assertNotNull(foundEmails4);
		assertEquals(2, foundEmails4.size());

	}

	@Test
	public void testFindByEmail() {
		KhachHang khachHang = new KhachHang();
		khachHang.setEmail("test@example.com");
		hangRepository.save(khachHang);

		KhachHang foundKhachHang = hangRepository.findByEmail("test@example.com");
		assertNotNull(foundKhachHang);
		assertEquals("test@example.com", foundKhachHang.getEmail());
	}

	@Test
	public void testFindByEmail_NotFound() {
		KhachHang khachHang = new KhachHang();
		khachHang.setEmail("test@example.com");
		hangRepository.save(khachHang);

		KhachHang foundKhachHang = hangRepository.findByEmail("notfound@example.com");
		assertNull(foundKhachHang);
	}

	@Test
	void testFindByTenContainingOrSoHopDongContaining() {
		KhachHang khachHang1 = new KhachHang();
		khachHang1.setTen("John Doe");
		khachHang1.setSoHopDong("HD-123");
		hangRepository.save(khachHang1);

		KhachHang khachHang2 = new KhachHang();
		khachHang2.setTen("Jane Doe");
		khachHang2.setSoHopDong("HD-456");
		hangRepository.save(khachHang2);

		List<KhachHang> khachHangList1 = hangRepository.findByTenContainingOrSoHopDongContaining("John", null);
		assertEquals(1, khachHangList1.size());
		assertEquals(khachHang1.getId(), khachHangList1.get(0).getId());

		List<KhachHang> khachHangList2 = hangRepository.findByTenContainingOrSoHopDongContaining(null, "HD-456");
		assertEquals(1, khachHangList2.size());
		assertEquals(khachHang2.getId(), khachHangList2.get(0).getId());

		List<KhachHang> khachHangList3 = hangRepository.findByTenContainingOrSoHopDongContaining("Doe", "");
		assertEquals(2, khachHangList3.size());

		List<KhachHang> khachHangList4 = hangRepository.findByTenContainingOrSoHopDongContaining("Not Found", null);
		assertEquals(0, khachHangList4.size());

		List<KhachHang> khachHangList5 = hangRepository.findByTenContainingOrSoHopDongContaining("john", null);
		assertEquals(0, khachHangList5.size());

		List<KhachHang> khachHangList6 = hangRepository.findByTenContainingOrSoHopDongContaining("", null);
		assertEquals(2, khachHangList6.size());

	}

}
