
    import java.util.Random;

    public class PumpManage {
        private Pump[] pumps;
        private Random random;
        private FuelInventory fuelInventory;

        public PumpManage(FuelInventory fuelInventory) {
            this.pumps = new Pump[6];
            this.random = new Random();
            this.fuelInventory = fuelInventory;
            initializePumps();
        }

        private void initializePumps() {
            pumps[0] = new Pump(1, "Unleaded");
            pumps[1] = new Pump(2, "Super Unleaded");
            pumps[2] = new Pump(3, "Unleaded");
            pumps[3] = new Pump(4, "Super Unleaded");
            pumps[4] = new Pump(5, "Unleaded");
            pumps[5] = new Pump(6, "Diesel");
        }

        public Pump[] getPumps() {
            return this.pumps;
        }

        public void updatePump(int pumpId, String fuelType) {
            for (Pump pump : pumps) {
                if (pump.getId() == pumpId) {
                    pump.setFuelType(fuelType);
                    System.out.println("Pump " + pumpId + " updated to " + fuelType);
                    return;
                }
            }
            System.out.println("Pump " + pumpId + " not found!");
        }

        public void sellFuel(int pumpId, double liters) {
            for (Pump pump : pumps) {
                if (pump.getId() == pumpId) {
                    if (pump.isBrokenDown()) {
                        System.out.println("Pump " + pumpId + " is currently broken down and cannot be used.");
                        return;
                    }
                    String fuelType = pump.getFuelType();
                    if (fuelInventory.checkFuelAvailability(fuelType, liters)) {
                        pump.dispenseFuel(liters);
                        fuelInventory.deductFuel(fuelType, liters);
                        System.out.println(liters + " liters of " + fuelType + " sold from Pump " + pumpId);


                        if (random.nextDouble() < 0.20) {
                            pump.setBrokenDown(true);
                            System.out.println("Pump " + pumpId + " has broken down!");
                        }
                    } else {
                        System.out.println("Insufficient fuel in inventory for " + fuelType);
                    }
                    return;
                }
            }

        }
    }

    class Pump {
        private int id;
        private String fuelType;
        private double fuelLevel;
        private boolean operational;
        private boolean brokenDown;

        public Pump(int id, String fuelType) {
            this.id = id;
            this.fuelType = fuelType;
            this.fuelLevel = 1000;
            this.operational = true;
            this.brokenDown = false;
        }

        public int getId() {
            return id;
        }

        public String getFuelType() {
            return fuelType;
        }

        public void setFuelType(String fuelType) {
            this.fuelType = fuelType;
        }

        public double getFuelLevel() {
            return fuelLevel;
        }
        public boolean isOperational() {
            return operational && !brokenDown;
        }

        public void setOperational(boolean operational) {

            this.operational = operational;
        }
        public boolean isBrokenDown() {

            return brokenDown;
        }

        public void setBrokenDown(boolean brokenDown) {
            this.brokenDown = brokenDown;
        }


        public void dispenseFuel(double liters) {
            if (fuelLevel >= liters) {
                fuelLevel -= liters;
                System.out.println(liters + " liters of " + fuelType + " dispensed from Pump " + id);
            } else {
                System.out.println("Not enough fuel in Pump " + id);
            }
        }
    }




