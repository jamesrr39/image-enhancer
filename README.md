Image Enhancer

Command line app/library to perform image transformations

Requires (supported) maven 3.0.5, jdk 1.8.0_11

Build:

    mvn install

Options:

    -image=[filepath]
    Filepath to the image to be transformed. Pass this argument multiple times if required

Example Args:

    -image=/home/user/Pictures/myimage.jpg -tt=brighten -ta=1.2 -ts=rectangle -tsa 200,250 100,400
