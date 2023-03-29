package com.example.waterbilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class ScheduledTasks {

	@Autowired
	private HoaDonService billingService;

	@Scheduled(cron = "0 0 15 * * ?")
	public void sendReminderEmails() throws MessagingException {
		billingService.sendReminderEmails();
	}

}
