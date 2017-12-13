#springboot assembly 多环境配置

正常打包：  
    如果需要切换环境运行jar包时
    java -jar xxx.jar --spring.profiles.active=test
    通过--spring.profiles.active对应application-{env}.yml