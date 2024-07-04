# Spring Boot Test Task Project
This is a test task Java / Maven / Spring Boot application 

## How to Run 
Open Idea and run or mvn spring-boot:run.

### Rest Service Description
Main rest url is 
```
http://localhost:8080/testtask/operation/{operationType}/{shapeType}?param1=value1:param2=value 
```
Supported Operation Types:
- area
- perimeter

Supported Shape Types and their parameters (all parameters should be int type):
- square (length)
- rectangle (length, width)
- triangle (side1, side2, side3)
- circle (radius)