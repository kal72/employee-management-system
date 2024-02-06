package com.kal.performancereviewservice.service;

import com.kal.performancereviewservice.model.PerformanceReview;
import com.kal.performancereviewservice.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReviewService {
    private final PerformanceReviewRepository reviewRepository;

    @Autowired
    public PerformanceReviewService(PerformanceReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<PerformanceReview> getReviewsByEmployeeId(Long employeeId) {
        return reviewRepository.findByEmployeeId(employeeId);
    }

    public PerformanceReview addPerformanceReview(PerformanceReview performanceReview) {
        return reviewRepository.save(performanceReview);
    }
}

