# EventAndReservationManagment
The purpose of this project is mainly academic. I've written all the code . The work is not finished because it's a group work and I've lost the complete version of it . I only have the part that I did which is the event management and reservation. 
## Installation
1. First of all [download wampserver](http://www.wampserver.com/en/download-wampserver-64bits/) 
2. Copy the relative path to your php.exe file after you have accomplished the installation (example : C:\wamp\bin\php\php5.6.31)
3. Go to Control Panel->System->Advanced System Settings->environment variables
4. Choose Path and then add  the relative path of the php.exe you copied  (example : C:\wamp\bin\php\php5.6.31)
5. Go to CMD and try the command php-v

```bash
php -v
```
6.check on the [usual symfony reuirements](https://symfony.com/doc/current/reference/requirements.html) 
7. I can help with the demos :D
## Usage 
1. Copy the project EventPi and put in this path C:\wamp64\www
2. Open CMD in that project
3.


```bash
$ symfony serve

```

If you don't have the Symfony client installed, run php bin/console server:run. Alternatively, you can configure a [web server](https://symfony.com/doc/current/setup/web_server_configuration.html)  like Nginx or Apache to run the application.

## Contributing 
I have used API in the SyrineMobile Project and external bundles for authentification such as [FOS user](https://symfony.com/doc/master/bundles/FOSUserBundle/index.html)   and [SwiftMailer](https://symfony.com/doc/current/reference/configuration/swiftmailer.html)  to notify the user and confirm the reservation for the event he participated in .
## How would I improve the project?
1. Because in the university we were obliged to work with the [FOS user](https://symfony.com/doc/master/bundles/FOSUserBundle/index.html) for authentification and we had to have the same database for the other applications (java and CodenameOne) we had a conflict. So I wouldn't use that bundle. I would do the authentification without an external Bundle 
2. The FrontEnd part of the project really lacks design  because we were so focused on the backend part of the project that we forgot one of the main qualities about website which design with tools  (HTML and CSS) 
3. The backOffice part of the symfony website is missing the partof promoting marchendise which the purpose of an E-commerce website 

4.I also wanted to handle the coockies part because it has as I believe a big part in increasing sales within E-commerce website but we never got the time to do so. 

 

