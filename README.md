# API autotests for Tocos

## Introduction

This project contains automated tests for the Tocos API. This API does not exist, but we assume it does and behaves as expected based on the descriptions below.

Tocos is a currency backed by carbon mitigation assets, and the API allows users to buy, sell, and transact Tocos with other users.

The API has several endpoints including:

- POST /users: Create a new user
- POST /buy: Buy Tocos using fiat currency, and add to a user's account
- POST /sell: Sell Tocos and convert back into fiat currency
- POST /transactions: Make a transaction from one user's account to another
- GET /transactions/{userId}: Get the transaction history for a user

The task is to write automated tests for the /transactions, /buy, and /sell endpoints.


## Technology stack

- Java 15
- JUnit5
- RestAssured
- WireMock


## Notes

We have elementary API schemes for test purposes and quite basic tests.

Authorization is omitted.

Performance tests are omitted.

Security tests are omitted.

In a real project, we would:
- Create users with specific parameters in the database and test against an actual API, but in this case, we use WireMock stubs
- Check users' balances after transactions, not only the response status code and message
- Consider different currencies and currency rates changing over time
- Handle situations like interrupted transactions
- And more.


## Assumptions:

All users have a daily limit set (e.g., the field constraint in the database would be NOT NULL).

The daily limit for buying, selling, and transferring Tocos is the same.

Tocos has three digits after the decimal point (based on a screenshot from the official website).


## Setup and installation

- Clone the repository
```
git clone https://github.com/deweinee/tocos-api-autotests.git
```
- Ensure you have Java 15 installed
- Ensure you have Maven installed
- Use Maven to install the required dependencies:
```
mvn clean install
```


## Running the Tests

```mvn test```
