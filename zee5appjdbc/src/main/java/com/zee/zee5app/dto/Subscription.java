package com.zee.zee5app.dto;


import lombok.Data;

@Data
public class Subscription {


		 
		private String subscriptionId;
		private String dop;
		private String expiry;
		private String amount;
		private String paymentmode;
		private String status;
		private String type;
		private String autorenewal;
		private String regId;
		
		
//		public Subscription()
//		{
//			System.out.println("hello ");
//		}

}
