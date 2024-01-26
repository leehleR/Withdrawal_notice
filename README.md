# WithDrawal Notification

### Overview

The Withdrawal Notification is a comprehensive solution designed to manage investor profiles, investment products, withdrawal notices, and notifications in a seamless manner.

### Features
Investor Management:

* Create profiles.
Retrieve a list of all investors.

Product Management:

* Add new investment products.
Associate products with specific investors.

Withdrawal Notices:

* Generate withdrawal notices for investment products.
Ensure compliance with withdrawal limits and age restrictions.

CSV Export:

* Export withdrawal notices to CSV files for record-keeping.

Notifications:

* Receive notifications for completed withdrawals, including the closing balance.

### Technologies Used
* Spring Boot
* Hibernate
* Java
* Postman


## API Endpoints:

Investor Management:

- GET /enviro/investor: Retrieve all investors.
- POST /enviro/investor: Create a new investor.

```JSON
{
  "fullName": "Mike Ross",
  "age": 35,
  "gender": "male",
  "contact": {
  "phone": "1234567890",
  "email": "mike.ross@example.com"
  },
  "address": {
  "street": "123 Main St",
  "city": "Anytown"
  },
  "productsList": [
  {
  "productType": "SAVINGS",
  "productName": "Savings Account",
  "currentAmount":2500.0
  },
  {
  "productType": "RETIREMENT",
  "productName": "Retirement Fund",
  "currentAmount": 30000.0
  }
  ]
  }
```

Withdrawal Notices:
--- 
POST /enviro/withdrawal-notice/make: Create a withdrawal notice.

#### - Request Parameters:
- productId (Long): ID of the investment product.
- withdrawalAmount (Double): Amount to be withdrawn.

---
GET /enviro/withdrawal-notice/export: Export withdrawal notices to CSV.

#### - Request Parameters:
- productId (Long): ID of the investment product.
- investorId (Long): ID of the investor.
- startDate (DateTime): Start date of the date range (ISO 8601 format).
- endDate (DateTime): End date of the date range (ISO 8601 format).


Notifications:

Notifications are automatically sent upon successful withdrawal.

- Clone the repository:

`````bash
git clone https://github.com/yourusername/investment-management-system.git

