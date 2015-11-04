# PROTELIS

Java-hosted execution of Protelis programs.

## Usage

### Prerequisites

Protelis requires Java 8+ in order to run.


### Using Protelis in your project

We suggest using Gradle, Maven or a similar system to deal with dependencies within your project. In this case, you can use Protelis (and all of its dependencies) by importing the following Maven dependency:

```xml
<dependency>
    <groupId>org.protelis</groupId>
    <artifactId>protelis</artifactId>
    <version>VERSION_YOU_WANT_TO_USE</version>
</dependency>
```

or the following Gradle dependency:

```Gradle
compile 'org.protelis:protelis:VERSION_YOU_WANT_TO_USE'
```

Alternatively, you can download the latest jar and place it in your classpath. In this case, be sure to include the dependencies of this project in your classpath.


### Javadoc

Javadoc is available [here][Javadoc]. This documentation is generated by our nightly build system, so it may be up to 24 hours out of date (assuming the build is passing).
The documentation for any specific version of this library is released on Maven Central along with the code and the compiled jar file.


### Downloads

The latest _stable_ artifacts for this project can be downloaded [here][Jars]. This page includes three artifacts:
* A jar file containing the compiled class files
* A jar file containing the source code
* A jar file containing the generated javadoc

Complete build reports can be downloaded [here][reports]


