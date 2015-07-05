## Transformation without arguments

For a transformation taking no extra arguments, all you have to do is create a class extending [AbstractTransformation](../src/main/java/net/jr39/image_enhancer/graphics/transformations/AbstractTransformation.java), and use [GenericTransformationArgs](../src/main/java/net/jr39/image_enhancer/graphics/transformations/GenericTransformationArgs.java) as the tranformation arguments.

See [SepiaTransformation](../src/main/java/net/jr39/image_enhancer/graphics/transformations/SepiaTransformation.java) for an example.