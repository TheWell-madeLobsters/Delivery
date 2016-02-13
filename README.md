# Hash Code
## Delivery
Problem statement for Online Qualification Round, Hash Code 2016

## Introduction
The internet has profoundly changed the way we buy things, but the online shopping of today is likely not the end of that change; after each purchase we still need to wait multiple days for physical goods to be carried to our doorstep.

This is where drones come in - autonomous, electric vehicles delivering online purchases.
Flying, so never stuck in traffic.
As drone technology improves every year, there remains a major issue: how do we manage and coordinate all those drones?

## Task
Given a fleet of drones, a list of customer orders and availability of the individual products in warehouses, schedule the drone operations so that the orders are completed as soon as possible.

## Problem description
### Map
The simulation takes place on a two-dimensional grid.
The grid is **not** cyclic and a drone cannot fly outside of the grid.
The drones can fly over all cells within the grid.

Each cell is identifies by a pair of integer coordinates **[r,c]** (*0 <= r < row count*, *0 <= c < column count*).

### Products
There are a number of product types available for order.
Each product type has one or more product items available in warehouses.
Each product type has a fixed product weight, identical for all product items.
Every product weight is guaranteed to be smaller or equal to the maximum payload that a drone can carry.

### Warehouses
Product items are stored in several warehouses.
Each warehouse is located in one particular cell of the grid, different for each warehouse.
Each warehouse initially stocks a known number of product items of each product type. No new product items beyond the initial availability will be stocked in the warehouses during simulation, but the drones can transport product items between the warehouses.
Any warehouse does not necessarily need to have every product type available.

### Orders
Each order specifies the product items purchased by the customer.
The product items in an order can be of one or multiple product types and can contain multiple product items of the same product type.

Each order specifies the cell in the grid where the product items have to be delivered.
Individual product items can be delivered in multiple steps, in any order.
It is valid to deliver the individual product items of an order using multiple drones, including using different drones at the very same time.

It is guaranteed that for each product type, the total number of product items in all orders is **not grater** than the total availability of product items of this product type in all warehouses.

It is **not** required to deliver all orders (see the Scoring section at the end).

### Drones
Drones transport product items from warehouses to customers and between the warehouses.

The drones always use the shortest path to fly from one cell in the grid to another.
The distance from cell [*Ra*,*Ca*] to cell [*Rb*,*Cb*] is calculated as *((Ra - Rb)^2 + (Ca - Cb)^2)^-2* (two-dimensional Euclidean distance).
We define a drone flight as a single movement of the drone that happens between two subsequent commands for the given drone (as described below).
Each drone flight takes **one turn** per one unit of distance between the start and the destination cell, **rounded up** to the next integer.

Multiple drones can be in the same cell at any moment - they never collide, as they can fly at different altitudes.

*For example, if the distance to be flown is 2.9 distance units, the duration of the flight is 3 turns.
If the distance to be flown is exactly 4 distance units, the duration of the flight is 4 turns.*

**At the beginning of the simulation, all drones are at the first warehouse (warehouse with id 0).**

### Commands
Each drone can be given the following basic commands:
- **Load**: Moves the specified number of items of the specified product type from a warehouse to the drone's inventory.
If the drone isn't at the warehouse it will fly there using the shortest path before loading the product items.
The requested number of items of the specified product type must be available in the warehouse.
The total weight of the items in the drone's inventory after the load cannot be bigger than the drone's maximum load.
- **Deliver**: Delivers the specified number of items of the specified product type to a customer.
If the drone isn't at the destination it will fly there using the shortest path before delivering the product items.
The drone must have the requested number of items of the specified product type in its inventory.

Each drone can also be given the following advanced commands.
These commands are **not** necessary to solve the problem, but you can use them to further improve your solution.
- **Unload**: Moves the specified number of items of the specified product type from drone's inventory to a warehouse. If the drone isn't at the warehouse it will fly there using the shortest path before unloading the product items.
The drone must have the requested number of items of the specified product type in its inventory.
- **Wait**: Waits the specified number of turns in the drone's current location.

### Simulation
The simulation proceeds in **T** turns, from 0 to *T - 1*.
A drone executes the commands issued to it in the order in which they are specified, one by one.
The first command issued to the drone starts in turn 0.

The duration of a command depends on its type:
- Each **Load**/**Deliver**/**Unload** command takes *d + 1* turns, where *d* is the distance travelled by the drone to perform the requested action (*d* can be 0 if the drone is already in the required location).
When the command starts, the drone flies to the required location in *d* turns.
Then, the *actual action* (loading, delivery or unloading) takes place and takes 1 turn.
- Each **Wait** command takes *w* turns, where *w* is the specified number of turns.

*For* **example** *let's assume that at the beginning of the simulation (at the beginning of turn 0) a drone is in warehouse 0 at the cell [1,1], warehouse 1 is in cell [1,3], customer order 0 has to be delivered to [1,4], and p and q are some product types, the items of which are part of order 0.*

*Let's consider the following commands:*
- **Load one item of product type p at warehouse 0.** *This will take 1 turn: 0 turns to get to the warehouse and 1 turn to load the item.*
- **Load five items of product type q at warehouse 1.** *This will take 3 turns: 2 turns to get from [1,1] to the warehouse at [1,3] and 1 turn to load the items.*
- **Deliver one item of product type p for customer order 0.** *This will take 2 turns: 1 turn to get from [1,3] to the delivery cell at [1,4] and 1 turn to deliver the item.*
- **Deliver five items of product type q for customer order 0.** *This will take 1 turn: 0 turns to get to the delivery cell and 1 turn to deliver the items.*

If there are multiple *actual actions* (see above) taking place in the same turn in the same warehouse, all unloading commands are processed **before** all loading commands.

*For* **example** *let's assume that in one turn, three different drones are all located at warehouse 0 and ordered to perform the following actions:*
- *Load one item of product type p at warehouse 0. (first drone)*
- *Load one item of product type p at warehouse 0. (second drone)*
- *Unload two items of type p at warehouse 0. (third drone)*

*Because unloading takes precedence over loading, all commands will succeed even if there were no items of product type p at the warehouse before this turn.*

## Input data set
The input data is provided as a data set file - a plain text file containing exclusively ASCII characters with lines terminated with a single '`\n`' character at the end of each line (UNIX-style line endings).

Product types, warehouses and orders are referenced by integer IDs.
There are **P** product types numbered from 0 to *P - 1*, **W** warehouses numbered from 0 to *W - 1* and **C** orders numbered from 0 to *C - 1*.

### File format
The first section of the file describes the **parameters of the simulation**.
This section contains a single line containing the following natural numbers separated by single space:
- number of rows in the area of the simulation (*1 <= number of rows <= 10000*)
- number of columns in the area of the simulation (*1 <- number of columns <= 10000*)
- **D** - number of drones available (*1 <= D <= 1000*)
- deadline of the simulation (*1 <= deadline of the simulation <= 1000000*)
- maximum load of a drone (*1 <= maximum load of a drone <= 10000*)

The next section of the file describes the **weights of the products available for orders**.
This section contains:
- a line containing the following natural number:
  - **P** - the number of different product types available in warehouses (*1 <= P <= 10000*)
- a line containing **P** natural numbers separated by single spaces denoting weights of subsequent product types, from product type 0 to product type **P - 1**.
For each weight, *1 <= weight <= maximum load of a drone*.

The next section of the file describes the **warehouse and availability of individual product types** at each warehouse.
This section contains:
- a line containing the following natural number:
  - **W** - the number of warehouses (*1 <= W <= 10000*)
- two lines for each warehouse, each two lines describing the subsequent warehouses 
