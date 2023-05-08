package com.example.waterbilling.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.waterbilling.model.HoaDon;
import com.example.waterbilling.model.KhachHang;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
class HoaDonRepositoryTest {
	@Autowired
	private HoaDonRepository hoaDonRepository;
	@Autowired
	private KhachHangRepository hangRepository;

	private HoaDon hoaDon1;
	private HoaDon hoaDon2;
	private HoaDon hoaDon3;

	private KhachHang khachHang1;
	private KhachHang khachHang2;
	private KhachHang khachHang3;

	@Test
	void testSave() {
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

		khachHang3 = new KhachHang();
		khachHang3.setTen("Nguyen Van C");
		khachHang3.setEmail("nguyenvanc@example.com");
		khachHang3.setSdt("0987654321");
		khachHang3.setDiaChi("Quan 3, TP HCM");
		khachHang3.setSoHopDong("HD2345678901");
		hangRepository.save(khachHang3);

		hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(LocalDate.now().minusDays(2));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);

		hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(LocalDate.now().minusDays(1));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang2);

		hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(LocalDate.now());
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang3);

		hoaDonRepository.save(hoaDon1);
		hoaDonRepository.save(hoaDon2);
		hoaDonRepository.save(hoaDon3);

		Optional<HoaDon> optionalHoaDon1 = hoaDonRepository.findById(hoaDon1.getId());
		assertTrue(optionalHoaDon1.isPresent());
		HoaDon savedHoaDon1 = optionalHoaDon1.get();
		assertNotNull(savedHoaDon1.getKhachHang());
		assertEquals(khachHang1.getId(), savedHoaDon1.getKhachHang().getId());

		Optional<HoaDon> optionalHoaDon2 = hoaDonRepository.findById(hoaDon2.getId());
		assertTrue(optionalHoaDon2.isPresent());
		HoaDon savedHoaDon2 = optionalHoaDon2.get();
		assertNotNull(savedHoaDon2.getKhachHang());
		assertEquals(khachHang2.getId(), savedHoaDon2.getKhachHang().getId());

		Optional<HoaDon> optionalHoaDon3 = hoaDonRepository.findById(hoaDon3.getId());
		assertTrue(optionalHoaDon3.isPresent());
		HoaDon savedHoaDon3 = optionalHoaDon3.get();
		assertNotNull(savedHoaDon3.getKhachHang());
		assertEquals(khachHang3.getId(), savedHoaDon3.getKhachHang().getId());
	}

	@Test
	void testFindEmailsByTrangThaiThanhToan() {
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

		khachHang3 = new KhachHang();
		khachHang3.setTen("Nguyen Van C");
		khachHang3.setEmail("nguyenvanc@example.com");
		khachHang3.setSdt("0987654321");
		khachHang3.setDiaChi("Quan 3, TP HCM");
		khachHang3.setSoHopDong("HD2345678901");
		hangRepository.save(khachHang3);

		hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(LocalDate.now().minusDays(2));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);

		hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(LocalDate.now().minusDays(1));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang2);

		hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(LocalDate.now());
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang3);

		hoaDonRepository.save(hoaDon1);
		hoaDonRepository.save(hoaDon2);
		hoaDonRepository.save(hoaDon3);

		List<HoaDon> hoaDonList = hoaDonRepository.findEmailsByTrangThaiThanhToan("Đã thanh toán");

		// Assert
		assertEquals(2, hoaDonList.size());
		assertEquals("nguyenvana@example.com", hoaDonList.get(0).getKhachHang().getEmail());
		assertEquals("nguyenvanc@example.com", hoaDonList.get(1).getKhachHang().getEmail());

		List<HoaDon> hoaDonList2 = hoaDonRepository.findEmailsByTrangThaiThanhToan("Chưa thanh toán");

		// Assert
		assertEquals(1, hoaDonList2.size());
		assertEquals("nguyenvanb@example.com", hoaDonList2.get(0).getKhachHang().getEmail());

		List<HoaDon> hoaDonList3 = hoaDonRepository.findEmailsByTrangThaiThanhToan("");

		// Assert
		assertEquals(0, hoaDonList3.size());
	}

	@Test
	void testFindByTrangThaiThanhToan() {
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

		khachHang3 = new KhachHang();
		khachHang3.setTen("Nguyen Van C");
		khachHang3.setEmail("nguyenvanc@example.com");
		khachHang3.setSdt("0987654321");
		khachHang3.setDiaChi("Quan 3, TP HCM");
		khachHang3.setSoHopDong("HD2345678901");
		hangRepository.save(khachHang3);

		hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(LocalDate.now().minusDays(2));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);

		hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(LocalDate.now().minusDays(1));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang2);

		hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(LocalDate.now());
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang3);

		hoaDonRepository.save(hoaDon1);
		hoaDonRepository.save(hoaDon2);
		hoaDonRepository.save(hoaDon3);

		List<HoaDon> hoaDons = hoaDonRepository.findByTrangThaiThanhToan("Đã thanh toán");
		assertEquals(2, hoaDons.size());

		List<HoaDon> hoaDons2 = hoaDonRepository.findByTrangThaiThanhToan("Chưa thanh toán");
		assertEquals(1, hoaDons2.size());

		List<HoaDon> hoaDons3 = hoaDonRepository.findByTrangThaiThanhToan("");
		assertEquals(0, hoaDons3.size());
	}

	@Test
	void testFindByKhachHangId() {
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

		khachHang3 = new KhachHang();
		khachHang3.setTen("Nguyen Van C");
		khachHang3.setEmail("nguyenvanc@example.com");
		khachHang3.setSdt("0987654321");
		khachHang3.setDiaChi("Quan 3, TP HCM");
		khachHang3.setSoHopDong("HD2345678901");
		hangRepository.save(khachHang3);

		hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(LocalDate.now().minusDays(2));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);

		hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(LocalDate.now().minusDays(1));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang1);

		hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(LocalDate.now());
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang3);

		hoaDonRepository.save(hoaDon1);
		hoaDonRepository.save(hoaDon2);
		hoaDonRepository.save(hoaDon3);

		List<HoaDon> hoaDonList = hoaDonRepository.findByKhachHangId(khachHang1.getId());

		assertEquals(2, hoaDonList.size());

		for (HoaDon hoaDon : hoaDonList) {
			assertEquals(khachHang1.getId(), hoaDon.getKhachHang().getId());
		}

		List<HoaDon> hoaDons = hoaDonRepository.findByKhachHangId(3L);

		assertEquals(0, hoaDons.size());

		List<HoaDon> dons = hoaDonRepository.findByKhachHangId(4L);

		assertEquals(0, dons.size());

	}

	@Test
	void testFindDonsOrderByNuoc() {
		// Create some test data
		KhachHang khachHang1 = new KhachHang();
		khachHang1.setTen("Nguyen Van A");
		khachHang1.setEmail("nguyenvana@example.com");
		khachHang1.setSdt("0123456789");
		khachHang1.setDiaChi("Quan 1, TP HCM");
		khachHang1.setSoHopDong("HD0123456789");
		hangRepository.save(khachHang1);

		HoaDon hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(LocalDate.of(2022, 5, 1));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);

		HoaDon hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(LocalDate.of(2022, 5, 2));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang1);

		HoaDon hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(LocalDate.of(2022, 5, 3));
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang1);

		hoaDonRepository.save(hoaDon1);
		hoaDonRepository.save(hoaDon2);
		hoaDonRepository.save(hoaDon3);

		// Call the method being tested
		LocalDate dateFrom = LocalDate.of(2022, 5, 1);
		LocalDate dateTo = LocalDate.of(2022, 5, 3);
		List<HoaDon> hoaDons = hoaDonRepository.findDonsOrderByNuoc(dateFrom, dateTo);

		// Assert the result is correct
		assertEquals(3, hoaDons.size());
		assertEquals(hoaDon1, hoaDons.get(0));
		assertEquals(hoaDon2, hoaDons.get(1));
		assertEquals(hoaDon3, hoaDons.get(2));

		// Call the method being tested
		LocalDate dateFrom1 = LocalDate.of(2022, 4, 1);
		LocalDate dateTo1 = LocalDate.of(2022, 4, 30);
		List<HoaDon> hoaDons1 = hoaDonRepository.findDonsOrderByNuoc(dateFrom1, dateTo1);

		// Assert the result is correct
		assertEquals(0, hoaDons1.size());

		// Call the method being tested
		LocalDate dateFrom2 = LocalDate.of(2022, 5, 4);
		LocalDate dateTo2 = LocalDate.of(2022, 5, 5);
		List<HoaDon> hoaDons2 = hoaDonRepository.findDonsOrderByNuoc(dateFrom2, dateTo2);

		// Assert the result is correct
		assertEquals(0, hoaDons2.size());

		// Call the method being tested
		LocalDate dateFrom3 = LocalDate.of(2022, 5, 4);
		LocalDate dateTo3 = LocalDate.of(2022, 5, 1);
		List<HoaDon> hoaDons3 = hoaDonRepository.findDonsOrderByNuoc(dateFrom3, dateTo3);

		// Assert the result is correct
		assertEquals(0, hoaDons3.size());

	}

	@Test
	void testFindDonsOrderByTien() {
		KhachHang khachHang1 = new KhachHang();
		khachHang1.setTen("Nguyen Van A");
		khachHang1.setEmail("nguyenvana@example.com");
		khachHang1.setSdt("0123456789");
		khachHang1.setDiaChi("Quan 1, TP HCM");
		khachHang1.setSoHopDong("HD0123456789");
		hangRepository.save(khachHang1);

		LocalDate dateFrom = LocalDate.of(2022, 1, 1);
		LocalDate dateTo = LocalDate.of(2022, 12, 31);

		HoaDon hoaDon1 = new HoaDon();
		hoaDon1.setNgayTao(dateFrom.plusDays(1));
		hoaDon1.setSoNuocTieuThu(10);
		hoaDon1.setTongTien(200000);
		hoaDon1.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon1.setKhachHang(khachHang1);
		hoaDonRepository.save(hoaDon1);

		HoaDon hoaDon2 = new HoaDon();
		hoaDon2.setNgayTao(dateFrom.plusDays(2));
		hoaDon2.setSoNuocTieuThu(20);
		hoaDon2.setTongTien(400000);
		hoaDon2.setTrangThaiThanhToan("Chưa thanh toán");
		hoaDon2.setKhachHang(khachHang1);
		hoaDonRepository.save(hoaDon2);

		HoaDon hoaDon3 = new HoaDon();
		hoaDon3.setNgayTao(dateFrom.plusDays(3));
		hoaDon3.setSoNuocTieuThu(30);
		hoaDon3.setTongTien(600000);
		hoaDon3.setTrangThaiThanhToan("Đã thanh toán");
		hoaDon3.setKhachHang(khachHang1);
		hoaDonRepository.save(hoaDon3);

		List<HoaDon> hoaDons = hoaDonRepository.findDonsOrderByTien(dateFrom, dateTo);

		// Check if the result is not null and has the correct size
		assertNotNull(hoaDons);
		assertEquals(3, hoaDons.size());

		// Check if the result is ordered by the "tongTien" property
		for (int i = 0; i < hoaDons.size() - 1; i++) {
			assertTrue(hoaDons.get(i).getTongTien() <= hoaDons.get(i + 1).getTongTien());
		}

		List<HoaDon> hoaDons2 = hoaDonRepository.findDonsOrderByTien(dateFrom.plusDays(3), dateTo);

		// Check if the result is not null and has the correct size
		assertNotNull(hoaDons2);
		assertEquals(1, hoaDons2.size());

		List<HoaDon> hoaDons3 = hoaDonRepository.findDonsOrderByTien(dateFrom.plusDays(3), LocalDate.of(2021, 12, 31));

		// Check if the result is not null and has the correct size
		assertNotNull(hoaDons3);
		assertEquals(0, hoaDons3.size());

	}

}
