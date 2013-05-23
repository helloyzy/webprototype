## webprototype

For quickly setting up a web application making use of Spring and Hibernate.

## Integration with Spring

(1) version should be matched (E.g. spring-mvc 3.2.2 should match spring-core 3.2.2).

(2) define configuration for spring in the web.xml (global as well as servlet specific).

### request mapping

(1) the urlmapping defined in the controller(annotated way) is relative to the url-pattern defined in the web.xml 
for the servlet. E.g. if you define url-pattern as /controller/* and the urlmapping as /simple/helloworld, 
then you should access [host]/controller/simple/helloworld.

### view resolvers

(1) can have multiple view resolvers and its priority can be defined through "order"
