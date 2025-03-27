package com.edwinokoronkwo.hotelstaffservice.controller;

import com.edwinokoronkwo.hotelstaffservice.model.Hotel;
import com.edwinokoronkwo.hotelstaffservice.model.Staff;
import com.edwinokoronkwo.hotelstaffservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long staffId) {
        Staff staff = staffService.getStaffById(staffId);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Staff> createStaff(@Valid @RequestBody Staff staff) {
        Hotel hotel = staff.getHotel();
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.createStaff(staff, hotel));
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long staffId, @Valid @RequestBody Staff staff) {
        Hotel hotel = staff.getHotel();
        Staff updatedStaff = staffService.updateStaff(staffId, staff, hotel);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long staffId) {
        staffService.deleteStaff(staffId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Staff>> getAllStaffSorted() {
        return ResponseEntity.ok(staffService.getAllStaffSortedByDepartment());
    }
}