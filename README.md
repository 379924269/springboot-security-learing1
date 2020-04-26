## springboot-security web学习
为什么要用springbootsecurity

###项目的要求：
####1、权限：动态权限
####2、登录验证码
````
调用验证码库实现的kaptcha
````

####3、用户锁定
[锁定可以参考地址](https://www.cnblogs.com/zimug/p/11946727.html)
````
  自定义成功失败处理类，添加到httpsecurity中，实现锁定， 解锁，通过CustomUserDetailsService中验证用户的时候，处理的
````
####4、挤下线
````
  1）：后面的不能登录处理：
    http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).maxSessionsPreventsLogin(true)；
登录的时候，会失败，抛出session认证异常，我们在MyAuthenctiationFailureHandler自定义的登录失败里面处理一下就可以了
    也可以参考：https://blog.csdn.net/qq_35035468/article/details/82188854
  2）：几下前面登录的用户
    http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl("/login/outLine");
````
####5、信道安全：http和https要求
####6、防御回话伪造：
````
登录登录成功后删除一个session然后重新生成一session
````
####7、用户切换
####8、自定义自动访问界面accessDenied.jsp
####10、自定义登录界面
####11、自动登录remmber me
####12、Digest认证