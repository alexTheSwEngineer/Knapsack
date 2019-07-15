# Knapsack   

This is a small exercise of the knapsack problem. 
The implementation assumes the scenario of a bakery that sells pastries in different packagings.
Each pastry, may come in different packages, and each package may have a different price.

The generic Knapsac problem solution is implemented in the class ```DiscretePositiveValueKnapsack```. 
Provided a integer target value to achieve, a collection of packets of any type and methods to extract value and weight of each packet this class will calculate:
* If the target is achievable
* What is the minimum weight to achieve the target value
* What are the exact packets to achieve the target value for the minimal weight

(Assuming there is an infinite supply of packets) 

Since pastry masters are not interested in knapsack there is a pastry calculator that calls the knapsack algorithm with the correct mappings.
More over pastry masters are not good at reading binary output so there is also a PastryPackagingPrinter that will format the results nicely.

You can see all of this in action in the MainTest.
There, a nice assortment of pastries like Vegemite Scroll, Blueberry Muffin and a Croissant together with some difficult default packaging are provided.
  
## Building the project  
  
The project can be built by running:  
 ```bash mvn clean install ```
 
 This will also automatically run the MainTest and print its results.