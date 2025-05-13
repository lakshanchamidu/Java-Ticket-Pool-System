import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static String vendorName;
    private static TicketPool pool;
    public static void main(String[] args) throws InterruptedException {
        pool = new TicketPool();

        List<String> existingVendorIds = loadVendorIds("VendorIds");
        AtomicInteger vendorCounter = new AtomicInteger(existingVendorIds.size() + 1);
        String uniqueVendorId = UUID.randomUUID().toString();

        vendorName = "Vendor " + vendorCounter.getAndIncrement();

        Vendor[] vendors = new  Vendor[pool.getMaxTicketCapacity()];
        Vendor vendor = new Vendor(pool, vendorName, pool.getTotalTickets(), pool.getTicketReleaseRate());
        pool.setVendor(vendor);
        existingVendorIds.add(uniqueVendorId);

        Thread vendorThread = new Thread(vendor);
        vendorThread.start();
        vendorThread.join();

        saveVendorData(pool, uniqueVendorId);
        saveVendorIds("VendorIds", existingVendorIds);
    }
    private static List<String> loadVendorIds(String filename) {
        List<String> vendorIds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                vendorIds.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("No existing vendor IDs found, starting fresh.");
        }
        return vendorIds;
    }
    private static void saveVendorIds(String filename, List<String> vendorIds) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String id : vendorIds) {
                writer.write(id+"\n");

            }
        } catch (IOException e) {
            System.out.println("Error saving vendor IDs.");
            e.printStackTrace();
        }
    }
    private static void viewTicketPoolStatus() {
        System.out.println("Tickets in Pool: " + pool.getPoolSize());
        System.out.println("Tickets Remaining to Add: " + pool.getRemainingTicketsToAdd());
        System.out.println("Tickets Sold: " + pool.getTicketsSold());
    }
    private static void saveVendorData(TicketPool pool, String vendorId) {
        String fileName = "VendorsInfo.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();

            }
            writer.write(vendorName + " Information:\n");
            writer.write("Vendor ID: " + vendorId + "\n");
            writer.write("Total Tickets: " + pool.getTotalTickets() + "\n");
            writer.write("Ticket Release Rate: " + pool.getTicketReleaseRate() + "\n");
            writer.write("Customer Retrieval Rate: " + pool.getCustomerRetrievalRate() + "\n");
            writer.write("Max Ticket Capacity: " + pool.getMaxTicketCapacity() + "\n\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to vendor file.");
            e.printStackTrace();
        }
    }
}

