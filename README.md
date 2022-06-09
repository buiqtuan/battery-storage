# Battery Storage
A simple project to demo storing and fetching data in memory using java spring boot

# Description
A java based project with springboot, h2 db.

This provide a simple solution for storing and fetching data synchonously.

It implementes clean architechture code base with three modules:

- persistence: DAO, Entities.
- common: common object.
- batteries-api: controllers, usecases, adapters to handling logics.

# Documents

There are two EP handling create and get logic:

**POST : /battery/create**
Request Body :
{
    "batteries" : [
        {
            "name" : "AAA",
            "postCode" : "400",
            "wattCapacity" : 100
        },
        {
            "name" : "BB",
            "postCode" : "450",
            "wattCapacity" : 200
        }
        ]
}
Response:
{
    "status": "SUCCESS",
    "description": "2 battery(ies) has/ve been created",
    "noOfBatteriesCreated": null
}

**GET : /battery/getByPostCodeRange?from={postCode}&to={postCode}**
Response:
{
    "status": "SUCCESS",
    "description": null,
    "batteries": [
        {
            "name": "BB",
            "postCode": 450,
            "wattCapacity": 200,
            "lowerCaseName": "bb"
        }
    ],
    "noOfBatteries": 1,
    "averageWattCapacity": "200"
}

**To access H2 DB GUI:**

**/h2-console/**
**User:sa
Password:123456**
