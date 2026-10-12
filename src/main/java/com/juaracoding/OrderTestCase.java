package com.juaracoding;

public class OrderTestCase {
    private String orderId;
    private String customerName;
    private double itemPrice;
    private int quantity;
    private double voucherDiscountPercent;
    private String testStatus;
    private String failureReason;

    public OrderTestCase(String orderId, String customerName, double itemPrice, int quantity, double voucherDiscountPercent) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.voucherDiscountPercent = voucherDiscountPercent;
        this.testStatus = "BLOCKED";
        this.failureReason = "-";
    }

    public double calculateSubtotal() {
        return this.itemPrice * this.quantity;
    }

    public double calculateDiscountAmount() {
        return calculateSubtotal() * (this.voucherDiscountPercent / 100.0);
    }

    public double calculateGrandTotal() {
        return calculateSubtotal() - calculateDiscountAmount();
    }

    public void evaluatePayment(double actualAmountPaid) {
        double difference = actualAmountPaid - calculateGrandTotal();

        if (difference > -0.001 && difference < 0.001) {
            this.testStatus = "PASS";
            this.failureReason = "-";
        } else if (difference < 0) {
            this.testStatus = "FAIL";
            this.failureReason = String.format("Underpaid by Rp %,.2f", -difference);
        } else {
            this.testStatus = "FAIL";
            this.failureReason = String.format("Overpaid by Rp %,.2f", difference);
        }
    }

    public void displayRow() {
        System.out.printf("%-14s | %-15s | Rp %,12.2f | %-3d | %5.1f%% | Rp %,12.2f | %-6s | %s%n",
                this.orderId, this.customerName, this.itemPrice, this.quantity,
                this.voucherDiscountPercent, calculateGrandTotal(), this.testStatus, this.failureReason);
    }

    public String getTestStatus() {
        return this.testStatus;
    }
}