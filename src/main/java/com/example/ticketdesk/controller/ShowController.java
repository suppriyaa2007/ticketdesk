package com.example.ticketdesk.controller;

import com.example.ticketdesk.entity.Show;
import com.example.ticketdesk.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public Show addShow(@RequestBody Show show) {
        return showService.saveShow(show);
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }
}