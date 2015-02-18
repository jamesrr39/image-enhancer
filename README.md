# Image Enhancer #

Command line app/library to perform image transformations

## Requires ##

* maven >= 3.0.5
* jdk >= 1.8.0_11

## Build ##

    mvn install

## Run ##
For now, we can use the mvn exec plugin, passing our args with the `-Dexec.args` argument. For example:

    mvn exec:java -Dexec.args="-image=/home/user/Pictures/myImage.jpg -tt=brighten -ta=1.2"

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