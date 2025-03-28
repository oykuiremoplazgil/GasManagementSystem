import java.util.Random;
public class MaintenanceManager {

    private Pump[] pumps;
    private Random random;

    public MaintenanceManager(Pump[] pumps) {
        this.pumps = pumps;
        this.random = new Random();
    }


    public void repairPump(int pumpId) {
        for (Pump pump : pumps) {
            if (pump.getId() == pumpId && !pump.isOperational() && pump.isBrokenDown()) {
                pump.setBrokenDown(false);
                pump.setOperational(true);
                System.out.println("Pump " + pumpId + " has been repaired!");
                return;
            }
        }
        System.out.println("Pump " + pumpId + " is either operational, not broken down, or not found!");
    }


    public void simulateBreakdowns() {
        for (Pump pump : pumps) {
            if (random.nextDouble() < 0.20) {
                pump.setBrokenDown(true);
                pump.setOperational(false);
                System.out.println("Pump " + pump.getId() + " is now broken down!");
            }
        }
    }
    public void checkPumpStatus() {
        for (int i = 0; i < pumps.length; i++) {
            String status = pumps[i].isOperational() ? (pumps[i].isBrokenDown() ? "Broken Down" : "Operational") : "Not Operational";
            System.out.println("Pump " + pumps[i].getId() + " is " + status);
        }
    }
}