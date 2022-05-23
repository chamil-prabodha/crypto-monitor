# Crypto-Monitor REST API

This is a RESTful api developed using Spring Boot framework to get price information of a cryptocurrency

## Important Notes
* API provides two endpoints to list available cryptocurrencies and get price information given a currency
* A third party API was used to derive currency from the IP address provided. This API has rate limitations and will return an error when getting IP address information. Due to this price calculation would fail in some occasions.

#### Due to time constraints the following has not been implemented
* User authentication
* Endpoint for list historical price of a currency
* CI/CD
* Unit tests for some API and Service layer

## API Spec
#### GET /api/currency/list

```
success response
{
    "success": true,
    "data": [
        {
            "id": "bitcoin",
            "symbol": "btc",
            "name": "Bitcoin"
        },
        {
            "id": "ethereum",
            "symbol": "eth",
            "name": "Ethereum"
        }
    ],
    "error": null
}
```

```
error response
{
    "success": false,
    "data": []
    "error": {
        "code": "400",
        "message": "IP info API for getting details of IP addresses is not available",
        "additionalInfo": "429 Too Many Requests: \"{<EOL>    \"error\": true,<EOL>    \"reason\": \"RateLimited\",<EOL>    \"message\": \"Visit https://ipapi.co/ratelimited/ for details\"<EOL>}\""
    }
}
```

#### GET /api/price/{currency}?ip={ip-address}
```
success response
{
    "success": true,
    "data": {
        "id": "bitcoin",
        "symbol": null,
        "name": null,
        "price": 30450,
        "currencySymbol": "$",
        "currencyName": "US Dollar"
    },
    "error": null
}
```

```
error response
{
    "success": false,
    "data": {
        "ip": null,
        "currency": null,
        "currencyName": null
    },
    "error": {
        "code": "400",
        "message": "IP info API for getting details of IP addresses is not available",
        "additionalInfo": "429 Too Many Requests: \"{<EOL>    \"error\": true,<EOL>    \"reason\": \"RateLimited\",<EOL>    \"message\": \"Visit https://ipapi.co/ratelimited/ for details\"<EOL>}\""
    }
}
```

## Test
```
./gradlew clean test
```

## Build
```
./gradlew clean build
```

## Steps to start the API
### Prerequisites
1. Gradle 7.4.1
2. Java 17 onwards

### Steps

1. Run `./gradlew clean build`. The jar will be created in `/build/libs/` directory
2. Run `java -jar build/libs/crypto-monitor-0.0.1-SNAPSHOT.jar`. The API will be running on port 8080
