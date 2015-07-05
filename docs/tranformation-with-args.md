## Transformation with arguments

If your argument requires arguments, create a class extending [GenericTransformationArgs](../src/main/java/net/jr39/image_enhancer/graphics/transformations/GenericTransformationArgs.java)

Then, [create your transformation class](./simple-transformation.md), replacing GenericTransformationArgs with the class you just created.

See [BrightenTransformation](../src/main/java/net/jr39/image_enhancer/graphics/transformations/BrightenTransformation.java) for an example.