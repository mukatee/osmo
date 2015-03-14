# Changelog #

Versions: x.y.z where
  * x is a major release. e.g., add a new concept. Some major changes to usage etc. can be expected.
  * y adds new features to the current set. Backwards compatibility to x is mostly maintained. Some minor API changes are possible.
  * z minor fixes to the current x.y version. No API change.

### v3.3.1 02/12/2013 ###
  * optimizing memory & cpu use in optimizers
  * bug fixes

### v3.3.0 15/11/2013 ###
  * added MultiOSMO
  * added trace reporting to all generators
  * refactoring and cleanup
  * updated docs
  * fixed manualdrive
  * fixes to laststep
  * added tutorial for scenarios

### v3.2.0 27/10/2013 ###
  * removed TestSuiteField -> recognized from field type
  * removed RequirementsField -> recognized from field type
  * added support to read step names from method names
  * added support for scenarios: startup sequence + slices
  * changed data generators to use explicit methods instead of next()
  * changed name comparison to ignore case
  * StateName -> CoverageValue

### v3.1.0 29/08/2013 ###
  * seed is now parameter to generator, not a static value (for parallel runs) (API change)
  * optimized greedy optimizer to run final sort much faster
  * added check for multiple steps with same name
  * added more extensive coverage metric printing
  * some cleanup: removed scripting from a file, removed output interface (API change)
  * added group support at class level
  * refactored coverage target definitions to separate objects (API change)
  * added state-pairs to coverage options
  * added support to print model structure
  * integrated online exploration

### v3.0.0 16/03/2013 ###
  * @EndState removed
  * Cleaned up old stuff
  * Merged some end conditions to ElementCoverage end condition
  * Added end condition for StateCoverage (of @StateName values)
  * Added LengthProbability end condition
  * Fixed bugs
  * Added group support to test steps, guards, and pre-post-elements
  * Added offline optimizer and associated test generation coverage mechanisms

> Interface changes:

|     | **Library** |
|:----|:------------|
| **OLD:** | osmo.tester.model.dataflow.`*` |
| **NEW:** | osmo.tester.model.data.`*` |

### v 2.5.0 20/02/2013 ###
  * @StateName added
  * Cleaned up old stuff
  * Fixed various bugs in ManualDrive
  * Seed must now be set explicitly

> Interface changes:

osmo.tester.model.dataflow.Words -> osmo.tester.model.dataflow.Text
|     | **Library** | **Code Example** |
|:----|:------------|:-----------------|
| **OLD:** | osmo.tester.model.dataflow.Words | Words words = new Words(3, 7); |
| **NEW:** | osmo.tester.model.dataflow.Text | Text words = new Text(3, 7); |


osmo.tester.scripter.robotframework.Scripter -> osmo.tester.scripter.robotframework.RFScripter
|     | **Library** | **Code Example** |
|:----|:------------|:-----------------|
| **OLD:** | osmo.tester.scripter.robotframework.Scripter | Scripter scripter = new Scripter(6);  |
| **NEW:** | osmo.tester.scripter.robotframework.RFScripter | RFScripter scripter = new RFScripter(6);  |


### v 2.4.0 12/10/2012 ###
  * Test report showing traces for generated tests
  * Added annotation for @LastStep
  * Guard association negation support
  * Sorting of steps, guards, and pre-post elements (determinism++)
  * Draft scripter interface

### v 2.3.1 28/06/2012 ###
  * Fixed build error (instrumented classes)

### v 2.3.0 13/06/2012 ###
  * Using Clover for test coverage in development
  * Upgraded build to JDK 7
  * Performance tuning for algorithms
  * Bug fixes
  * Added getOptions to some data model objects
  * Refactored some API methods for clarity
  * Added Time end condition
  * Added ability to define slice configurations for inline variables
  * GUI metric changes
  * Added basic support for integrated Jenkins reporting
  * Exception unwrapping is on by default
  * Out of bounds data generation options

### v 2.2.1 08/02/2012 ###
  * Fix for "n must be positive" error in Balancing algorithm.

### v 2.2.0 29/01/2012 ###
  * JUnit integration
  * Separate OSMOConfiguration object
  * TestStep to replace Transition (which will still remain)
  * Tutorials for various features

### v 2.1.0 13/12/2011 ###
  * manual GUI update, asking variable values now..

### v 2.0.1 30/11/2011 ###
  * added missing templates and libraries needed for report generation to distribution package

### v 2.0.0 24/11/2011 ###
  * several fixes to make calendar example better, including more deterministic etc.
  * separated examples to different module from core
  * added option for strict end conditions
  * added end condition for data (variable) coverage
  * added end condition for transition (step) coverage
  * added filtering support to define when some steps or values should not be used
  * added support for model prefixes
  * added support for custom (user) attributes inside test case objects (from test models)
  * improved suite optimization algorithms
  * improved checking for invalid configurations
  * created an api to use the test generator externally not just from OSMOTester class
  * improved (new) weighted random algorithm
  * renamed optimized random to less random
  * added option to use testutils functions with separate seeds at the same time (Randomizer in common module)
  * added first draft GUI for running OSMOTester manually
  * added first draft of text based domain specific language
  * added coverage matrices
  * refactored (warning: api change), fixed bugs, the usual stuff..


### v 1.2.1 17/10/2011 ###
  * fixes to the calendar example
  * random test generation algorithm uses the common seed now
  * valuerangeset to support boundaryscan (was only valuerange)

### v 1.2.0 04/10/2011 ###
  * added extensive calendar example (in source code) and tutorial (in manual)
  * added robot framework scripter
  * added ASCII reporting

### v 1.1.0 09/09/2011 ###
  * fixes to make random numbers more uniformly distributed
  * added transitioncoverage endcondition

### v 1.0.0 24/08/2011 ###
  * This is the first version to provide some stable interface
  * Cleaned up excess parameters from TestSuite and TestStep
  * Fixed spamming over several log files ([issue #7](https://code.google.com/p/osmo/issues/detail?id=#7))
  * Changed TestSuite initialization to require non-null value
  * Added requirements coverage and test data reporters ([issue #6](https://code.google.com/p/osmo/issues/detail?id=#6), [issue #11](https://code.google.com/p/osmo/issues/detail?id=#11))
  * Added basic boundary scan algorithm to dataflow generators ([issue #9](https://code.google.com/p/osmo/issues/detail?id=#9))
  * Defined common interfaces for dataflow input and output ([issue #8](https://code.google.com/p/osmo/issues/detail?id=#8))

### v 0.4.0 02/08/2011 ###
  * added @Variable annotation and resulting state storage to TestStep and @Pre and @Post parameters.
  * fixed some bugs and did some optimizations


### v 0.3.0 16/07/2011 ###
  * split oracle to pre and post methods

### v 0.2.0 12/07/2011 ###
  * added features based on v0.1 experiences and feedback, and invariance taxonomy
  * added weights to transitions
  * added weighted random algorithm
  * added `@EndState` annotation
  * renamed generation algorithms to FSM traversal algorithms
  * renamed strategies to end conditions
  * added compositional end conditions (or/and) for test cases and test suites
  * added support for several end conditions for both test case and test suite
  * added data generation and evaluation components, including readable characters, value range, and value range set
  * added implementation for basic test suite optimizers
  * fixed some bugs and did some refactoring to clarify code

### v 0.1.0 06/2011 ###
  * initial release