# 💳 Smart Billing System

A simple and user-friendly **Java Swing** application that helps small shops and businesses manage customer billing efficiently.

---

## 🧠 Introduction

The **Smart Billing System** is designed to simplify billing for small retail stores.  
It allows the user to add and remove products, automatically calculate totals, apply tax and discounts, and generate printable bills.  
The system also stores each bill as a `.txt` file, ensuring easy record keeping and reference.

---

## ⚙️ Features

- 🛍️ Add / Remove products dynamically  
- 💰 Auto-calculate Subtotal, Tax, and Discount  
- 🔢 Auto-increment Bill Number for every transaction  
- 👤 Customer name and phone entry fields  
- 🧾 Generate and Save printable bill (in `.txt` format)  
- 🕒 Shows Date and Time on bill  

---

## 🛠️ Technologies Used

- **Programming Language:** Java  
- **GUI Framework:** Swing  
- **File Handling:** Java I/O  
- **IDE Used:** Eclipse (can also be run on IntelliJ / NetBeans)

---

## 🚀 How to Run

1. Clone or download this repository:  
   ```bash
   git clone https://github.com/karansingh7786/Smart-Billing-System.git
   ```

2. Open the project in **Eclipse** or any Java IDE.

3. Navigate to `SmartBillingApp.java`.

4. Run the program:
   - Enter product name, quantity, and price.  
   - Click **"Add Product"** to insert items.  
   - Adjust **Tax %** and **Discount %** if needed.  
   - Click **"Update Total"** to calculate.  
   - Finally, click **"Print Bill"** to generate and save the bill.

5. The bill will be saved automatically in the project folder as  
   ```
   Bill_<BillNumber>.txt
   ```

---

## 🧾 Sample Bill Format

```
        SMART BILLING STORE
     123 Market Road, Mumbai
   Phone: +91-9876543210
====================================
Bill No: 1001
Customer: Rajesh Sharma
Phone: 9876543210
Date & Time: 04-10-2025 15:45:22
====================================
Product      Qty   Price   Subtotal
------------------------------------
Sugar        2     45.00   90.00
Tea Pack     1     120.00  120.00
------------------------------------
Subtotal: 210.00
Tax (18%): 37.80
Discount (10%): 21.00
GRAND TOTAL: 226.80
====================================
       THANK YOU! VISIT AGAIN
====================================
```

---

## 📸 Screenshots
<img width="970" height="664" alt="image" src="https://github.com/user-attachments/assets/8f9cc812-b5e9-4635-aab5-95a9bac8537f" />




---

## 👨‍💻 Author

**Developed by:** Karan Singh  
**Academic Project - 2025**  
Department of Computer Science (AI&ML) 
