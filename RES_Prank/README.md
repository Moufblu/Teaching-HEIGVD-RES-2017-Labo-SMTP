Description of Project

We want to send mail forged. We consider to have those files :
-a list of mails file
-a .properties that contains the port and the adress of the server where we want to connect
-a list of files with the name format "message1.txt" that contains the subject and the mail prank we wish to send

Our program then connects to the server specified in the .properties.
Then we build a prank mail to send at the group of victims. we choose a random victim to be the sender and we send the prank to the others.
We let the choice to the program user to repeat the operation with 'y' or 'n' console response.

then we disconnect.

How to Configure

Put the list of mails, the .properties and the list of file "pranks" at the root of the program and run it.
The pranks files have to be at the format : message1.txt where the number is between 1-3.

Implementation choices

We have decided to let the choice to the user to repeat the operation to send new pranks.
We have only an array of 3 pranks message1.txt message2.txt and message3.txt
We have the file of victims that contains the mails.

How to Install MockMock

Go to this website: https://github.com/tweakers-dev/MockMock
There is a "here" link to click in the section Installation / setup
It will download a .jar file
Then to run the mock server open a console where you installed the .jar and write the commande :
java -jar MockMock.jar -p 25000 -h 8282
Defaults ports are 8282 for web interface and 25 for smtp

the -h and -p parameters are optionals and specifie the ports smtp and html we want to "listen"

Now you can open a browser and write : localhost:numberPort where numberPort is the html port choosen previously.