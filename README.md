# OOP-Design-course

# ✅ Report of the First Task of the Project

### 🔔 Changes Made for the New Message Sending Service:
- Changed the method name in the `MessageSender` interface.
- Changed the method name in the `EmailSender` class accordingly.
- Added a new **SMS sending service** in the `services` package.
- Modified the `ReservationService` class to:
  - Select the proper notification service dynamically.
  - Remove the default notifier which was previously set to Email.

---

### 💳 Changes Made for the New Payment Service:
- Added a new payment method to the `PaymentProcessor`.
- Modified the corresponding `case` in the `ReservationService` to support the new payment type.

---

# ✅ Report of the Second Task of the Project  
# SOLID Principles Evaluation Report  
**Hotel Room Reservation System**

---

## 1. Introduction  
This document evaluates the design of the hotel room reservation system based on the **SOLID principles**. The purpose of this evaluation is to identify which principles are **respected or violated**, explain **why**, and assess the overall design quality.

### Analyzed Classes:
- `Main`
- `ReservationService`
- `Reservation`
- `PaymentProcessor`
- `MessageSender`
- `EmailSender`
- `Room` / `LuxuryRoom`

---

## 2. Single Responsibility Principle (SRP)
> A class should have only one reason to change.

### ✅ Respected In:
- **`Reservation`**  
  Only responsible for storing reservation data and calculating total price.

- **`PaymentProcessor`**  
  Handles only payment-related operations.

- **`EmailSender`**  
  Responsible only for sending emails.

- **`Main`**  
  Responsible only for application startup and object creation.

### ❌ Violated In:
- **`ReservationService`**  
  Handles multiple responsibilities:
  - Reservation processing  
  - City-based discount logic  
  - Payment processing  
  - Invoice printing  
  - Notification sending  

- **`MessageSender`**  
  Interface name is generic but only supports email-related behavior.

---

## 3. Open/Closed Principle (OCP)
> Software entities should be open for extension but closed for modification.

### ❌ Violated In:
- **`ReservationService`**
  - Uses `switch` statements for selecting payment and notification methods.
  - Adding new types requires modifying this class.

- **`PaymentProcessor`**
  - Adding new payment methods requires modifying the class.

- **`MessageSender`**
  - Adding new message types requires modifying the interface.

### ✅ Respected In:
- **`Reservation`**
  - Pure data model; extensible without modification.

- **`EmailSender`**
  - New message senders can be added alongside it.

---

## 4. Liskov Substitution Principle (LSP)
> Subtypes must be substitutable for their base types.

### ✅ Respected In:
- **`LuxuryRoom` as a subtype of `Room`**

Room room = new LuxuryRoom("203", 150);
EmailSender implementing MessageSender
Fulfills the interface contract correctly.

## 5. Interface Segregation Principle (ISP)

> Clients should not be forced to depend on interfaces they do not use.

### ❌ Violated In:

- **`MessageSender`

Interface named generically but contains only an email-specific method.

Adding SMS or Push Notification would make the interface unnecessarily large.

### ✅ Respected In:

Other parts of the system do not currently depend on large or forced interfaces.

## 6. Dependency Inversion Principle (DIP)

> High-level modules should not depend on low-level modules. Both should depend on abstractions.

### ❌ Violated In:

- **`ReservationService`

new PaymentProcessor();
new EmailSender();


Direct dependency on concrete classes.

Makes testing and extending the class harder.

Main

Directly creates ReservationService without abstraction.

Results in tight coupling.
# SOLID Refactoring Report
**Hotel Room Reservation System**

---
# ✅ Report of the third Task of the Project

## Overview
This report compares the original hotel reservation system code with the refactored version and explains the actions taken to ensure **SOLID principles** are properly applied. The core functionality of the system has been preserved.

---

## 1. Single Responsibility Principle (SRP)

**Original Code:**  
- `ReservationService` handled multiple responsibilities: reservation processing, discount application, payment, invoice printing, and notification sending.  
- SRP was strongly violated.

**Refactored Code:**  
- `ReservationService` now manages **reservation flow only**.  
- Payment and notifications are handled by separate classes (`IPaymentProcessor`, `IMessageSender`).  
- Each class now has a **single responsibility** ✅.

---

## 2. Open/Closed Principle (OCP)

**Original Code:**  
- Switch statements were used to select payment and notification types.  
- Adding new methods required modifying existing classes.

**Refactored Code:**  
- Introduced interfaces for abstraction:
  - `IPaymentProcessor` → `PayByCard`, `PayByPayPal`, `PayByCash`, `PayByPerson`  
  - `IMessageSender` → `EmailNotifier`, `SmsNotifier`  
- `ReservationService` depends only on these interfaces.  
- New payment or notification types can be added **without modifying existing code** ✅.

---

