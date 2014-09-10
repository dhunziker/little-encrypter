little-encrypter
================

A little encryption utility used by myself for en- and decrypting passwords. By default, the algorithm used is AES-256 and the key is retrieved from an environment variabled called ENC_KEY. Defaults can be changed and settings are stored in the file config.xml alongside the executable JAR.

Dependencies
------------

JavaFX:
- [afterburner.fx](http://afterburner.adam-bien.com/)
- [ControlsFX](http://fxexperience.com/controlsfx/)

Logging:
- [SLF4J](http://www.slf4j.org/)
- [Log4j 2](http://logging.apache.org/log4j/2.x/)

Testing:
- [Lambda Behave](https://github.com/RichardWarburton/lambda-behave)

Build
-----

Run maven using:

```mvn clean package```

Run
---

Start the application using:

```java -jar little-encrypter-app.jar```

Known Problems
--------------

Problem: Application throws InvalidKeyException: Illegal key size or default parameters

Solution: Download http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html and unzip its content to ```$JAVA_HOME/jre/lib/security/```
