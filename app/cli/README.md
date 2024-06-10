# Cli

## Binary

You can build the project in a binary in order to have a "real cli".

You need to install graal VM and apply ./gradlew build nativeImage

Then you will with the binary in the build folder

./give-me-the-odds pathToFalcon pathToEmpireFile

## Java

Otherwise you can run the cli using build JAR

java -jar cli.jar pathToFalcon pathToEmpireFile

You can also run the application using Intellij launcher
