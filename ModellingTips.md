# Introduction #

Some notes collected on our experiences..

## Modelling Tips ##

  * Use the OSMO provided data types when possible

These can be tracked by the test generator to give you information about coverage of data values over different paths. The main ones are ValueSet, ValueRange, Text.

  * Create your OSMO data types at class level.

Meaning declare them as class-level variables and initialize while declaring or in the constructor. The generator will not catch them if they are created later such as inside methods.

  * Use your OSMO data types only after generation is started.

Otherwise the generator has not initialized them and the randomization they use will not work. You may use them in BeforeSuite or BeforeTest methods to set up stuff for test generation.

However, if you do wish to use them inside methods before generation has started, you have to initialize them with setSeed() to avoid NullPointers..

  * Use deterministic data types if not OSMO provided ones

If you use Java objects such as HashSet or HashMap, you may get non-deterministic test generation. Why? Because you might ask for items such as keySet() or values(), which can have a non-deterministic ordering. Thus even with the same randomization results, you might get different results. That is, get(1) can give a different results at different times. Objects such as LinkedHashSet and LinkedHashMap fix this for you.

  * Avoid static variables

If you have static variables they are shared data over all model instances. Since many configurations of the generator will create numerous new instances of the model objects for parallel test generation/exploration these can easily mess up the results and model state. Simple way: search for keyword "static" and see if it is real state or not. If it is state, make it instance specific.

  * Do not rely on BeforeSuite to initialize something instance-specific.

The BeforeSuite tagged methods are only executed once before test generation. After this, the following test cases can be executed on different instances. You can avoid this by using the plain generator or having the ModelFactory return same instances every time. But better avoid it.