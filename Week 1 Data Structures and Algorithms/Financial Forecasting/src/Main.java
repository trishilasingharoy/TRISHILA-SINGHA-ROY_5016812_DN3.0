
class FutureValuePredictor {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: if no periods left, return present value
        if (periods == 0) {
            return presentValue;
        }
        // Recursive case: calculate future value for one less period
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    // Method to calculate the average growth rate based on past values
    public static double calculateAverageGrowthRate(double[] pastValues) {
        double totalGrowthRate = 0;
        int periods = pastValues.length - 1;
        for (int i = 1; i < pastValues.length; i++) {
            totalGrowthRate += (pastValues[i] - pastValues[i - 1]) / pastValues[i - 1];
        }
        return totalGrowthRate / periods;
    }

    // Recursive method to predict future value based on past growth rates
    public static double predictFutureValue(double[] pastValues, int periods) {
        double averageGrowthRate = calculateAverageGrowthRate(pastValues);
        return calculateFutureValue(pastValues[pastValues.length - 1], averageGrowthRate, periods);
    }

    public static void main(String[] args) {
        double presentValue = 1150.0; // Initial value for future value calculation
        double growthRate = 0.09; // Growth rate (5%) for future value calculation
        int futurePeriods = 10; // Number of periods (years) for future value calculation

        double futureValue = calculateFutureValue(presentValue, growthRate, futurePeriods);
        System.out.println("Future Value after " + futurePeriods + " periods: $" + futureValue);

        double[] pastValues = { 4000.0, 1150.0, 1100.5, 2157.93, 15515.51 }; // Example past values
        int predictionPeriods = 5; // Number of future periods to predict

        double predictedFutureValue = predictFutureValue(pastValues, predictionPeriods);
        System.out.println("Predicted Future Value after " + predictionPeriods + " periods: $" + predictedFutureValue);
    }
}

