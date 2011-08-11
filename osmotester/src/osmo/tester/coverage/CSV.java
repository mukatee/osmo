package osmo.tester.coverage;

import java.util.Map;
import osmo.tester.generator.testsuite.TestSuite;
import osmo.tester.model.FSMTransition;

/**
 * Returns coverage tables in comma separate value (CSV) format
 * 
 * @author Olli-Pekka Puolitaival
 */
public class CSV extends CoverageMetric {
  
  public CSV(TestSuite ts) {
    super(ts);
  }

  @Override
  public String getTransitionCoverage(){
    String ret = "Name;Count\n";
    Map<FSMTransition, Integer> coverage = countTransitionCoverage();
     for(Map.Entry<FSMTransition, Integer> a : coverage.entrySet()){
       ret += a.getKey().getName()+";"+a.getValue()+"\n";
     }
     return ret;
  }
  
  /**
   * Output something like this
   * transition1;transition2;2
   * transition2;transition3;0
   * */
  @Override
  public String getTransitionPairCoverage(){
    String ret = "From;To;Count\n";
    Map<String, Integer> coverage = countTransitionPairCoverage();
    for(Map.Entry<String, Integer> a : coverage.entrySet()){
      ret += a.getKey()+";"+a.getValue()+"\n";
    }
    return ret;
  }
  
  public String traceabilityMatrix(){
    //TODO: implement
    return null;
  }

  @Override
  public String getRequirementsCoverage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getTraceabilityMatrix() {
    // TODO Auto-generated method stub
    return null;
  }
}