## Build Status
[![Build Status](https://drone.io/github.com/Protelis/Protelis/status.png)](https://drone.io/github.com/Protelis/Protelis/latest)


### Main project
* [Build reports][dashboard]
* [Test summary][test]
* [FindBugs violations][findbugs]
* [PMD violations][pmd]
* [Checkstyle violations][checkstyle]


### Tests
* [FindBugs violations][findbugs-test]
* [PMD violations][pmd-test]
* [Checkstyle violations][checkstyle-test]


## Notes for Developers


### Importing the project
The project has been developed using Eclipse, and can be easily imported in such IDE.


#### Recommended configuration
* Download [the latest Eclipse for Java SE developers][eclipse]. Arch Linux users can use the package extra/eclipse-java, which is rather up-to-date.
* Install the Gradle plug-in
	* In Eclipse, click Help -> Eclipse Marketplace...
	* In the search form enter "gradle", then press Enter
	* One of the retrieved entries should be "Gradle IDE Pack", click Install
	* Follow the instructions, accept the license, wait for Eclipse to download and install the product, accept the installation and restart the IDE.
* Install the FindBugs plug-in
	* In Eclipse, click Help -> Eclipse Marketplace...
	* In the search form enter "findbugs", then press Enter
	* One of the retrieved entries should be "FindBugs Eclipse Plugin", click Install
	* Follow the instructions, accept the license, wait for Eclipse to download and install the product, accept the installation and restart the IDE.
* Install the PMD plug-in
	* **Do not** install eclipse-pmd from the Eclipse Marketplace
	* In Eclipse, click Help -> Install New Software
	* In the text field labelled "Work with:", enter: https://sourceforge.net/projects/pmd/files/pmd-eclipse/update-site-latest/
	* Press Enter
	* PMD for Eclipse 4 will appear in the plugin list. Select it and click Next.
	* Follow the instructions, accept the license, wait for Eclipse to download and install the product, accept the installation and restart the IDE.
* Install the Checkstyle plug-in
	* In Eclipse, click Help -> Eclipse Marketplace...
	* In the search form enter "checkstyle", then press Enter
	* One of the retrieved entries should be "Checkstyle Plug-in" with a written icon whose text is "eclipse-cs", click Install
	* Follow the instructions, accept the license, wait for Eclipse to download and install the product, accept the installation and restart the IDE.


#### Import Procedure
* Open Eclipse
* Click File -> Import -> Git -> Projects from Git -> Next
* Clone URI -> Next
* Paste `git@github.com:Protelis/Protelis.git` as URI -> Next -> Next
* Select the directory where you want to clone the repository. Beware that it **does not** point to the current Eclipse workspace by default
* Next -> Next -> Finish
* The project will appear in your projects list.
* Right click on the project, select Gradle -> Refresh Dependencies. If the option is disabled, do first Gradle -> Enable Dependency Management and then try again.
* Checkstyle, PMD and FindBugs should be pre-configured. **Do not** run Gradle -> Refresh all, because that would delete the automatic invocation of the code checkers.
	* If you have not noticed the warning above and you have deleted our Eclipse configuration, assuming that you have not committed your changes, you can recover it by using ```git checkout HEAD .settings/edu.umd.cs.findbugs.core.prefs .settings/org.eclipse.jdt.core.prefs .pmd .checkstyle .project .classpath```
	* If you also have committed the changes to those files, run the same command substituting ``HEAD`` with the latest commit from us.

### Developing the project
Contributions to this project are welcome.  To ensure that your contribution is incorporated quickly, we request that you follow the following coding best practices:

1. Commit often. Do not send pull requests with a single giant commit adding or changing massive amounts at once, as this is likely to have conflicts with otehr work. Split your work into multiple commits and request a merge to the main line often.
2. Do not introduce low quality code. All new code should comply with checker rules (which are quite strict) and should not introduce other warnings.  Resolution of existing warnings (if any are currently present) is very welcome.


#### Building the project
While developing, you can rely on Eclipse to build the project, it will generally do a very good job.
If you want to generate the artifacts, you can rely on Gradle. Just point a terminal on the project's root and issue

```bash
./gradlew
```

This will trigger the creation of the artifacts the executions of the tests, the generation of the documentation and of the project reports.


#### Release numbers explained
We release often. We are not scared of high version numbers, as they are just numbers in the end.
We use a three level numbering, following the model of [Semantic Versioning][SemVer]:

* **Update of the minor number**: there are some small changes, and no backwards compatibility is broken. More particularly, it should be the case that any project that depends on this one should have no problem compiling or running. Raise the minor version if there is just a bug fix or a code improvement, such that no interface, constructor, or non-private member of a class is modified either in syntax or in semantics. Also, no new classes should be provided.
	* Example: switch from 1.2.3 to 1.2.4 after improving how an error condition is reported 
* **Update of the middle number**: there are changes that should not break any backwards compatibility, but the possibility exists. Raise the middle version number if there is a remote probability that projects that depend upon this one may have problems compiling if they update. For instance, if you have added a new class, since a depending project may have already defined it, that is enough to trigger a mid-number change. Also updating the version ranges of a dependency, or adding a new dependency, should cause the mid-number to raise. As for minor numbers, no changes to interfaces, constructors or non-private member of classes are allowed, except for adding *new* methods. If mid-number is update, minor number should be reset to 0.
	* Example: switch from 1.2.3 to 1.3.0 after adding new methods for computing with Tuples.
* **Update of the major number**: *non-backwards-compatible change*. If a change in interfaces, constructors, or public member of some class has happened, a new major number should be issued. This is also the case if the semantics of some method has changed. In general, if there is a high probability that projects depending upon this one may experience compile-time or run-time issues if they switch to the new version, then a new major number should be adopted. If the major version number is upgraded, the mid and minor numbers should be reset to 0.
	* Example: switch from 1.2.3 to 2.0.0 after changing the interface for parsing programs.


[Javadoc]: http://137.204.107.70/protelis-build/protelis/build/docs/javadoc/
[Jars]: http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22protelis%22
[reports]: https://drone.io/github.com/Protelis/Protelis/files/build/reports/reports.tar
[dashboard]: http://137.204.107.70/protelis-build/protelis/build/reports/buildDashboard/
[test]: http://137.204.107.70/protelis-build/protelis/build/reports/tests/
[checkstyle]: http://137.204.107.70/protelis-build/protelis/build/reports/checkstyle/main.html
[checkstyle-test]: http://137.204.107.70/protelis-build/protelis/build/reports/checkstyle/test.html
[findbugs]: http://137.204.107.70/protelis-build/protelis/build/reports/findbugs/main.html
[findbugs-test]: http://137.204.107.70/protelis-build/protelis/build/reports/findbugs/test.html
[pmd]: http://137.204.107.70/protelis-build/protelis/build/reports/pmd/main.html
[pmd-test]: http://137.204.107.70/protelis-build/protelis/build/reports/pmd/test.html
[eclipse]: https://eclipse.org/downloads/
[SemVer]: http://semver.org/spec/v2.0.0.html