# Online Medicine Shopping System

## Requirement description: 
- To provide computerized data storage facility. 
- Users can search easily any record.  
- The new system requires less time for completion of any work.  
- All the stock of medicine is update automatically in the new system. 
- The system is user friendly and anyone having computer knowledge can handle it easily. 
- Maintaining stock, Supplier information, Customer information & bill information are easy.

## Roles and Use cases: 
### Admin:
- Login: Admin need to login by providing the login credentials.
- Product Entry: Admin can enter details about new medicine products details.
- View Order: Admin can view details about the order placed by the user.
- View Users Details: Admin can view all the registered user’s details.

### User:
- Registration: User can register on the system and get his online account on site.
- Login: The user has to login into the system and then he can make use of the system resources. The user need not login all the time; once he’s logged in, he is remembered until he logs out.
- View Products: The products are arranged and can be viewed in categories.
- Add to Cart: Users can add multiple products to cart.
- Pay using Card: After total bill is calculated user can pay via credit card online (dummy).
- View Order: User can view details about the order placed.

## Requirements:
- java 11
- docker
- docker compose
- PostgreSQL

## Installation:
```bash
cd *into project folder*
docker pull suryansh4544/onlinemedicineshoppingsystem
docker-compose -up
```
