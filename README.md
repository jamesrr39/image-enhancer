Image Enhancer

Command line app/library to perform image transformations

Requires (supported) maven 3.0.5, jdk 1.8.0_11

Build:

    mvn install

Options:

    -image=[filepath]
    Filepath to the image to be transformed. Pass this argument multiple times if required

Example Args:

Brighten a rectangular area

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=1.2 -ts=rectangle -tsa 200,250 100,400

Darken the whole image

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=0.5

Contrast the whole image

    -image=/home/user/Pictures/myimage.jpg -tt=contrast -ta=1.5

Grayscale whole image

    -image=/home/user/Pictures/myimage.jpg -tt=grayscale