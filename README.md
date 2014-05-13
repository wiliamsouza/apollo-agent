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
git clone https://github.com/wiliamsouza/apollo-agent.git
```

Go to apollo/agent:

```
cd apollo-agent
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

Troubleshooting
---------------

libstdc++.so.6:

If you get an error like:
```
adb: error while loading shared libraries: libstdc++.so.6: cannot open shared object file: No such file or directory
```
On ubuntu 14.04 install:

```
sudo apt-get install lib32stdc++6
```

udev rules:

Apollo agent ships with an intial udev rules `agent/etc/udev/rules.d/51-android.rules`
it sets device group to `plugdev` and change the mode to `0666`.

To reload rules run:

```
udevadm control --reload-rules
```

To monitor run:

```
udevadm monitor --environment --udev
```
