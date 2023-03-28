# MealsApp :spaghetti:

### Description:

  As part of the 3rd assignment of the course "Software Design" at Hellenic Open Univercity, 
  students were asked to develop an application called Meals App.

  The application draws, processes and presents data from the website [TheMealDB](https://themealdb.com/api.php). 
The Meals App provides these web services via REST (Representational State Transfer) in order to be used by any interested party.
The data is returned in JSON (JavaScript Object Notation) format.
Some of the information that can be retrieved are:
```
- Searching for a meal by name.
- Search a random meal by name.
- Search for meals by meal category.
- Filtering by meal category.
- Search by meal type, search by meal type, search by ingredients, etc.
```

  When the data has been extracted the application can also store/edit the data in a local database.


### Project Objective:

  The objective of the project as requested by the assignment is to extract the data from the API and present it to the user in a simple and stable way.
Specifically, themealdb.com is an online database of recipes and meals from all over from all over the world. It features over 280 recipes, including traditional and contemporary dishes from various cuisines. With its user-friendly interface, the site is easy to navigate, allowing users to find recipes for any occasion.

  One of the most useful features of [TheMealDB](https://themealdb.com/api.php)  is its search function. Itcan search for recipes by cuisine, meal type, ingredient. The site also provides step-by-step instructions for each recipe.
  The main goal of the project is for the Meals App to pull data from themealdb.comand present it. The user can very easily search for a meal in various ways, pull the data and then edit or save it with the help of a simple and easy to use graphical user interface (GUI).

  At the same time it can export tables with statistical data and print them out in a .pdf file.
The design of the application was made with the idea that new functions can be added easily and quickly
without being burdened with complex methods or calculations.


### Admissions:

The flexibility of the code gives the general assumption that the Meals App can be extended with simple backend/frontend additions.
The user can view the entity of a meal at any time in the local database and can pump or remove it if desired. The logic of the search in many ways is that it first detects if a meal exists in the API and then checks if it exists in the database and the user can accordingly select the data to be displayed.
A table was used for the whole application operation, taking advantage of JPA's capabilities.
On each search/visit/view of a meal either from the API or the db, a simple record is created with the basic data of the meal and a +1 is added to the corresponding field of that record.
```
The user has the option to delete all data or keep the meal stats.
```
We also considered that for random search to follow the above mentioned function as well.

As for the application, it was implemented in Java and specifically JDK 17.0.2 was used. While Apache Derby (version 10.14.2.0) was used for the database. The GUI was created with Swing.

For the collaboration of the team members in writing the code, github was used and for IDE, Netbeans IDE 16 was used.

Several tools were used for the Scrum processes, such as:
```
- Planningpoker.com (for effort calculation and prioritization)
- Trello (for backlog management)
- Toggl (for recording the time each member spends on User Stories)
- Midjourney (for visuals)
```
Figma was also used for designing the GUI mockups and Visual Paradigm for and ProjectLibre for drawing Gantt charts
File sharing was done with Dropbox, and Google Docs and Online MS were also used Office, for shared authoring of required documents.
In order to compile the application and run it, the necessary library import must be done.
Relevant instructions can be found in the document ["Οδηγίες Εγκατάστασης & Χρήσης.docx"](Instructions/Οδηγίες_Εγκατάστασης_&_Χρήσης.docx).


### Calculating the effort required per requirement:
  Soon we had to consider the factors that would help or hinder us for the project, according to the Project Management Body of Knowledge (PMBOK) we identified the following questions.
```
● How familiar are we with Java?
● What knowledge do we need to reach the desired level?
● Is there any relevant previous experience?
● Can we work as a team or can we work as a team?
```


### Indicative:

Snapshots from the implementation of project management, you can find more details at folder ["Instructions"](Instructions).

ProjectLibre and Gantt charts:

<img width="845" alt="Screenshot 2023-03-28 at 09 56 14" src="https://user-images.githubusercontent.com/32077784/228154684-60962020-c460-45b8-b710-f1b551583b78.png">

Burndown Chart:

<img width="838" alt="Screenshot 2023-03-28 at 09 55 27" src="https://user-images.githubusercontent.com/32077784/228155587-fe0fe299-0669-4f7b-b3a9-3ee72879b79c.png">

### Team: :handshake:
|Student|Role|
|:---|:---|
|`Alex Marios Remountakis`|Scrum Master and Back-End Developer, responsible for the development of JPA db, API and Scrum.|
|`Nikolaos Stefanatos`|Front-End Developer, responsible for development and maintenance of the interface of the application.|
|`Michael Nikoltsios`|Full Stack Developer and Debugger helping and examining the code for errors that may occur.|
