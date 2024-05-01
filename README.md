## Requirements
- Maven
- openjdk version "19.0.2"

## Steps to run this application

navigate to root of the project diur. Once you are in the root of the dir execute `ls` to confirm you see the following output.
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

## How to use this application

- Make a post request to http://localhost:3000/CAD/100.
- You should receive the following

![alt text](<Screen Shot 2024-04-30 at 11.33.23 PM.png>)

## Assumptions made

1. Currency code is defined as a 3 character string ignoring case i.e `cad and CAD are treated the same`. 
2. Tracked amount for a currency is always >= 0.
    1. If user tries to subtract an amount greater than what is currently tracked for a given currency application throws an error
3. Tracked amount is always < 50,000
    1. If user tries to increase the amount past 50,000 application throws an error
4. If there is no tracked amount for a currency then application will not allow user to input 0 for said currency.
5. If there is no currency tracked there will be no output in the console.