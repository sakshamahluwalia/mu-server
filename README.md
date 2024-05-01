## Requirements
- Maven
- openjdk version >= "19.0.2"

## Steps to run this application

Navigate to root of the project dir. Once you are in the root of the dir execute `ls` to confirm you see the following output.
```
payment-recorder % ls
README.md       pom.xml         src             target
```

run the following:
```
mvn compile
mvn exec:java -Dexec.mainClass="org.hsbc.Main"
```

You should see the following in your terminal:
```
starting to log payments...
Server started at: http://localhost:3000
```

If for some reason you are still unable to run the application. I have deployed the backend application on the URL http://159.203.49.211/

## How to use this application

There are 2 routes in this application

### POST /payment/currencyCode/Amount

- Make a post request to http://159.203.49.211/api/payment/hkd/200
- You should receive the following
```
{CAD=100}
```

### GET /reset-transactions

- Make a get request to http://159.203.49.211/api/reset-transactions
- If there are tracked payments then this will reset all recorded payments currently tracked and return `200` with the message `Transactions reset`
- If there are no tracked payments this route will return `400` with the message `no payments to reset`


## Assumptions made

1. Currency code is defined as a 3 character string ignoring case i.e `cad and CAD are treated the same`. 
2. Tracked amount for a currency is always >= 0.
    1. If user tries to subtract an amount greater than what is currently tracked for a given currency application throws an error
3. Tracked amount is always < 50,000
    1. If user tries to increase the amount past 50,000 application throws an error
4. If there is no tracked amount for a currency then application will not allow user to input 0 for said currency.
5. If there is no currency tracked there will be no output in the console.