ezBanking application
-----------------------

abstract idea
----------------
It has crossed my mind that NoSQL and DDD would work well together because:
- NOSQL storage seems a good fit to store the model as documents...
- In DDD Invariants (consistency of model) is enforced in aggregates (and domain services) 
	which overcomse the "problem" of schemaless storage
 
concrete
-----------
Making a simple banking application using
- NOSQL solution (mongodb) (no transactions)
- DDD
eventually:
- REST API
- exceptionhandling with translation to property httpErroCodes for restctrl
- consumed by angular4 frontend...
- security etc...
- swagger to document endpoints
- hateoas? (hypermedia as the engine of the application state)


Requirements:
-------------
First iteration:
----------------
create REST API to 
- create account --> DONE
- list accounts --> DONE
- deposit money on an account --> DONE
- list balance per account --> DONE
- transfer money from one account to the other --> DONE
TODO : In AccountCtrl: iso returning Account (entity), create specific DTO's for each (view/response)purpose

later...:
----------------
- add security: 2 roles ADMIN, USER
ADMIN:
-- can create users
-- can create account for users
-- cannot modify a users accounts
USER:
-- a user can deposit money on any account
-- a user can only transfer money FROM his account to any other account
-- a user can only check his own balance
-- a user can only view transactions of his own accounts
Anonymous:
can do nothing...


DRAFT (business) MODEL DESCRIPTION
-----------------------
Account: 
* has a accountHolder (name of person)
* an id (which is currently the only way to uniquely identify, nee dto add bankaccountnr or something)
* a list of transactions
** Transactions (2types):
*** Deposit : add money to one account
*** Transfer : move money from one account to another account



HOWTO RUN:
----------
(must have angular/nodejs/npm/etc... installed and in the classpath!!)
* To Build jar with everything:
** go to parent module and : 
	`mvn clean install` (or package)
** then run : `java -jar backend/target/ezBAnk....jar`
--> app should be running on 8080
 
How the build works:
-------------------- 
is a multimodule project:
ezBank
|-> ezBank-frontend
|-> ezBank-backend

* frontEnd build result is put in backend/src/main/resources/static
* then backend is build including with the files from the frontend...
(result is an executable jar in backend/target/ that contains back and frontend)

HOW to run in development
* in ezBank/backend: `mvn spring-boot:run`  --> runs on 8080
* in ezBank/frontend: `npm start` OR `ng serve --proxy-config proxy.conf.json`  --> runs on 4200
The frontend "proxyconfig" will forwared all "http://host:4200/api/*" calls to "http://host:8080/api/*" 
--> this way you can have 2 servers running during development, one for clientside code and one for backend...


importing in eclipse:
---------------------
import existing maven project > select the parent folder ... import