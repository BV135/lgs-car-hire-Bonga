package za.co.carhire.controller.reservation;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.carhire.domain.authentication.User;
import za.co.carhire.domain.reservation.Booking;
import za.co.carhire.domain.reservation.Location;
import za.co.carhire.domain.reservation.Payment;
import za.co.carhire.domain.vehicle.Car;
import za.co.carhire.factory.reservation.BookingFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private String baseUrl = "http://localhost:3045/booking";

    private static Booking booking;

    @BeforeEach
    void setUp() {
        int bookingID = 1;
        User user = new User.Builder()
                .setUserId(4)
                .build();
        List<Car> cars = new ArrayList<>();
        LocalDateTime bookingDateAndTime = LocalDateTime.of(2025, 5, 25, 10, 0);
        LocalDateTime startDate = LocalDateTime.of(2025, 5, 25, 10, 30);
        LocalDateTime endDate = LocalDateTime.of(2025, 5, 25, 11, 0);
        Payment payment = new Payment.Builder()
                .setPaymentID(3)
                .build();

        Location pickUpLocation = new Location.Builder()
                .setLocationName("Beaufort West")
                .setProvinceOrState("Western Cape")
                .build();

        Location dropOffLocation = new Location.Builder()
                .setLocationName("Laingsburg")
                .setProvinceOrState("Western Cape")
                .build();

        String bookingStatus = "Pending";

        booking = BookingFactory.createBooking(bookingID, user, cars, bookingDateAndTime, startDate, endDate, pickUpLocation, dropOffLocation, bookingStatus);
    }

    @Test
    @Order(1)
    void create() {
        String url = baseUrl + "/create";
        System.out.println("Post data: " + booking);
        ResponseEntity<Booking> response = restTemplate.postForEntity(url, booking, Booking.class);
        System.out.println("Response: " + response.getBody());
        assertEquals(booking, response.getBody());
    }

    @Test
    @Order(2)
    void read() {
    }

    @Test
    @Order(3)
    void update() {
    }

    @Test
    @Order(4)
    void delete() {
        String url = baseUrl +"delete/"+booking.getBookingID();
        System.out.println("URL: "+url);
        restTemplate.delete(url);
    }
}