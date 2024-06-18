package com.example.demo.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
		@Autowired
		private RewardService rewardservice;
		@GetMapping("/{customerId}/points")
		public Map<String,Integer> getRewardPoints(@PathVariable String customerId,@RequestParam String startdate, String enddate){
			LocalDate start = LocalDate.parse(startdate);
			LocalDate end = LocalDate.parse(enddate);
			return rewardservice.calculateRewardPoints(customerId, start, end);
				
		}
		@GetMapping("/{customerId}/monthly-points")
		public Map<String, Map<String,Integer>> getMonthluRewardPoints(@PathVariable String customerId, @RequestParam int year){
			return rewardservice.calculateMonthlyRewardPoints(customerId, year);
		}
} 
