package com.kal.performancereviewservice.controller;

import com.kal.performancereviewservice.model.PerformanceReview;
import com.kal.performancereviewservice.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance-reviews")
public class PerformanceReviewController {
    private final PerformanceReviewService reviewService;

    @Autowired
    public PerformanceReviewController(PerformanceReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PerformanceReview>> getReviewsByEmployeeId(@PathVariable Long employeeId) {
        List<PerformanceReview> reviews = reviewService.getReviewsByEmployeeId(employeeId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/add")
    public ResponseEntity<PerformanceReview> addPerformanceReview(@RequestBody PerformanceReview performanceReview) {
        PerformanceReview addedReview = reviewService.addPerformanceReview(performanceReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReview);
    }
}

