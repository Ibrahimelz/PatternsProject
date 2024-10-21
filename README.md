# PatternsProject

## Scenario: Book My Ticket
Passengers will be able to log in, create an account or use our program anonymously, where
their information will be set by the program. Once they pass the identification page, they will
be able to book a ticket. They will be given a set of hardcoded tickets. If the chosen ticket has
a plane with full seats, then a message will appear stating that they cannot purchase that
ticket due to the unavailability. Once selected, they will go through the payment process
where they will enter the number of tickets wanted and their card information. If they have
an account, a premium or a regular passenger, credits can be accumulated and can be
transformed into discounts. Premium passengers will always have a 5% discount on their
total. Their ticket will then be booked in their name. If they want to modify their ticket, they
will have to request an employee to cancel the ticket. Once they are done with the process
of cancelling their initial ticket, the passenger will choose the corrected ticket. To cancel a
ticket, they will still need to request an employee, which will refund them. Their refund
information will be available in the passenger’s refund list. For the Employees, they can only
log in or create an account. They will have the task of booking a ticket for a certain passenger.
However, they handle the process of cancelling and refunding a passenger’s ticket. The
employee will send back a notice mentioning the amount returned, the ticket ID and the
passenger’s ID. Passengers who have used a discount on their total will also have their
credits back, but not their 5% discount if they are a premium member. Each operation will
be registered in case of any error. Once a ticket is booked, the ticket will register the seat
attributed to the passenger, the passenger’s temporary ID, if they are booking anonymously,
and the passenger's name. Lastly, after their purchase, credits will be added to passengers
with an account. They will then be presented to the “Option” page, where they have the
choice to view their credits, view their booked and refunded tickets, and modify, book or
cancel a ticket. Invalid input will not be accepted, and the user will have to re-enter their
credentials until the system recognizes them as being valid.

## Design Paradigm
- The MVC pattern will be used to divide the project into three sections: model, view
and controller. In the view, we will provide the interface using JavaFX.

### View: Graphical User Inter
- On the first page the user can decide if they would like to continue the program on
light or dark mode, the default would be light mode
- The user will have to complete the requested information to go to the next page or
they can cancel the process to leave the page.
- The passenger can view their refund list, accumulated credits and booked tickets.
### Controller: Logical content and links the model with the view
- Calculating the total price
- Validation for the user authentication and other user input
- Interaction between the program and the database to fetch and store information.
- Calculate the discount with a certain amount of credits.
- Fetching the entered input to create objects such as the ticket.
- Creating operations for each action being made and storing them in a database
### Model: Representation of data
- The fields that state all the data of the ticket
- User containing all the base information that both employee and passenger will
inherit
- What data is going to be needed to create an object

## Expected Output

### Creation of an account for a passenger
- The passenger will have to enter their information: email, phone number, full name
and password. A passenger user will be created and added to the passenger list.
When click a button their account will be saved with the date and time created. All
their lists, refund and booked lists, will be set to null and their credit will be set to 0.
### Logging into an account for a passenger
- The passenger will need to enter their email and the password that they’ve created
in the creation process. The credentials will be searched in the passenger's list and
can only move on until a confirmation message appears on the page. A button will
appear, and they can click on it to move to the next page.
- When the passenger enters information that is not found in the passengers list, they
will not be able to move on to the next page and an error message will appear stating
that they will need to re-enter the correct information.
### Skipping the identification page for a passenger
- The anonymous passenger will be able to skip the identification page and will
continue to use the program with the given identification, meaning that their data
will be set by the program to create an anonymous account, though they will still
have a userID.
### Passenger choosing their ticket
- The passenger will be presented with a set of predefined tickets, they will pick a
ticket using a radio button and then they will proceed. If a message pops up saying
that the plane is fully booked, then the passenger will need to choose another ticket.
### Passenger requesting a modification for their ticket.
- In our program, to modify our ticket, the passenger will need to request to cancel
their ticket using the ticket ID that will be cancelled. They will receive a refund on
both their money and credits.
### Passenger requesting to cancel a ticket.
- The passenger cannot directly cancel their ticket. They will need to request the
assistance of an employee. Their refund will be seen in their refunds list and their
credit will be refunded.
### Employee booking a ticket for a passenger
- Just like the passenger, the employee will be able to book a ticket for a passenger
using their provided credentials. The employee will enter the passenger’s payment
information and they will book a ticket in their name.
### Employee cancelling a ticket for a passenger
- The employee will receive a request to cancel a certain ticket from a passenger. The
passenger will provide them with the ticketID that they want to cancel, to help the
employee cancel the ticket successfully. The employee will refund the money and
the credits of the passenger
### Employee “modifying” a ticket for a passenger
- The employee will help the passenger to modify their ticket. The passenger will need
to request an employee to cancel the ticket. The employee will do the same steps as
the cancellation process.
### Employee logging in the system
- The employee will need to enter their email and their password to be able to log in.
in the system. If the entered information does not match a user then they won’t be
able to log in to their account.
### Employee creating an account
- The employee has the benefit of creating their account which will then be stored
among the other employees’ accounts.
