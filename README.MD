=ezBanking application=
-----------------------

h3.abstract idea
----------------
It has crossed my mind that NoSQL and DDD would work well together because:
- NOSQL storage seems a good fit to store the model as documents...
- In DDD Invariants (consistency of model) is enforced in aggregates (and domain services) 
	which overcomse the "problem" of schemaless storage
 
h3.concrete
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
