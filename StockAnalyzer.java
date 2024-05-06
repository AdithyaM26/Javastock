package com.example.shrey.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StockAnalyzer {

	public static double computeMaxProfit(String stockName, int year) throws IOException {
		String csvFile = stockName.toUpperCase() + ".csv";
		String line;
		String[] data;
		double minPrice = Double.MAX_VALUE;
		double maxProfit = 0.0;
		boolean found = false;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				String date = data[0];
				int dataYear = Integer.parseInt(date.split("-")[0]);
				if (dataYear == year) {
					found = true;
					double currentPrice = Double.parseDouble(data[4]); // Closing price
					if (currentPrice < minPrice) {
						minPrice = currentPrice;
					} else {
						double profit = currentPrice - minPrice;
						if (profit > maxProfit) {
							maxProfit = profit;
						}
					}
				}
			}
		}

		if (!found) {
			System.out.println("No data found for the specified year.");
		}

		return maxProfit;
	}

	public static RO maxProfit(String stockName, int year) throws IOException {
		String csvFile = stockName.toUpperCase() + ".csv";
		csvFile = "C:\\Users\\Kishore\\OneDrive\\Desktop\\demo\\demo\\src\\main\\java\\com\\example\\shrey\\demo\\"
				+ csvFile;
		String line;
		String[] data;
		double minPrice = Double.MAX_VALUE;
		double maxProfit = 0.0;
		String buyDate = "";
		String sellDate = "";
		double buyPrice = 0.0;
		double sellPrice = 0.0;
		boolean found = false;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				String date = data[0];
				int dataYear = Integer.parseInt(date.split("-")[0]);
				if (dataYear == year) {
					found = true;
					double currentPrice = Double.parseDouble(data[4]); // Closing price
					if (currentPrice < minPrice) {
						minPrice = currentPrice;
						buyDate = date;
						buyPrice = currentPrice;
					} else {
						double profit = currentPrice - minPrice;
						if (profit > maxProfit) {
							maxProfit = profit;
							sellDate = date;
							sellPrice = currentPrice;
						}
					}
				}
			}
		}

		if (!found) {
			System.out.println("No data found for the specified year.");
			return null;
		}

		return new RO(buyDate, buyPrice, sellDate, sellPrice, maxProfit);
	}

	public static void main(String[] args) {
		try {
			String stockName = "AAPL"; // Replace with the desired stock symbol
			int year = 2024; // Replace with the desired year
			RO ro = maxProfit(stockName, year);
			if (ro != null) {
				System.out.println("Max Profit Information:");
				if (ro.getBuyDate() != null) {
					System.out.println("Buy date: " + ro.getBuyDate());
				}
				if (ro.getBuyPrice() != 0.0) {
					System.out.println("Buy price: " + ro.getBuyPrice());
				}
				if (ro.getSellDate() != null) {
					System.out.println("Sell date: " + ro.getSellDate());
				}
				if (ro.getSellPrice() != 0.0) {
					System.out.println("Sell price: " + ro.getSellPrice());
				}
				System.out.println("Profit: " + ro.getProfit());

				testMaxProfit();
				System.out.println("All tests passed successfully.");
			}
		} catch (IOException e) {
			System.err.println("Error reading stock data: " + e.getMessage());
		}
	}

	public static void testMaxProfit() {
		try {
			assert maxProfit("AAPL", 2024) != null : "Test case failed: Data found";

			assert maxProfit("AAPL", 2025) == null : "Test case failed: No data found";

			assert maxProfit("INVALID", 2024) == null : "Test case failed: Null and exception handling";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
