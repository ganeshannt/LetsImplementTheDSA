# Problem Definition

Design a Ride Sharing application where drivers can offer rides (origin, destination, number of seats), and riders can request rides (origin, destination, number of seats).

An algorithm calculates the ride amount charged for a given ride based on distance and the number of seats.

## Features
1. When the ride closes, show the amount charged to the rider.
2. **Ride Amount Calculation**:
    - **If Number of Seats ≥ 2**:  
      `Ride Amount = Number of kilometers * Number of seats * 0.75 * Amount Charged per KM`
    - **If Number of Seats = 1**:  
      `Ride Amount = Number of kilometers * Amount Charged per KM`

The program should accept input for two or more drivers and multiple riders requesting rides. Multiple rides can happen simultaneously.

---

## Assumptions
1. **Amount Charged per KM** = 20
2. **Number of Kilometers** = `Destination - Origin`
3. All values are integers.

---

## Test Case

### Case 1: A requests a ride (R1) with 1 seat
- **Input**: A requests `50, 60, 1`
- **Output**:  
  `Ride Amount = 10 * 20 (Amount/KM) = 200`

### Case 2: A requests a ride (R1) with 2 seats
- **Input**: A requests `50, 60, 2`
- **Output**:  
  `Ride Amount = 10 * 2 * 0.75 * 20 (Amount/KM) = 300`

---

## Bonus Features
1. **Preferred Rider**: A rider is upgraded to a preferred rider if they have completed more than 10 rides.
    - **Preferred Rider Amount Calculation**:
        - **If Number of Seats ≥ 2**:  
          `Preferred Ride Amount = Number of kilometers * Number of seats * 0.5 * Amount Charged per KM`
        - **If Number of Seats = 1**:  
          `Preferred Ride Amount = Number of kilometers * Amount Charged per KM * 0.75`

---

## Expected Functionalities
1. **Add Driver** (name)
2. **Add Rider** (name)
3. **Create Ride** (id, origin, destination, number of seats)
4. **Create/Update Ride** (id, origin, destination, number of seats)
5. **Withdraw Ride** (id)
6. **Close Ride and Return Ride Amount Charged**

---

## Expectations
1. Create sample data. It can be included in a file, test case, or main driver program.
2. The code should be demo-able via the main driver program or test cases.
3. Code should be **modular** and demonstrate basic **OOP design principles**:
    - Avoid mixing responsibilities between classes.
4. The code should be **extensible**:
    - Use interfaces and contracts to allow for adding/removing functionality without rewriting the entire codebase.
5. Handle edge cases gracefully.
6. Ensure the code is **legible**, **readable**, and adheres to **DRY** principles.
7. Database integration is **not required**.

---

## Guidelines
1. **Do not access the internet** except for syntax references.
2. Use any programming language and IDE of your choice.
3. The entire code must be your **own work**.
