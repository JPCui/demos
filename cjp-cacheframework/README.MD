# cjp-cacheframework

利用AOP代理编写的缓存框架，主要用于理解AOP

###
> cn.cjp.mycache
>> advice
>
    定义切面方法。

>> bean
>
    定义分页类等。

>> cache
>
    定义缓存访问接口，可根据业务需求修改存储方式。
    目前是以Map模拟缓存库，可以替换为redis缓存访问接口。

>> dao
>
    数据访问接口，可根据业务需求进行修改。
    目前是以Map模拟数据库，可以替换为MySQL数据访问接口。

>> domain
>
    领域模型，可根据业务需求进行修改。





