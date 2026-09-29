package com.example.ticketdesk.service;

import com.example.ticketdesk.entity.Booking;
import com.example.ticketdesk.entity.Show;
import com.example.ticketdesk.entity.Student;
import com.example.ticketdesk.repository.BookingRepository;
import com.example.ticketdesk.repository.ShowRepository;
import com.example.ticketdesk.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ShowRepository showRepository;

    // Book Seats
    public Booking saveBooking(Long studentId, Long showId, Integer seatsBooked) {

        Student student = studentRepository.findById(studentId).orElseThrow();
        Show show = showRepository.findById(showId).orElseThrow();

        // Prevent Overbooking
        if (show.getAvailableSeats() < seatsBooked) {
            throw new RuntimeException("Seats Not Available");
        }

        // Reduce Available Seats
        show.setAvailableSeats(show.getAvailableSeats() - seatsBooked);
        showRepository.save(show);

        Booking booking = new Booking();
        booking.setStudent(student);
        booking.setShow(show);
        booking.setSeatsBooked(seatsBooked);
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    // View All Bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Cancel Booking
    public String cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow();

        Show show = booking.getShow();

        // Release Seats
        show.setAvailableSeats(show.getAvailableSeats() + booking.getSeatsBooked());
        showRepository.save(show);

        bookingRepository.delete(booking);

        return "Booking Cancelled Successfully";
    }
}