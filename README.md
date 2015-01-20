SBT plugin for creating [RiffRaff](https://github.com/guardian/deploy) deployable artifacts
===========================================================================================

Add

```
addSbtPlugin("com.gu" % "riffraff-artifact" % "0.4")
```

to your `project/plugins.sbt` and

```
riffRaffPackageType := (packageBin in config("compile")).value

lazy val root = (project in file(".")).enablePlugins(RiffRaffArtifact)
```

to your `build.sbt`.

Run `riffRaffArtifact` to build an artifact deployable by
[RiffRaff](https://github.com/guardian/deploy).

If you follow the above steps the `riffraffArtifact` command will produce an archive called `artifacts.zip` in the 
target directory. This will contain a `deploy.json` from the `resourceDirectory` (`conf` for a Play app) in the root 
and a packages directory with a sub-directory, named using the project name, containing a `zip` archive and the file
with the extension `.conf` and the name of the project found in the projects base directory. e.g. for a project with the 
name `example`:

```
deploy.json
packages/example/example.zip
packages/example/example.conf
```

Package Type Customisation
-------------

If you want to bundle your app as a `tgz` using
[sbt-native-packager](https://github.com/sbt/sbt-native-packager)
add:

```
import com.typesafe.sbt.packager.Keys._

riffRaffPackageType := (packageZipTarball in config("universal")).value

```

to your build.sbt.

If you prefer bundling your app as an uber-jar, instead include the
[sbt-assembly](https://github.com/sbt/sbt-assembly) plugin and add

```
import sbtassembly.Plugin.AssemblyKeys._

riffRaffPackageType := assembly.value
```

