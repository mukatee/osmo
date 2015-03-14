# OSMO tester #

Open Source Modelling Objects (OSMO) consists of a number of tools and libraries to help in modeling software behaviour and to help in applying these models. The main tool in this set is currently OSMO Tester, which is a model-based testing tool.

## Easy to model ##

OSMO uses Java as modelling language. With the wide adoption of Java this enables easy adoption for both developers and testers. Java also makes it possible to use all Java libraries, IDE's and other tools, providing a powerful modelling environment.

## Easy to setup ##

OSMO is just a single jar-file and model is a class containing a couple of imports and annotations. The format is described with examples in the documentation page.

## Free ##

OSMO uses the LGPL license in order to enable free use and fruitful collaboration with all interested parties. You can also access OSMO source code on Google Code.

## Modular and Flexible ##

OSMO is designed with an easy to extend architecture. In practice you can write your own test generation algorithm, test end conditions, visualization etc. Only your coding skills and inspiration are the borders.

# OSMO Features #

  * **Modelling language**
    * Extended state machine in Java format.
    * Or can also be seen as a rule system using Java for models.

  * **Online or Offline test generation?**
    * Both. In practice OSMO is intended to provide a free and easy yet powerful starting point for different types of MBT, to experiment with the concepts of MBT and its adoption. It uses a "free programming approach", which makes it especially suitable for online testing, but it also works great for offline test generation.
    * The dynamic nature of OSMO also enables support for non-deterministic online models.
    * OSMO is constantly evolving and due to its open source nature can be easily extended.
    * However, if more advanced algorithms and specific support for specific environments are needed, it is also possible to adopt another commercial MBT tool once you have convinced yourself of the benefits of MBT.

  * **Scripting**
    * OSMO focuses on supporting easy, customized scripting. Scripter code is mainly written as part of model.
    * You can also use the OSMO model as a basis for an automatically generated domain-specific language as a higher-level model abstraction.

