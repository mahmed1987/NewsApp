# News Application Demo
###### A demo showcasing the industry standard approach (Multi modules , MVVM , CLEAN) for developing in android

This application takes the approach of extreme code isolation by distributing various components of the application in modules. The following modules together make the application

## Common

This module would not have any dependencies. However THIS WILL BE a dependency for the other modules in the application. As the name suggests this module will house common functionality that is required throughout the application. Most importantly this will host
###### Purposes
1. Base classes of various android components ( Fragments, Activites)
2. Extension methods that would be used in the application
3. Styles 


## App 

This module will be the one which would be the entry point of our application. This module will be responsible for creating our DI container and also host the main build gradle file as well as the android manifest. This module will be dependent on all the other modules in the application. This is necessary for it to create the DI Container graph. 
Other than creating the dependency graph , this module has no other purpose than to launch the landing page of the application (which would reside in another module)
###### Purposes
1. Create the DI container
2. Launch the landing page of the application

## Business
This module will hold all the non UI code of our application. In clean architecture way of speaking , this would hold the usecases for our application.
This module will not be aware of the UI which would use the business it exposes. This module will have dependencies over the repository module of our application
###### Purposes
1. Provide usecases for our application (used by the UI )

## Repository

This module will hold the repositories for all the data entities we are to work with in our application. 
###### Purpose
1. Provide data to the business module

## Dtos
This module is the glue which binds the UI with the data it needs to display. The UI is never aware of anything other than the business module. However the challenge then would be to send a piece of data upstream from repositories to business to UI. Dtos is a module on which the UI , business and repositories are together dependent , without being aware of each other directly. This gives us isolation of the UI with the rest of the system
###### Purpose
1. Provide the DTOs to the UI (which would be translated version of the actual data coming from Room/Web)

## Network
This module exists at the lowest level of the application module graph. By that I mean , this module has no dependencies and only the **Repository** module is the only module which is aware of this module
###### Purpose
1. Provide the network related functionality to the application (Retrofit lives in this module)

## News (UI module)
This would be the module which would host the news feature of the application. This feature includes two fragments and takes its data from the business module.
###### Purpose
1. Provide a list of news.
2. Provides detail of a single news.






