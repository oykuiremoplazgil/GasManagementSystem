import java.util.Scanner;

public class Main {
    private static ReportingManager reportingManager = new ReportingManager();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date: ");
        String date = scanner.nextLine();
        while (true) {
            System.out.println("Enter your role (1-manager/2-cashier/3-maintenance/4-exit): ");
            int role = scanner.nextInt();
            scanner.nextLine();

            if (role == 4) {
                System.out.println("Bye...");
                break;
            }

                System.out.println("Enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Enter your password: ");
                String password = scanner.nextLine();

                User user = new User(username, password, role);
                user.roleEntry();

                FuelInventory fuelInventory = new FuelInventory(1000, 1000, 1000);
                PumpManage pumpManage = new PumpManage(fuelInventory);
                MaintenanceManager maintenanceManager = new MaintenanceManager(pumpManage.getPumps());
                maintenanceManager.simulateBreakdowns();

                switch (role) {
                    case 1:
                        while (true) {
                            System.out.println("\nSelect an operation:");
                            System.out.println("1. Generate Daily Report");
                            System.out.println("2. Generate Monthly Report");
                            System.out.println("3. Check Fuel Stock");
                            System.out.println("4. Exit");

                            int managerChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (managerChoice) {
                                case 1:
                                    reportingManager.generateDailyReport(date);
                                    break;
                                case 2:
                                   reportingManager.generateMonthlyReport(date);
                                   break;
                                case 3:
                                    fuelInventory.displayFuelStock();
                                    break;
                                case 4:
                                    System.out.println("Bye...");
                                    return;
                                default:
                                    System.out.println("Invalid choice, please try again.");
                            }
                        }

                    case 2:
                        System.out.println("Enter pump ID: ");
                        int pumpId = scanner.nextInt();
                        System.out.println("Enter liters to sell: ");
                        double liters = scanner.nextDouble();
                        System.out.println("Enter price per liter: ");
                        double pricePerLiter = scanner.nextDouble();

                        pumpManage.sellFuel(pumpId, liters);
                        reportingManager.recordSale(liters, pricePerLiter,date);

                        break;

                    case 3:
                        while (true) {
                            System.out.println("\nSelect an operation:");
                            System.out.println("1. Check Pump Status");
                            System.out.println("2. Repair Pump");
                            System.out.println("3. Exit");

                            int maintenanceChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (maintenanceChoice) {
                                case 1:
                                    maintenanceManager.checkPumpStatus();
                                    break;
                                case 2:
                                    System.out.println("Enter pump ID to repair: ");
                                    pumpId = scanner.nextInt();
                                    maintenanceManager.repairPump(pumpId);
                                    break;
                                case 3:
                                    System.out.println("Bye...");
                                    return;
                                default:
                                    System.out.println("Invalid choice, please try again.");
                            }
                        }

                    default:
                        System.out.println("Invalid role, please try again.");
                }
            }
        }
    }
