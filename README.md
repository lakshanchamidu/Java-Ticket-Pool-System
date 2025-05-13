# 🎟️ Java Ticket Pool System

A Java-based multithreaded ticket management system that simulates vendors releasing tickets into a shared pool and customers retrieving them at different rates. It includes file persistence for vendor information and synchronized access to ensure thread safety.

---

## 📌 Features

- ✅ Vendor thread that releases tickets into the pool.
- ✅ Customer thread (structure ready) for retrieving tickets.
- ✅ Synchronized access to the shared ticket pool using `wait()` and `notifyAll()`.
- ✅ Console input for configuring ticketing parameters.
- ✅ Vendor information and IDs are saved to text files (`VendorIds`, `VendorsInfo.txt`).
- ✅ Real-time console output for ticket pool updates.

---

## 🚀 How It Works

1. **Configuration**: On running the program, the user is prompted to enter:
   - Total number of tickets
   - Ticket release rate (milliseconds)
   - Customer retrieval rate (milliseconds)
   - Maximum ticket pool capacity

2. **Ticket Release**: The `Vendor` thread releases tickets into the `TicketPool` queue one by one at the specified rate. If the queue reaches the max capacity, it waits.

3. **Ticket Pool**:
   - Uses a synchronized `Queue<String>` to store tickets.
   - Uses `wait()` and `notifyAll()` to manage capacity safely.
   - Tracks total tickets sold and remaining tickets to add.

4. **File Logging**:
   - Vendor info is logged in `VendorsInfo.txt`.
   - A UUID is generated for each vendor and saved in `VendorIds`.

---

## 🔧 How to Run

### 🖥️ Requirements

- Java JDK 8 or above
- Command-line terminal or IDE (e.g., IntelliJ, Eclipse)

### 📦 Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/Java-Ticket-Pool-System.git
   cd Java-Ticket-Pool-System
