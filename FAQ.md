# Frequently Asked Questions #

## OSMO ##

  * What is OSMO?

OSMO is short for Open Source Modelling Objects. The "Objects" can be anything to help in modelling and analysing software behaviour. Once upon time there was an idea it would have more tools such as a spec (model) miner, automated debugging assistant (input minimizer etc.), and so on. Maybe someday.

  * What is OSMO Tester?

It is a model-based testing tool for generating test cases from behavioral models of the system under test.

## OSMO Tester ##

  * What is the philosophy behind OSMO Tester? Why another MBT tool?

There are many commercial, powerful MBT tools. Also, there are some free open source alternatives. However, these are typically expensive, constrained or not actively maintained. We wanted to provide a simple and easy way to adopt the MBT concept and its benefits, while supporting a flexible modelling approach.

  * How should I start with MBT?

Pick a small part of you system with a few test steps. Build a model for this. Generate some tests and see how it goes. Grow this incrementally.

  * How do I model with OSMO?

Read the manual that is included in the .zip file. Check the javadocs for the osmo.tester.annotation package. Everything is basically based on a few annotations.

  * Can I access test generation history in my model?

Yes, use a field type of TestSuite.

  * Why does OSMO Tester only have "simple" algorithms for test generation?

Simply put, because our experience is that it is enough in most cases to generate test cases with basic random algorithms. Biggest issues are usually in integrating with test harnesses, getting the information for the system, expressing it in a model, maintaining these and so on. OSMO Tester instead aims to provide means for easy and powerful modelling of the system. This perhaps leaves more to the modelling expert. ("With more power comes more responsibility")

Additionally, technically speaking the OSMO Tester approach is based on dynamic analysis and execution of the models represented as Java bytecode. We are also allowing use of any third-party libraries, integrated development environments and whatever you find useful.

For some different optimization approaches based on these properties you can check the GreedyOptimizer, MultiGreedy, and OSMOExplorer implementations.

Of course, if someone is interested to provide some more advanced algorithms we will be happy to receive commits. Hybrid approaches in combining static analysis would be one way, but without limiting the expressive power in the language, available used libraries, requiring use of specific customized virtual machines or other similar limitations we do not see this happening. Making these changes would be a huge effort and change what the tool and approach are about at the core.

  * What programming languages does OSMO support?

Only Java currently. In theory there should be no problem using any language that can be compiled to Java bytecode, executed with a JVM, and supports annotations. If you find something like this and successfully apply it, let us know.

  * Can I express coverage requirements in the model?

Yes, check the manual and the use of the Requirements field.

  * Can I assign weights for paths to be covered in the model?

Yes, use the "weight" attribute of the TestStep annotations. The default weight if none is specified is 10. Check the @TestStep and WeightedRandomAlgorithm javadocs for more information.

  * How do I create my own algorithms, input objects, etc.?

Just implement the matching interface and you are done. Check the source code for examples and the relevant interfaces in this case.

  * What is the preferred way to create instances of my model objects?

I recommend using the ModelFactory instance. It works for all types of generator configurations. That is, the greedy optimizers, the explorer and the basic generator. Due to Java syntax it is best to have a class for this for now but hopefully it gets better with JDK8..