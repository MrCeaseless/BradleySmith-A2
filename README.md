# Amusement Park Ride Management System

## Overview
This project is a Java-based simulation of an amusement park ride management system.  
It implements queue management, ride history tracking, sorting, and CSV import/export functionality, following object-oriented programming principles.

The main class for ride functionality is **Ride.java**, which implements `RideInterface`.

---

## Features
### **Part 3 – Queue Management**
- Add visitors to the ride queue (FIFO order).
- Remove visitors from the front of the queue.
- Print the current queue with ticket details.

### **Part 4a – Ride History**
- Add visitors to the ride history once they have ridden.
- Check if a visitor is in the ride history.
- Display the total number of visitors.
- Print the ride history using an `Iterator` (required by brief).

### **Part 4b – Sorting**
- Sort ride history by visitor name and ticket number using a `Comparator`.
- Demonstrates case-sensitive sorting (e.g., "Alex Moon" vs "alex moon").

### **Part 6 – File Import/Export**
- **Export** ride history to a CSV file.
- **Import** ride history from a CSV file.
- Minimal CSV parsing with handling for quoted fields and commas.
- Skips invalid or malformed records.
- Generates a sample file (`ride_history.csv`) in the project directory.

---


