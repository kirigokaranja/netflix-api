# Netflix Api Project

> Main Categories:

    - Movies
    - Categories
    - Users
  
## Categories
> Add a category:
```
<POST> localhost:8080//categories

{
	"categoryName": "Romance"
}
```
> Get all categories:
```
<GET> localhost:8080//categories
```

> Get one category by category id:
```
<GET> localhost:8080//categories/{categoryId}
```

> Update a category:
```
<PATCH> localhost:8080//categories/{categoryId}

{
	"categoryName": "RomCom"
}
```

## Users
>Add a user: 
```
<POST> localhost:8080//users

{
	"userName": "James Bond",
	"userNationalId": 007
}
```

> Get all users:
```
<GET> localhost:8080//users
```

> Get one user by user id: 
```
<GET> localhost:8080//users/{userId}
```

> Update a user:
```
<PATCH> localhost:8080//users/{userId}

{
	"userName": "James Bond"
}
```

## Movies
>Add a movie:
```
<POST> localhost:8080//users/{userId}/movie

{
	"movieName": "The Morning Show",
	"releaseYear": 2019,
	"category": [
		{"id":1},{"id":6}]
}
```

>View all movies
```
<GET> localhost:8080//movies
```

>view movie by category id and type
```
<GET> localhost:8080/movies/category/{categoryid}?type={movietype}
```

>view unverified suggested movies
```
<GET> localhost:8080//movies/new
```

>admin verifies a movie
```
<POST>localhost:8080//users/{userid}/verify/{movieid}
```

>update a movie
```
<PATCH> localhost:8080/users/{userid}/movie/{movieid}

{
	"movieName": "The Good Morning Show",
	"releaseYear": 2019,
	"category": [
		{"id":2}]
}
```

>delete a movie
```
<DELETE> localhost:8080//users/{userid}/movie/{movieid}
```
