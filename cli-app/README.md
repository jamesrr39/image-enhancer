# Image Enhancer CLI App

## Build

See [parent project readme](../README.md)


## Run ##
You can choose between using the mvn exec plugin, passing our args with the `-Dexec.args` argument, for example:

    mvn exec:java -Dexec.args="-image=/home/user/Pictures/myImage.jpg -tt=brighten -ta=1.2"

Or, you can package the app using `mvn package` and use it as a standalone app. You need to pass the main class of `net.jr39.image_enhancer.ImageEnhancer`
Example usage:

    java -cp cli-app/target/cli-app-develop-SNAPSHOT.jar net.jr39.image_enhancer.ImageEnhancer -image=/home/user/Pictures/myImage.jpg -tt=brighten -ta=1.2

The best combination to use is to use `mvn exec:java` when developing locally, to take advantage of being able to compile modules separately (and more quickly than the whole thing) after making changes, and `mvn package` when deploying to an environment.

## Options ##

See `AppArgs.java`

## Example Args ##

Brighten a rectangular area

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=1.2 -ts=rectangle -tsa 200,250 100,400

Darken the whole image

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=0.5

Contrast the whole image

    -image=/home/user/Pictures/myimage.jpg -tt=contrast -ta=1.5

Grayscale whole image

    -image=/home/user/Pictures/myimage.jpg -tt=grayscale

Darken a circle with a centre point of [x,y] [200,250] and a radius of 100px

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=0.5 -ts=circle -tsa 200,250 100

Darken a circle with a centre point of [x,y] [200,250] and a radius of 100px gradually (more effect in the centre than the outside)

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=0.5 -ts=circle -tsa 200,250 100 -gradual

