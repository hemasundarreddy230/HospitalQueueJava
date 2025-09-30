import java.util.PriorityQueue;
import java.util.Scanner;
class Patient implements Comparable<Patient> {
    private String name;
    private int severity;
    private int order;  
    private static int counter = 0;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
        this.order = counter++;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }
    @Override
    public int compareTo(Patient other) {
        if (this.severity != other.severity) {
            return Integer.compare(this.severity, other.severity);
        }
        return Integer.compare(this.order, other.order);
    }

    @Override
    public String toString() {
        String level = switch (severity) {
            case 1 -> "Emergency";
            case 2 -> "Serious";
            case 3 -> "General";
            default -> "Unknown";
        };
        return name + " (" + level + ")";
    }
}
public class HospitalQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Patient> pq = new PriorityQueue<>();

        while (true) {
            System.out.println("\n--- Hospital Queue System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Treat Patient");
            System.out.println("3. View Waiting Patients");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();
                    System.out.println("Severity: 1=Emergency, 2=Serious, 3=General");
                    System.out.print("Enter severity (1-3): ");
                    int severity = sc.nextInt();
                    sc.nextLine();
                    pq.add(new Patient(name, severity));
                    System.out.println("✅ Patient " + name + " registered with severity " + severity);
                }
                case 2 -> {
                    if (pq.isEmpty()) {
                        System.out.println("⚠️ No patients to treat.");
                    } else {
                        Patient next = pq.poll();
                        System.out.println("💉 Treating patient: " + next);
                    }
                }
                case 3 -> {
                    if (pq.isEmpty()) {
                        System.out.println("⚠️ No patients waiting.");
                    } else {
                        System.out.println("\n📋 Current Waiting List:");
                        for (Patient p : pq) {
                            System.out.println(" - " + p);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("👋 Exiting system. Stay healthy!");
                    sc.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

}
