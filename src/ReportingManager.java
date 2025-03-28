
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportingManager {
    private static final int MAX_RECORDS = 1000;
    private SaleRecord[] dailySales = new SaleRecord[MAX_RECORDS];
    private SaleRecord[] monthlySales = new SaleRecord[MAX_RECORDS];
    private int dailyIndex = 0;
    private int monthlyIndex = 0;

    public void recordSale(double litersSold, double pricePerLiter, String date) {
        double sale = litersSold * pricePerLiter;
        SaleRecord record = new SaleRecord(date, litersSold, sale);

        if (dailyIndex < MAX_RECORDS) {
            dailySales[dailyIndex++] = record;
        }


        String monthYear = date.substring(0, 7);
        if (monthlyIndex < MAX_RECORDS) {
            monthlySales[monthlyIndex++] = new SaleRecord(monthYear, litersSold, sale);
        }

        System.out.println("Recorded sale: " + litersSold + " liters, " + sale + " currency units");
    }

    public void generateDailyReport(String date) {
        double totalSales = 0;
        double totalFuelSold = 0;

        for (int i = 0; i < dailyIndex; i++) {
            if (dailySales[i].getDate().equals(date)) {
                totalSales += dailySales[i].getSale();
                totalFuelSold += dailySales[i].getLitersSold();
            }
        }

        String report = "Date: " + date + "\n" +
                "Total Fuel Sold: " + totalFuelSold + " liters\n" +
                "Total Sales: " + totalSales + " currency units\n";
        System.out.println(report);
        saveToFile(report, "daily_report_" + date + ".txt");
    }


    public void generateMonthlyReport(String monthYear) {
        double totalSales = 0;
        double totalFuelSold = 0;

        for (int i = 0; i < dailyIndex; i++) {
            SaleRecord record = dailySales[i];
            String recordMonthYear = record.getDate().substring(0, 7);

            if (recordMonthYear.equals(monthYear)) {
                totalSales += record.getSale();
                totalFuelSold += record.getLitersSold();
            }
        }


        String report = "Month-Year: " + monthYear + "\n" +
                "Total Fuel Sold: " + totalFuelSold + " liters\n" +
                "Total Sales: " + totalSales + " currency units\n";
        System.out.println(report);
        saveToFile(report, "monthly_report_" + monthYear + ".txt");
    }

    private void saveToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Report saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the report: " + e.getMessage());
        }
    }


    private static class SaleRecord {
        private String date;
        private double litersSold;
        private double sale;

        public SaleRecord(String date, double litersSold, double sale) {
            this.date = date;
            this.litersSold = litersSold;
            this.sale = sale;
        }

        public String getDate() {

            return date;
        }

        public double getLitersSold() {

            return litersSold;
        }

        public double getSale() {
            return sale;
        }
    }
}

