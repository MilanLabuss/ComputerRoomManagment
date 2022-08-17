The Cork College of Commerce has many computer rooms. Each computer room has several computers. From time to time computers are unusable because a component fails, software needs to be installed or reinstalled etc. c.
As a starting point, you have been given the details held by the technician on the computer rooms. This includes:
- The computer room names and the number of computers in each room.
- The serial number, name (room number and computer number), model and manufacturer of
each computer.
You are required to design, code and test a JavaFX application that will act as a digital fault logbook, subject to the following considerations.

THE TEACHER
Teachers are tasked with filling in the fault book when they come across a fault. They are to be able to login to the system using their user name and password. The user names and passwords are held in an underlying database table.
The user can change their password on the system. They must first provide their old password, enter their new password and confirm their new password. If the old password is correct and the new password matches the confirmation password, the user's password is changed.

THE LOGBOOK
Any faults found are to be logged with the following detail: The date the fault was logged, the room number, and the number of the computer, the fault with the computer and the name of the user who logged the fault.
- All fields are mandatory.
- The date should be taken from the system clock.
- The room number entered must be a valid computer room number.
- The computer number must be a valid computer number.
- The fault is a brief description of the fault with the computer.
- The name of the person logging the fault is the user who logged in to log the fault.
All users must be able to:
- View all entries. If a fault has been logged already, it should not be logged again.
- View all entries that they have logged and that are not resolved.
- View all entries that they have logged and that are resolved (see next section).

THE TECHNICIAN
Repairs
are carried out by the technician who must be able to:
- View all faults logged.
- View faults by room.
- View individual faults (e.g. Computer 7 in room 116). In this case, the serial number,
manufacturer and model are displayed.
- Indicate the status of the repair if the computer is not repaired e.g. “RAM ordered.”
- Indicate that a repair has been made. In this case, the fault details are transferred from the
logbook table to the resolved table.
- View the number of faults and the detail on all faults that have been inactive for five days or
more.
- At the end of every academic year, the principal considers replacing a computer room for
next year's students and wants be told which room has had the most faults per computer. The technician makes this available to management.

THE ADMINISTRATOR
The administrator must be able to:
- Modify any details in the Computer Room and Computer tables. They should be able to remove a room, add a room, modify room details, remove a computer, add a computer and modify computer details.
- Add a user or remove a user. BRIEF
You are required to design, implement (using JavaFX) and test a program that will interface with a SQL database to fulfill the requirements detailed above. The application is to:
- Be developed using prototyping and rapid application development, with each part of your development documented.
- Be error free, robust and reliable.
- Adhere to best practice in terms of the user interface and underlying coding.
- Be prepared for deployment.
