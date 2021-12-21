package com.mycompany.market.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {

	 private int purchaseId;
	 private String clientId;
	 private String userId;
	 private LocalDateTime date;
	 private String paymentMethod;
	 private String coments;
	 private String state;
	 private List<PurchaseItem> items;
	 
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getComents() {
		return coments;
	}
	public void setComents(String coments) {
		this.coments = coments;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<PurchaseItem> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItem> items) {
		this.items = items;
	}
	 
	 
	 
}
