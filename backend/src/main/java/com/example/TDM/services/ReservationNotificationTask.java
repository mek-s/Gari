package com.example.TDM.services;

import com.example.TDM.models.Reservation;
import com.example.TDM.models.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ReservationNotificationTask {

    private final ReservationService reservationService;
    private final UserService userService;

    @Autowired
    public ReservationNotificationTask(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 * * * *") // Execute every hour
    public void sendNotificationForUpcomingReservations() {
        // Get current time plus one hour
        LocalDateTime oneHourFromNow = LocalDateTime.now().plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String targetTime = oneHourFromNow.format(formatter);

        // Retrieve reservations that are 1 hour away from heureEntree
        List<Reservation> upcomingReservations = reservationService.getReservationsByHeureEntree(targetTime);

        // Send push notifications for each upcoming reservation
        for (Reservation reservation : upcomingReservations) {
            sendPushNotification(reservation);
        }
    }

    private void sendPushNotification(Reservation reservation) {
        try {
            User user = userService.getUserByUsername(reservation.getUsername());
            if (user != null && user.getToken() != null) {
                // Construct and send push notification using Firebase SDK
                String token = user.getToken();
                // Construct the message
                Message message = Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder()
                                .setTitle("Upcoming Reservation")
                                .setBody("Your reservation is in 1 hour!")
                                .build())
                        .build();
                // Send the push notification
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message: " + response);
            }
        } catch (Exception e) {
            System.out.println("Error sending push notification for reservation: " + reservation.getId_reservation() + ", error: " + e.getMessage());
        }
    }
}