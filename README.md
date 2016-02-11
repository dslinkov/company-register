# company-register

**Company register** is a small Spring Boot Application with an even smaller front-end JavaScript client written using AngularJS.  This application's API is able to accept user input, save it in the database as well as return query results.  It has been written with minimal validation and unit/integration tests are absent.

This application supports listing, adding, and updating companies.  It also supports adding and updating employees.

### API Information and Usage ###

In this exercise I decided not to use HATEOAS.  However, adding it would be trivial.

### List All Available Companies ###

**Request**

	curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "http://very-good-company.herokuapp.com/registry/api/company"

**Response**

	{
	  "companies": [
	    {
	      "id": 3,
	      "name": "A Very Good Company",
	      "address": "123 Wymount Terrace",
	      "city": "Provo",
	      "country": "USA",
	      "phone": "",
	      "email": "test@test.com"
	    },
	    {
	      "id": 4,
	      "name": "Another Company",
	      "address": "123 Main St",
	      "city": "Provo",
	      "country": "USA",
	      "phone": "555-555-5555",
	      "email": "test@test.com"
	    },
	    {
	      "id": 5,
	      "name": "Nexus Company",
	      "address": "456 Main St",
	      "city": "Provo",
	      "country": "USA",
	      "phone": "555-555-5555",
	      "email": "test@test.com"
	    },
	    {
	      "id": 6,
	      "name": "A Very Good Construction Company",
	      "address": "123 Main St",
	      "city": "Provo",
	      "country": "USA",
	      "phone": "",
	      "email": "test@test.com"
	    }
	  ]
	}

### Retrieve a specific company ###

**Request**

	curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "http://very-good-company.herokuapp.com/registry/api/company/3"

**Response**

	{
	  "id": 3,
	  "name": "A Very Good Company",
	  "address": "123 Wymount Terrace",
	  "city": "Provo",
	  "country": "USA",
	  "phone": "",
	  "email": "test@test.com"
	}

### Create a new company ###

**Request**

	curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H -d '{
	  "name": "A Very Good Company 2",
	  "address": "123 Wymount Terrace",
	  "city": "Provo",
	  "country": "USA",
	  "phone": "",
	  "email": "test@test.com"
	}' "http://very-good-company.herokuapp.com/registry/api/company"

**Response**

	{
	  "id": 14,
	  "name": "A Very Good Company 2",
	  "address": "123 Wymount Terrace",
	  "city": "Provo",
	  "country": "USA",
	  "phone": "",
	  "email": "test@test.com"
	}


### Update a specific company ###

**Request**

	curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H -d '{
	  "name": "A Very Good Company",
	  "address": "123 Wymount Terrace",
	  "city": "Provo",
	  "country": "USA",
	  "phone": "",
	  "email": "test@test.com"
	}' "http://very-good-company.herokuapp.com/registry/api/company/3"

**Response**

	{
	  "id": 3,
	  "name": "A Very Good Company",
	  "address": "123 Wymount Terrace",
	  "city": "Provo",
	  "country": "USA",
	  "phone": "",
	  "email": "test@test.com"
	}

### Retrieve all employees of a specific company ###

**Request**

	curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "http://very-good-company.herokuapp.com/registry/api/company/3/employee"

**Response**

	{
	  "employees": [
	    {
	      "id": 1,
	      "firstName": "John",
	      "lastName": "Beck",
	      "companyId": 3,
	      "position": "QB"
	    },
	    {
	      "id": 2,
	      "firstName": "Max",
	      "lastName": "Hall",
	      "companyId": 3,
	      "position": "RB"
	    }
	  ]
	}

### Add an employees to a specific company ###

**Request**

	curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H -d '    {
	      "firstName": "Taysom",
	      "lastName": "Hill",
	      "position": "QB/RB"
	    }' "http://very-good-company.herokuapp.com/registry/api/company/14/employee"

**Response**

	{
	  "id": 3,
	  "firstName": "Taysom",
	  "lastName": "Hill",
	  "companyId": 14,
	  "position": "QB/RB"
	}

### Update an employee of a specific company ###

**Request**

	curl -X PUT -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H -d '    {
	      "firstName": "Taysom",
	      "lastName": "Hill",
	      "position": "QB"
	    }' "http://very-good-company.herokuapp.com/registry/api/company/14/employee/3""http://very-good-company.herokuapp.com/registry/api/company/14/employee"

**Response**

	{
	  "id": 3,
	  "firstName": "Taysom",
	  "lastName": "Hill",
	  "companyId": 14,
	  "position": "QB"
	}


### Error Handling ###

This application is using `javax.validation` as well as Hibernate validation.  This application is also using Spring's custom error handling through the use of `@ErrorControllerAdvice`. Thus if an API user submits an invalid input, they may receive the following error message.

	{
	  "timestamp": 1455134842514,
	  "status": 400,
	  "error": "Bad Request",
	  "exception": "org.springframework.web.bind.MethodArgumentNotValidException",
	  "errors": [
	    {
	      "codes": [
	        "Email.companyDto.email",
	        "Email.email",
	        "Email.java.lang.String",
	        "Email"
	      ],
	      "arguments": [
	        {
	          "codes": [
	            "companyDto.email",
	            "email"
	          ],
	          "arguments": null,
	          "defaultMessage": "email",
	          "code": "email"
	        },
	        [],
	        ".*"
	      ],
	      "defaultMessage": "not a well-formed email address",
	      "objectName": "companyDto",
	      "field": "email",
	      "rejectedValue": "tet.com",
	      "bindingFailure": false,
	      "code": "Email"
	    }
	  ],
	  "message": "Validation failed for object='companyDto'. Error count: 1",
	  "path": "/registry/api/company"
	}

A user employing an incorrect HttpMethod would receive the following error:

	{
	  "timestamp": 1455134659866,
	  "status": 405,
	  "error": "Method Not Allowed",
	  "exception": "org.springframework.web.HttpRequestMethodNotSupportedException",
	  "message": "Request method 'POST' not supported",
	  "path": "/registry/api/company/14/employee/3"
	}


