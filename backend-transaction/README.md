#springboot-mybatis-transaction
===
事务处理@transactional  
注意：  
1、mybatis是依赖的之前的项目，需要在项目中再配置spring datasource(只读当前的application)。
2、不能使用依赖包中的jar包，需要再次引入。
3、需要指定@ComponentScan basePackages扫描dao所在包否则无法注入dao。
   默认只扫描@springbootapplication所在包的子包。  
4、transactional注解加在实现类的方法或者类上，不要加在接口上。