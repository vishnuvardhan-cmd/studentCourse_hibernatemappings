# studentCourse_hibernatemappings

StudentController
Post Mappings
Request 
{
    "name":"sushma",
    "courses":["mba","mca","btech"]
}

Response
{
    "id": 153,
    "name": "sushma",
    "courses": [
        "btech",
        "mca",
        "mba"
    ]
}

get all

Request
localhost:8085/students

Response
[
    {
        "id": 102,
        "name": "vishnu",
        "courses": [
            "mtech",
            "mca",
            "mba"
        ]
    },
    {
        "id": 152,
        "name": "krishna",
        "courses": [
            "mca",
            "mba"
        ]
    },
    {
        "id": 153,
        "name": "sushma",
        "courses": [
            "btech",
            "mca",
            "mba"
        ]
    }
]

update request
Request
localhost:8085/student/102
{
    "name":"vishnu",
    "courses":["mba","mca","btech"]
}

Response
{
    "id": 102,
    "name": "vishnu",
    "courses": [
        "btech",
        "mtech",
        "mca",
        "mba"
    ]
}

request
localhost:8085/student/153

response
Student with id: 153 deleted successfully!


course controller

post 
request
{
    "name":"bpharm",
    "students":["sushma"]
}
response
{
    "id": 252,
    "name": "bpharm",
    "students": [
        "sushma"
    ]
}

Get
request
localhost:8085/courses

response

[
    {
        "id": 1,
        "name": "mca",
        "students": [
            "krishna",
            "vishnu"
        ]
    },
    {
        "id": 2,
        "name": "mtech",
        "students": [
            "vishnu"
        ]
    },
    {
        "id": 3,
        "name": "mba",
        "students": [
            "krishna",
            "vishnu"
        ]
    },
    {
        "id": 52,
        "name": "btech",
        "students": [
            "vishnu"
        ]
    },
    {
        "id": 152,
        "name": "mpharm",
        "students": [
            "vishnu"
        ]
    },
    {
        "id": 202,
        "name": "bpharm",
        "students": [
            "ravi",
            "vishnu"
        ]
    },
    {
        "id": 252,
        "name": "bpharm",
        "students": [
            "sushma"
        ]
    }
]

Get mapping
findByid
request

localhost:8085/courses/3

response

{
    "id": 3,
    "name": "mba",
    "students": [
        "vishnu",
        "krishna"
    ]
}

delete mapping byid

request 

localhost:8085/courses/252

response
successfully deleted the id : 252

put mapping
request
 {
        "name": "b-pharm",
        "students": [
            "ravi",
            "vishnu"
        ]
    }

response
{
    "id": 202,
    "name": "b-pharm",
    "students": [
        "vishnu",
        "ravi"
    ]
}
