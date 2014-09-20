Apollo agent
============

Apollo agent is a Java application and uses [gradle](http://www.gradle.org/)
as it's build system.

Dependecies
-----------

 * JDK 1.6.0_45
 * Graddle 1.10

Get the code
------------

```
git clone https://github.com/wiliamsouza/apollo-agent.git
cd apollo-agent
```

Build source code
-----------------

Edit `gradle.build` and change `androidHome` to point to android sdk folder.

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

It will run `FindBugs` and generate a report inside
`build/reports/findbugs/main.html`

Troubleshooting
---------------

libstdc++.so.6:

If you get an error like:
```
adb: error while loading shared libraries: libstdc++.so.6: cannot open
shared object file: No such file or directory
```
On ubuntu 14.04 install:

```
sudo apt-get install lib32stdc++6
```

findbug:

```
FindBugs encountered an error.
```

Using java 1.8 as findbug site says "Some classfiles compiled for Java 1.8
give FindBugs problems, the next major release of FindBugs will handle Java
1.8 classfiles".

udev rules:

Apollo agent ships with an intial udev rules
`agent/etc/udev/rules.d/51-android.rules` it sets device group to
`plugdev` and change the mode to `0666`.

To reload rules run:

```
udevadm control --reload-rules
```

To monitor run:

```
udevadm monitor --environment --udev
```
