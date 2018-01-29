# Elvis
Elvis Operator is an alternative to Java's Optional Operator that works for me.

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/thanosfisherman/maven/elvis/images/download.svg)](https://bintray.com/thanosfisherman/maven/elvis/_latestVersion)

```groovy
implementation 'com.thanosfisherman.elvis:elvis:2.0'
```

# Why Elvis?

Well I am a fan of Elvis (The king!) and Elvis operator `?:` (Also known as  null coalescing operator) which many languages like Groovy or kotlin make use.
Sadly Java doesn’t support Elvis operator. It was actually proposed to be included in Java 7 but the idea was rejected. :( If you want to read the discussion about it for yourself check out [this](http://mail.openjdk.java.net/pipermail/coin-dev/2009-March/000047.html).

So The `?:` binary "Elvis" operator results in the value of the left-hand-side if it is not null, avoiding evaluation of the 
right-hand-side. But If the left-hand-side is null, the right-hand-side is evaluated and this is the result returned instead.

So if you were to write the following in Java:

```java
private String getStreetName() {
    return streetName != null ? streetName : "Unknown Street";
}

```

Using Elvis (the real operator) You could instead write:

```java
private String getStreetName() {
	return streetName ?: "Unknown Street";
}
```

Just for the history, Elvis operator was named like that because `?:` looks like an emoji with a pompadour haircut. And who else could that be if not Elvis 
<img src="https://d3npzzrehyahmo.cloudfront.net/images/eb/7a/eb7aab9824560002b0d2d945e508dd9d_24017b2253e_t.png" alt="Elvis" width="30" height="30"/>

# Usage 

It's worth mentioning that this lib just as `Optional`, can also offer safe navigation in between chain method calls plus a few more features.

Using Elvis (This library) the above snippets could be replaced with:

```java
private String getStreetName() {
	return Elvis.of(streetName).orElse("Unknown Street")
}
```

`Elvis.of()` is equivalent to `Optional.ofNullable()` because come on! This is the most frequently used method and should be short and succinct to use. 

I mean the whole idea of Optional is to deal with nulls. It should have been pretty obvious that `Optional.of()` could also accept null values and not the other way around.

so I did a few renamings and here's the coresponding matches in Elvis vs Optional

`Elvis.of()` == `Optional.ofNullable()`

`Elvis.ofNonNull()` == `Optional.of()`

`Elvis.next()` == `Optional.map()`

## To be continued...
