This project demonstrates https://bugs.eclipse.org/bugs/show_bug.cgi?id=535819

We use ebean and the APT-tools https://github.com/ebean-orm/ebean-querybean to generate query beans.
I've bundled all neccessary libraries in this repo.

*How to reproduce*

if you run `compile-3.13.0.jar` it will compile fine.
if you run `compile-3.16.0.jar` it will fail:

```
java.lang.NullPointerException
        at org.eclipse.jdt.internal.compiler.lookup.PackageBinding.addType(PackageBinding.java:106)
        at org.eclipse.jdt.internal.compiler.lookup.ClassScope.buildType(ClassScope.java:432)
        at org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope.buildTypeBindings(CompilationUnitScope.java:188)
        at org.eclipse.jdt.internal.compiler.lookup.LookupEnvironment.buildTypeBindings(LookupEnvironment.java:463)
        at org.eclipse.jdt.internal.compiler.Compiler.internalBeginToCompile(Compiler.java:845)
        at org.eclipse.jdt.internal.compiler.Compiler.processAnnotations(Compiler.java:961)
        at org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:450)
        at org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:426)
        at org.eclipse.jdt.internal.compiler.batch.Main.performCompilation(Main.java:4704)
        at org.eclipse.jdt.internal.compiler.batch.Main.compile(Main.java:1768)
        at org.eclipse.jdt.internal.compiler.batch.Main.main(Main.java:1491)
```

As far as I figured out, the bug is very fragile. If I slightly modify the finder class, the NPE will vanish.

```java
public class Finder {

	//	add that block and it will compile
	//	@Nonnull 
	//	public static QEntity1 getQuery1() {
	//		return new QEntity1();
	//	}

	@Nonnull // remove that annotation, and it will compile
	public static QEntity2 getQuery2() {
		return new QEntity2();
	}
}
```


this will not compile:
```java
package de.foconis.ecjbug.domain;

import de.foconis.ecjbug.domain.query.*; // note that import!

public class Finder {

}
```