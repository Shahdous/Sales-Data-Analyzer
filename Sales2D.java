/**
 * Sales2D Class
 * @author Shahdous Zaman Khan Prohor
 */
import java.util.Arrays;
import java.util.Random;

public class Sales2D {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Sales2D <number_of_weeks>");
            return;
        }

        Random random = new Random();
        final int NUMBER_OF_WEEKS = Integer.parseInt(args[0]);

        if (NUMBER_OF_WEEKS <= 0) {
            System.out.println("Number of weeks must be a positive integer.");
            return;
        }

        final int NUMBER_OF_DAYS = 7;
        int[][] salesAmountPerWeek = new int[NUMBER_OF_WEEKS][NUMBER_OF_DAYS];

        System.out.println("\nSales Data:");
        System.out.println("\n Monday  Tuesday  Wednesday  Thursday  Friday  Saturday  Sunday");
        System.out.println("______________________________________________________________");

        for (int weeks = 0; weeks < NUMBER_OF_WEEKS; weeks++) {
            System.out.printf("\nWeek %2d: ", weeks + 1);
            for (int days = 0; days < NUMBER_OF_DAYS; days++) {
                salesAmountPerWeek[weeks][days] = 100000 + random.nextInt(400001);
                String formattedAmount = formatAmount(salesAmountPerWeek[weeks][days]);
                System.out.printf("%10s ", formattedAmount);
            }
        }

        System.out.println(); // Newline after the data table

        // Compute lowest and highest sales per week
        double[] lowestSale = new double[NUMBER_OF_WEEKS];
        double[] highestSale = new double[NUMBER_OF_WEEKS];
        Arrays.fill(lowestSale, Double.MAX_VALUE);
        Arrays.fill(highestSale, Double.MIN_VALUE);

        for (int weeks = 0; weeks < NUMBER_OF_WEEKS; weeks++) {
            for (int days = 0; days < NUMBER_OF_DAYS; days++) {
                if (salesAmountPerWeek[weeks][days] < lowestSale[weeks]) {
                    lowestSale[weeks] = salesAmountPerWeek[weeks][days];
                }
                if (salesAmountPerWeek[weeks][days] > highestSale[weeks]) {
                    highestSale[weeks] = salesAmountPerWeek[weeks][days];
                }
            }
        }

        // Display statistics for each week
        System.out.println("\nStatistics:");
        double[] difference = new double[NUMBER_OF_WEEKS];
        for (int weeks = 0; weeks < NUMBER_OF_WEEKS; weeks++) {
            difference[weeks] = highestSale[weeks] - lowestSale[weeks];
            System.out.printf("Week %2d: ", weeks + 1);
            System.out.print("Lowest Sale: " + formatAmount((int) lowestSale[weeks])
                    + ", Highest Sale: " + formatAmount((int) highestSale[weeks])
                    + ", Difference: " + formatAmount((int) difference[weeks]));
            System.out.println();
        }

        // Organizing sales data by day across weeks
        int[][] weeklySalesForEachDay = new int[NUMBER_OF_DAYS][NUMBER_OF_WEEKS];
        for (int days = 0; days < NUMBER_OF_DAYS; days++) {
            for (int weeks = 0; weeks < NUMBER_OF_WEEKS; weeks++) {
                weeklySalesForEachDay[days][weeks] = salesAmountPerWeek[weeks][days];
            }
        }

        // Calculate average sales for each day of the week
        int[] averageSalesForEachDay = new int[NUMBER_OF_DAYS];
        int totalSales = 0;
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (int days = 0; days < NUMBER_OF_DAYS; days++) {
            for (int weeks = 0; weeks < NUMBER_OF_WEEKS; weeks++) {
                totalSales += weeklySalesForEachDay[days][weeks];
            }
            averageSalesForEachDay[days] = totalSales / NUMBER_OF_WEEKS;
            totalSales = 0;
            // Display average sale for each day (optional check)
            // System.out.println("Average sale for " + daysOfWeek[days] + ": " + formatAmount(averageSalesForEachDay[days]));
        }

        // Find and display the day with the lowest average sales
        int lowestAverageSale = Integer.MAX_VALUE;
        for (int days = 0; days < NUMBER_OF_DAYS; days++) {
            if (averageSalesForEachDay[days] < lowestAverageSale) {
                lowestAverageSale = averageSalesForEachDay[days];
            }
        }

        for (int days = 0; days < NUMBER_OF_DAYS; days++) {
            if (lowestAverageSale == averageSalesForEachDay[days]) {
                System.out.println("Lowest Average sale day: " + daysOfWeek[days]);
                System.out.println("Average Sales: " + formatAmount(lowestAverageSale));
            }
        }
    }

    // Helper method to format amounts into dollar format
    private static String formatAmount(int formattedAmount) {
        double amountInDollars = formattedAmount / 100.0;
        return String.format("$%,.2f", amountInDollars);
    }
}