## 3. Liskov Substitution Principle (LSP)

**Original Code:**  
- Partially respected: `LuxuryRoom` could replace `Room`.  
- Adding new subclasses could potentially violate behavior.

**Refactored Code:**  
- All subclasses of `Room`, `IPaymentProcessor`, and `IMessageSender` can safely replace their base types.  
- Behavior of the system remains correct regardless of the implementation ✅.

---

## 4. Interface Segregation Principle (ISP)

**Original Code:**  
- `MessageSender` interface only supported email.  
- Clients had to depend on irrelevant methods.  

**Refactored Code:**  
- `IMessageSender` receives the entire `Reservation` object.  
- Each notifier uses only the information it needs (email or phone).  
- Interface is now focused and small ✅.

---

## 5. Dependency Inversion Principle (DIP)

**Original Code:**  
- `ReservationService` directly instantiated concrete classes (`EmailSender`, `PaymentProcessor`).  
- Tight coupling made testing and extending difficult.

**Refactored Code:**  
- Dependencies are injected through constructors:
```java
IPaymentProcessor payment = new PayByPayPal();
IMessageSender notifier = new EmailNotifier();
ReservationService service = new ReservationService(payment, notifier);
```
## 6. Additional Improvements

SMS notification added (SmsSender). Each notifier chooses whether to use email or phone.

Multiple payment methods implemented without modifying ReservationService.

The system is fully extensible for new payment or notification channels without changing existing code.

# ✅ Report of the Forth Task of the Project

# Impact of Applying Object-Oriented Principles from the Beginning

## Comparison Between Original and Refactored Design  
Hotel Room Reservation System

This section explains **how many of the changes made in the first development step could have been avoided** if SOLID principles had been applied from the beginning, and **how many changes would actually be required** to add the two new features:

- ✅ Onsite Payment (Cash / In-Person)
- ✅ SMS Notification

---

## 1. Problems in the Original Design

In the original version of the project:

- `ReservationService` directly created:
  - `PaymentProcessor`
  - `EmailSender`
- Payment logic was implemented using a `switch` statement.
- Notification logic was also implemented using a `switch` statement.
- `MessageSender` interface was tightly coupled to **email only**.
- Dependencies were **not injected**.
- Adding a new payment or message type **required modifying multiple existing classes**.

This caused violations of:
- ❌ SRP
- ❌ OCP
- ❌ ISP
- ❌ DIP

---

## 2. Changes That Were Made in Step One

To add these two new features:
- Onsite Payment
- SMS Notification

The following changes were required in the original design:

1. **Renaming methods in `MessageSender` and `EmailSender`**
2. **Adding a new `SmsSender` class**
3. **Modifying `ReservationService` to dynamically select the notifier**
4. **Adding a new payment method inside `PaymentProcessor`**
5. **Modifying the payment `switch` inside `ReservationService`**

✅ Total changes required in the original design:  
**5 separate modifications**

---

## 3. If SOLID Had Been Applied From the Beginning

If the system had originally followed SOLID principles:

- `ReservationService` would depend only on:
  - `IPaymentProcessor`
  - `IMessageSender`
- All dependencies would be injected via constructor.
- No `switch` statements would exist for:
  - Payment selection
  - Message sending
- Each payment and notification type would be implemented as a **separate class**.

### As a Result:

✅ The following changes would have been completely **unnecessary**:

- Renaming methods in `MessageSender`
- Modifying `ReservationService` logic
- Adding new `switch` cases
- Changing existing payment logic

➡️ **At least 3 to 4 changes from Step One would have been avoided.**

---

## 4. How Many Changes Would Be Needed With Proper SOLID Design?

With a correct SOLID-based design, adding the two new features would require only:

### 1. Onsite Payment:
```java
class PayByPerson implements IPaymentProcessor {
    public void pay(double amount) {
        System.out.println("Paid in person: " + amount);
    }
}
```
### 2. SMS Notification:
class SmsSender implements IMessageSender {
    public void sendMessage(Reservation reservation) {
        System.out.println("Sending SMS to " + reservation.customer.mobile);
    }
}


✅ No changes to:

`ReservationService`

`MessageSender`

`Existing payment classes`

`Existing notifiers`

✅ Only injection in Main:
```java
IPaymentProcessor payment = new PayByPerson();
IMessageSender notifier = new SmsSender();
ReservationService service = new ReservationService(payment, notifier);
```

# ✅ Report of the Fifth Task of the Project
### As the new code follows solid principles it can now be develoded more easily .
### As a result , our app is more maintainable and new features can be easily added .
### Making a simple change in the codebase or adding a new feature will not require a big change in the codebase and 
### It also will not cause conflicts.
### 
### Since the dependency to different parts of the code has been lowered by followig the solid principles , testing 
### has become easier .


# I have used AI to write this report . 
