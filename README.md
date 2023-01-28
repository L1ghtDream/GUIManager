# GUI Manager

![Build](../../actions/workflows/build.yml/badge.svg)
![Version](https://img.shields.io/badge/Version-1.4.1-red.svg)

A library that allows the easy creation of GUIs in Spigot

## Use

### Maven

```xml

<repositories>
    <repository>
        <id>lightdream-repo</id>
        <url>https://repo.lightdream.dev/</url>
    </repository>
    <!-- Other repositories -->
</repositories>
```

```xml

<dependencies>
    <dependency>
        <groupId>dev.lightdream</groupId>
        <artifactId>gui-manager</artifactId>
        <version>1.4.1</version>
    </dependency>
    <!-- Other dependencies -->
</dependencies>
```

### Gradle

```groovy
repositories {
    maven { url "https://repo.lightdream.dev/" }

    // Other repositories
}

dependencies {
    implementation "dev.lightdream:gui-manager:1.4.1"

    // Other dependencies
}
```

## Example

Can be found in the [source code](/src/main/java/dev/lightdream/guiamanger/example)
