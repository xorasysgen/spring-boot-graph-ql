# Spring-Boot-GraphQL
GraphQL is a query language for your API, and a server-side runtime for executing queries by using a type system you define for your data. GraphQL isn't tied to any specific database or storage engine and is instead backed by your existing code and data.


A GraphQL service is created by defining types and fields on those types, then providing functions for each field on each type. For example, a GraphQL service that tells us who the logged in user is (me) as well as that user's name might look something like this:


## EndPoint
http://localhost:8080/graph/stock

curl -X POST \
  http://localhost:8080/graph/stock \
  -H 'Content-Type: text/plain' \
  -d '{
	allStocks {
		id
		name
		high
		close
	}
}'




## EndPoint http://localhost:8080/graph/stock

curl -X POST \
  http://localhost:8080/graph/stock \
  -d '{
	stock(id: 7){
	name
	open
	}
}'

# Reference 
1. https://graphql.org/learn/
2. https://graphql.org/code/
