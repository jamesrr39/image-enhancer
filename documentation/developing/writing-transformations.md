# Writing transformations #

This app has been created with the idea of being able to add transformations easily, allowing the transformation developer to concentrate on writing the transformation code itself.

There are 5 files you will need to touch; 3 of them need modifications and 2 new files

Modifications:

* TransformationTypes - add your transformation into the TransformationTypes enum
* AppArgs
  * setTransformationType add the string to identify the transformation usage in the CLI (command line interface) app
  * getTransformationArgs - add to the switch statement a case that returns YourTransformationArgs
  * getTransformationType - add to the switch statement a case that returns YourTransformation
* README.md - please be nice and leave an example set of args for your transformation to be run in the readme!

New Files:

* YourTransformation extends AbstractTransformation<YourTransformationArgs>
* YourTransformationArgs extends AbstractTransformationArgs
