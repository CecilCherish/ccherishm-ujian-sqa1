package com.juaracoding;

public class OrderTestRunner {
    public static void main(String[] args) {
        
        OrderTestCase[] orderSuite = {
            new OrderTestCase("ORD-2026-001", "Budi Santoso", 500000.0, 2, 0.0),   // Skenario 1: pas
            new OrderTestCase("ORD-2026-002", "Siti Rahma", 1200000.0, 1, 0.0),    // Skenario 2: kurang
            new OrderTestCase("ORD-2026-003", "Dewi Lestari", 2500000.0, 1, 20.0), // Skenario 3: voucher 20%
            new OrderTestCase("ORD-2026-004", "Andi Wijaya", 750000.0, 2, 10.0)    // Skenario 4: lebih
        };

        double[] actualPayments = {1000000.0, 1000000.0, 2000000.0, 1500000.0};

        for (int i = 0; i < orderSuite.length; i++) {
            orderSuite[i].evaluatePayment(actualPayments[i]);
        }

        System.out.println("=========================================================================================================");
        System.out.println("                                JUARACODING AUTOMATED ORDER TRACKING REPORT                               ");
        System.out.println("=========================================================================================================");
        System.out.printf("%-14s | %-15s | %-15s | %-3s | %-6s | %-15s | %-6s | %s%n",
                "Order ID", "Customer", "Item Price", "Qty", "Diskon", "Grand Total", "Status", "Failure Reason");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        int totalPass = 0;
        int totalFail = 0;
        double omzetOrderPass = 0.0; 
        double omzetSemuaOrder = 0.0;

        for (OrderTestCase tc : orderSuite) {
            tc.displayRow();
            omzetSemuaOrder += tc.calculateGrandTotal();
            if (tc.getTestStatus().equals("PASS")) {
                totalPass++;
                omzetOrderPass += tc.calculateGrandTotal();
            } else {
                totalFail++;
            }
        }

        int totalCases = orderSuite.length;
        double passRate = ((double) totalPass / totalCases) * 100.0;

        System.out.println("=========================================================================================================");
        System.out.println("METRIK KUALITAS & RINGKASAN TRANSAKSI:");
        System.out.println("Total Test Executed : " + totalCases);
        System.out.printf("Total Test PASS     : %d (%.1f%%)%n", totalPass, passRate);
        System.out.println("Total Test FAIL     : " + totalFail);
        System.out.printf("Omzet Bersih (order PASS saja)    : Rp %,.2f%n", omzetOrderPass);
        System.out.printf("Omzet Bersih (semua order)        : Rp %,.2f%n", omzetSemuaOrder);
        System.out.println("=========================================================================================================");
    }
}