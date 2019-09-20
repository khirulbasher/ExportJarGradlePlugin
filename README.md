# ExportJarGradlePlugin
When we have a Java application and we want to make it executable, then 
we need many configuration and command-line task,
and sometimes if everything looks ok, but there exist's a silly mistake, you
can't run the executable jar file.

The problem can be much more bigger and you have to face following challenges
if,
<ol>
    <li>The application depend's on many other jar file.</li>
    <li>You have to make it's executable file, and link-up with dependencies.</li>
</ol>

To solve those problem, here's the solution for your gradle project.
Here the gradle-plugin named ExportJar, to use this plugin you have to build the
plugin first.

    gradle build
Then use the plugin and make your gradle build file.

FILE: build.gradle
```groovy
import com.lemon.framework.gradleplugin.task.Executable

buildscript {

    repositories {
        mavenLocal()
        mavenCentral()
        flatDir {
            dirs 'libs'
        }
    }

    dependencies {
        classpath "com.lemon.framework:gradle-plugin:$frameworkVersion"
    }
}

jar {
    exclude exportJar.exclude
    manifest {
        attributes('Main-Class':"your.package.YourMainClassName") /*Fully-Qualified Main Class Name*/
        attributes('Class-Path':exportJar.dependencies())
    }
}
```

Then if you run

    gradle exportJar
It will create an executable jar file in /export directory and copy all of it's dependencies on 
a directory named /library and link-up those with executable jar file.

You can change the name of directories, by specifying

```groovy
    exportJar {
        exportLocation="exportedDir"
        dependencyLocation='lib'
    }
```
