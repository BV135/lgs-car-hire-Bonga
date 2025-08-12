package za.co.carhire.factory.reservation;
/* User.java

     User POJO class

     Author: Bonga Velem (220052379)

     Date: 18 May 2025 */

import za.co.carhire.domain.authentication.User;
import za.co.carhire.domain.reservation.BookingStatus;
import za.co.carhire.domain.reservation.Notification;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class NotificationFactoryTest {

    private Notification notification = NotificationFactory.createNotification(12121, new User(), "Welcome to Our App!", "2025-05-18", BookingStatus.CONFIRMED);



    @org.junit.jupiter.api.Test
    void createNotification() {
        assertNotNull(notification);

        System.out.println(notification.toString());
    }
}