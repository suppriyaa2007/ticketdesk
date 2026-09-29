package com.example.ticketdesk.controller;

import com.example.ticketdesk.entity.Booking;
import com.example.ticketdesk.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking addBooking(@RequestParam("studentId") Long studentId,
                              @RequestParam("showId") Long showId,
                              @RequestParam("seatsBooked") Integer seatsBooked) {

        return bookingService.saveBooking(studentId, showId, seatsBooked);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}