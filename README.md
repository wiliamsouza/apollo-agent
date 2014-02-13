Apollo agent
============

Apollo agent is a Java application and uses [gradle](http://www.gradle.org/)
as it's build system.

Dependecies
-----------

 * JDK 1.6.0_45
 * Graddle 1.10

Development machine use versions above.

Get the code
------------

```
git clone https://github.com/wiliamsouza/apollo.git
```

Go to apollo/agent:

```
cd apollo/agent
```

Build source code
-----------------

```
gradle build
```

It will download project dependencies, build the souce code and generate
an excutable Jar inside `build/libs/agent-<verion>.jar`.

Run tests
---------

```
gradle test
```

Check for bugs
--------------

```
gradle check
```

It will run `FindBugs` and generate a report inside `build/reports/findbugs/main.html`
