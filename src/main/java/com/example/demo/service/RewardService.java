package com.example.demo.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
@Service
public class RewardService {
		@Autowired
		private TransactionRepository transactionrepository;
		
		public Map<String, Integer> calculateRewardPoints(String customerId,LocalDate startdate,LocalDate enddate){
			
			List<Transaction> transactions = transactionrepository.findByCustomerIdAndDateBetween(customerId, startdate, enddate);
			int totalPoints = 0;
			
			for(Transaction transaction: transactions) {
				double amount = transaction.getAmount();
				int points=0;
			
			if(amount>100) {
				points +=(amount-100)*2;
				amount = 100;
			}
			if(amount>50) {
				points +=(amount-50)*1;
			}
			totalPoints += points;
			}
			Map<String,Integer> rewardPoints = new HashMap<>();
			rewardPoints.put(customerId, totalPoints);
			return rewardPoints;
		}
		public Map<String,Map<String,Integer>> calculateMonthlyRewardPoints(String customerId, int year){
			Map<String,Map<String,Integer>> monthlyPoints = new HashMap<>();
			for(int month = 1; month <= 12; month++) {
				LocalDate startdate = LocalDate.of(year, month,1);
				LocalDate enddate = startdate.withDayOfMonth(startdate.lengthOfMonth());
				Map<String,Integer> points = calculateRewardPoints(customerId, startdate, enddate);
				monthlyPoints.put(startdate.getMonth().toString(), points);	
			}
			return monthlyPoints;
		}
	

}
