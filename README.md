Discussion
==========

> This repo contains code that got started like so:

1. Interesting code that I encountered (in various blogs/websites)
2. Some problems that got asked at Interviews (I think most of the  interviewers' details have been removed)
3. Code that I would like to ask my candidates.
4. Also some of them started as tutorials/exercises (leetcode, udemy, etc.)

I tried to convert most of them into a valid-junit test that asserts (mostly assertj).
Some tests may look excessive, but they help me with debugging.

To run pure JDK code (no libraries) or its equivalent
```
java -classpath ./target/test-classes org.ravi.udemy.jdk8.dates.LocalTimeExample
```

-----


*Transparency*
-------------
Most of the code, I indicated the source of the code/algorithm/inspiration.



**WARNING**
---------
`Parent-pom` from a sibling repo, pulls down versions that have vulnerabilities. Use caution

_NOTE_
------
1. Uses [lombok](https://www.baeldung.com/lombok-ide) to help generate some boiler plate code.

2. Also, in my IDE rebuild fails, command line is SUCCESS. In those cases, I made spacing changes and the IDE recompiled and resolved everything. Unfortunately, plain rebuild class did not work with IntelliJ-Community. 

3. Stream [tutorial](https://stackify.com/streams-guide-java-8) groups methods 
