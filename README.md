# FavouriteRecipeProject
Project Assessment:
Create a standalone java application which allows users to manage their favourite recipes. It should allow adding, updating, removing and fetching recipes. Additionally users should be able to filter available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.
For example, the API should be able to handle the following search requests:
-  All vegetarian recipes
-  Recipes that can serve 4 persons and have “potatoes” as an ingredient
-  Recipes without “salmon” as an ingredient that has “oven” in the instructions.


# Instructions

# How to run
###Build Spring Boot Project with Maven
- clone the project
- cd FavoriteRecipeProject
- mvn install
- java -jar target/FairytaleFlavors-0.0.1-SNAPSHOT.jar

#Run Spring Boot app using any tool like Intellije/ Visual Studio/ Eclipse
- Alternatively, any IDE could be used to run the FavoriteRecipeApplication.

#SampleRequests(Postman recommended):
##CreateRecipe:
###SampleRequest:
-http://localhost:8080/api/v1/recipes/createRecipe
-RequestBody:
{
"recipeName":"Chicken-Tikka",
"isVegetarian":"false",
"servingSize":"4",
"ingredients":["oil", "onion","chicken","salt"],
"cookingInstructions":["add oil", "add onion","add chicken", "add salt"]
}

{
"recipeName":"Paneer-Butter-Masala",
"isVegetarian":"true",
"servingSize":"2",
"ingredients":["oil", "onion","paneer","butter"],
"cookingInstructions":["add oil", "add onion","add paneer", "add butter"]
}

###SampleResponse:
{
    "id": 2,
    "recipeName": "Paneer-Butter-Masala",
    "createDate": "28-10-2022, 14:59",
    "isVegetarian": true,
    "servingSize": 2,
    "ingredients": [
        "oil",
        "onion",
        "paneer",
        "butter"
    ],
    "cookingInstructions": [
        "add oil",
        "add onion",
        "add paneer",
        "add butter"
    ]
}

###StatusCode:
- 201 created

##GetRecipeById:
###SampleRequest:
-http://localhost:8080/api/v1/recipes/getRecipeById?id=2

###SampleResponse(if data is present inside the database wih given id):
{{
    "id": 2,
    "recipeName": "Paneer-Butter-Masala",
    "createDate": "28-10-2022, 14:59",
    "isVegetarian": true,
    "servingSize": 2,
    "ingredients": [
        "oil",
        "onion",
        "paneer",
        "butter"
    ],
    "cookingInstructions": [
        "add oil",
        "add onion",
        "add paneer",
        "add butter"
    ]
}

###status code:
- 200 Ok

###SampleResponse(if data is not present inside the database wih given id):
{
    "statusCode": 404,
    "timestamp": "2021-09-24T12:27:22.5875773",
    "message": "Recipe Not Found with Id: 3"
}

##GetRecipeByName:
###SampleRequest:
-http://localhost:8080/api/v1/recipes/getRecipeByName?recipeName=paneer-butter-masala

###SampleResponse:
{
    "id": 2,
    "recipeName": "Paneer-Butter-Masala",
    "createDate": "28-10-2022, 14:59",
    "isVegetarian": true,
    "servingSize": 2,
    "ingredients": [
        "oil",
        "onion",
        "paneer",
        "butter"
    ],
    "cookingInstructions": [
        "add oil",
        "add onion",
        "add paneer",
        "add butter"
    ]
}

##GetRecipeList:
###SampleRequest:
- http://localhost:8080/api/v1/recipes/getRecipeList

###SampleResponse:
[
    {
        "id": 1,
        "recipeName": "Chicken-Tikka",
        "createDate": "28-10-2022, 14:58",
        "isVegetarian": false,
        "servingSize": 4,
        "ingredients": [
            "oil",
            "onion",
            "chicken",
            "salt"
        ],
        "cookingInstructions": [
            "add oil",
            "add onion",
            "add chicken",
            "add salt"
        ]
    },
    {
        "id": 2,
        "recipeName": "Paneer-Butter-Masala",
        "createDate": "28-10-2022, 14:59",
        "isVegetarian": true,
        "servingSize": 2,
        "ingredients": [
            "oil",
            "onion",
            "paneer",
            "butter"
        ],
        "cookingInstructions": [
            "add oil",
            "add onion",
            "add paneer",
            "add butter"
        ]
    }
]

##SearchRecipe
###SampleRequest:
- http://localhost:8080/api/v1/recipes/searchRecipe
####RequestBody1:
{
    "isIngredientsIncluded":true,
    "ingredient":"paneer",
    "cookingInstructions":"add chicken"
}

####Response:
{
    "statusCode": 404,
    "timestamp": "2022-10-28T16:11:04.033978",
    "message": "Recipe for particular instructions and ingredients not found"
}

####RequestBody2:
{
    "isIngredientsIncluded":true,
    "ingredient":"paneer",
    "cookingInstructions":"add paneer"
}

####ResponseBody2:
[
    [
        {
            "id": 1,
            "recipeName": "Paneer-Butter-Masala",
            "createDate": "28-10-2022, 16:16",
            "isVegetarian": true,
            "servingSize": 2,
            "ingredients": [
                "oil",
                "onion",
                "paneer",
                "butter"
            ],
            "cookingInstructions": [
                "add oil",
                "add onion",
                "add paneer",
                "add butter"
            ]
        }
    ]
]

##DeleteRecipeById:
###SampleRequest:
- http://localhost:8080/api/v1/recipes/deleteRecipeById?id=2

###SampleResponse:
- Deleted Id: 2



# Swagger implementation
http://localhost:8080/swagger-ui.html


