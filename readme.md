# Order Matcher Exercise #
This is an exercise to implement an order book with continuous matching of limit
orders with time priority.

This exercise requires Java 8, gradle 1.10+ and a compatible IDE, for example NetBeans.

## Instructions
In an electronic exchange an order book is kept: All
buy and sell orders are entered into this order book and the prices are
set according to specific rules. Bids and asks are matched and trades
occur.

This class keeps an order book, that can determine in real-time the
current market price and combine matching orders to trades. Each order
has a quantity and a price.

*The trading rules:*
It is a match if a buy order exist at a higher price or equal to a sell
order in the order book. The quantity of both orders is reduced as much as
possible. When an order has a quantity of zero it is removed. An order can
match several other orders if the quantity is large enough and the price is
correct. The price of the trade is computed as the order that was in the
order book first (the passive party).

The priority of the orders to match is based on the following:

  1. On the price that is best for the active order (the one just entered)
  2. On the time the order was entered (first come first served)

## Build and Run ##
First:
>cd OrderMatcherExercise

Compile:
>gradle build

Compile and run unit tests:
>gradle build

Run the application (through gradle):
>gradle run

Package the application:
>gradle installApp

Run the application (standalone):
>./build/install/OrderMatcherExercise/bin/OrderMatcherExercise

### Eclipse IDE ###

If you want to edit java files from within eclipse, run
>gradle eclipse

and then from within eclipse File -> Import... -> General -> Existing projects into workspace -> Next.. Browse.. browse to where you cloned repo -> Ok.. -> Select All -> Import.

### Netbeans IDE ###

Install the [Netbeans Gradle Plugin](http://plugins.netbeans.org/plugin/44510/gradle-support) and just open the project (the cloned repo).
