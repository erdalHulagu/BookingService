package com.erdal.model;

import lombok.Data;

@Data
public class SaloonReport {

	private Long saloonId;

	private String saloonName;

	private Double totalEarnings;

	private Integer totalBookings;

	private Double totalRefund;

	private Integer cancelledBookings;

}
