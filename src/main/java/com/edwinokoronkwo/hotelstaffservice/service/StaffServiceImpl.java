package com.edwinokoronkwo.hotelstaffservice.service;

import com.edwinokoronkwo.hotelstaffservice.model.Staff;
import com.edwinokoronkwo.hotelstaffservice.model.Hotel;
import com.edwinokoronkwo.hotelstaffservice.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(Long staffId) {
        return staffRepository.findById(staffId).orElse(null);
    }

    @Override
    public Staff createStaff(Staff staff, Hotel hotel) {
        validateStaffHotelRating(staff, hotel);
        staff.setHotel(hotel);
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long staffId, Staff staff, Hotel hotel) {
        validateStaffHotelRating(staff, hotel);
        staff.setHotel(hotel);
        staff.setStaffId(staffId);
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }

    private void validateStaffHotelRating(Staff staff, Hotel hotel) {
        if (staff.getPerformanceRating() >= 4 && hotel.getStarRating() < 4) {
            throw new IllegalArgumentException("Staff with rating 4 or more can only be assigned to hotels with rating 4 or more.");
        }
        if (staff.getPerformanceRating() <= 3 && hotel.getStarRating() > 3) {
            throw new IllegalArgumentException("Staff with rating 3 or less can only be assigned to hotels with rating 3 or less.");
        }
    }

    @Override
    public List<Staff> getAllStaffSortedByDepartment() { // Renamed method
        return staffRepository.findAllSortedByDepartment(); // Updated call
    }
}