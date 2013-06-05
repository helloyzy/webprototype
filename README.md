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

### transaction support

(1) use sessionFactory.getCurrentSession() to make @Transactional label work (sessionFactory.openSession() will open a new 
session and thus does not belong to current transaction scope)

## Integration with Hibernate 4

Refer to this article: http://hi.baidu.com/austincao/item/fc9907da3d854e44fa576861

(1) Session factory bean : in spring-orm, from org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean to 
org.springframework.orm.hibernate4.LocalSessionFactoryBean

(2) Cache support: second-level cache configuration modified, from hibernate.cache.provider_class to hibernate.cache.region.factory_class

(3) Does not support HibernateTemplate, need to get session from SessionFactory

(4) Modify hibernate.current_session_context_class from thread to org.springframework.orm.hibernate4.SpringSessionContext
