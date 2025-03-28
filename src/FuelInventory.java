public class FuelInventory {
    private double unleadedStock;
    private double superUnleadedStock;
    private double dieselStock;

    public FuelInventory(double unleadedStock, double superUnleadedStock, double dieselStock) {
        this.unleadedStock = unleadedStock;
        this.superUnleadedStock = superUnleadedStock;
        this.dieselStock = dieselStock;

    }
    public boolean checkFuelAvailability(String fuelType, double liters) {
        switch (fuelType.toLowerCase()) {
            case "unleaded":
                return unleadedStock >= liters;
            case "super unleaded":
                return superUnleadedStock >= liters;
            case "diesel":
                return dieselStock >= liters;
            default:
                System.out.println("Invalid fuel type");
                return false;
        }
    }

    public void deductFuel(String fuelType, double liters) {
        switch (fuelType.toLowerCase()) {
            case "unleaded":
                if (unleadedStock >= liters) {
                    unleadedStock -= liters;
                } else {
                    System.out.println("Insufficient unleaded fuel in inventory.");
                }
                break;
            case "super unleaded":
                if (superUnleadedStock >= liters) {
                    superUnleadedStock -= liters;
                } else {
                    System.out.println("Insufficient super unleaded fuel in inventory.");
                }
                break;
            case "diesel":
                if (dieselStock >= liters) {
                    dieselStock -= liters;
                } else {
                    System.out.println("Insufficient diesel fuel in inventory.");
                }
                break;
            default:
                System.out.println("Invalid fuel type");
                break;
        }
    }

    public void displayFuelStock() {
        System.out.println("Unleaded: " + unleadedStock + " liters");
        System.out.println("Super Unleaded: " + superUnleadedStock + " liters");
        System.out.println("Diesel: " + dieselStock + " liters");
    }

}
