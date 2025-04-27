
package virtualMarket.payment;

import java.time.LocalDateTime;

import virtualMarket.order.*;

public class Payment {
	private int id;
	private Order order;
	private double amount;
	private LocalDateTime paymentDate;
	private String status;

	public Payment(int id, Order order, double amount, LocalDateTime paymentDate, String status) {
		this.id = id;
		this.order = order;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.status = status;
	}

	public boolean processPayment() {
		
		return true;
	};

	public String getPaymentStatus() {
		return null;
	};

}
