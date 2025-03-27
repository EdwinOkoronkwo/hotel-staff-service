package com.edwinokoronkwo.hotelstaffservice;

import com.edwinokoronkwo.hotelstaffservice.model.Staff;
import com.edwinokoronkwo.hotelstaffservice.model.Hotel;
import com.edwinokoronkwo.hotelstaffservice.repository.StaffRepository;
import com.edwinokoronkwo.hotelstaffservice.service.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StaffServiceImplTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffServiceImpl staffService;

    private Staff staff;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        staff = new Staff();
        staff.setStaffId(1L);
        staff.setStaffName("Test Staff");
        staff.setPerformanceRating(4);

        hotel = new Hotel();
        hotel.setHotelId("Test1234");
        hotel.setHotelName("Test Hotel");
        hotel.setStarRating(4);
    }

    @Test
    void createStaff_valid_shouldSaveStaff() {
        when(staffRepository.save(staff)).thenReturn(staff);

        Staff createdStaff = staffService.createStaff(staff, hotel);

        assertEquals(staff, createdStaff);
        verify(staffRepository, times(1)).save(staff);
    }

    @Test
    void createStaff_invalidRating_shouldThrowException() {
        staff.setPerformanceRating(5);
        hotel.setStarRating(3);

        assertThrows(IllegalArgumentException.class, () -> staffService.createStaff(staff, hotel));
        verify(staffRepository, never()).save(any(Staff.class));
    }

    @Test
    void updateStaff_valid_shouldSaveStaff() {
        when(staffRepository.save(staff)).thenReturn(staff);
        when(staffRepository.existsById(1L)).thenReturn(true);

        Staff updatedStaff = staffService.updateStaff(1L, staff, hotel);

        assertEquals(staff, updatedStaff);
        verify(staffRepository, times(1)).save(staff);
    }

    @Test
    void updateStaff_invalidRating_shouldThrowException() {
        staff.setPerformanceRating(5);
        hotel.setStarRating(3);

        assertThrows(IllegalArgumentException.class, () -> staffService.updateStaff(1L, staff, hotel));
        verify(staffRepository, never()).save(any(Staff.class));
    }

    @Test
    void getStaffById_validId_shouldReturnStaff() {
        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));

        Staff foundStaff = staffService.getStaffById(1L);

        assertEquals(staff, foundStaff);
        verify(staffRepository, times(1)).findById(1L);
    }

    @Test
    void getStaffById_invalidId_shouldReturnNull() {
        when(staffRepository.findById(1L)).thenReturn(Optional.empty());

        Staff foundStaff = staffService.getStaffById(1L);

        assertNull(foundStaff);
        verify(staffRepository, times(1)).findById(1L);
    }

    @Test
    void deleteStaff_shouldCallDeleteById() {
        staffService.deleteStaff(1L);
        verify(staffRepository, times(1)).deleteById(1L);
    }
}