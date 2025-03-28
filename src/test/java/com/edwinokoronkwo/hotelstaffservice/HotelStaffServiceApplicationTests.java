package com.edwinokoronkwo.hotelstaffservice;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@SpringBootTest
@EnabledIf(expression = "#{environment.acceptsProfiles('mysql')}", reason = "Disabled in GitHub Actions")
class HotelStaffServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
