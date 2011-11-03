package osmo.tester.model.dataflow;

import java.util.ArrayList;
import java.util.Collection;

import static osmo.common.TestUtils.cInt;

/** @author Teemu Kanstren */
public class ReadableWords extends SearchableInput<String> {
  /** Minimum length of generated word. */
  private int min = 5;
  /** Maximum length of generated word. */
  private int max = 10;
  /** Used to create characters for words. */
  private ReadableChars chars = new ReadableChars();
  /** History of generated words. */
  private Collection<String> history = new ArrayList<String>();

  /** Constructor for default values (min=5, max=10). */
  public ReadableWords() {
  }

  /**
   * Constructor for setting minimum and maximum char sequence size.
   *
   * @param min The minimum length.
   * @param max The maximum length.
   */
  public ReadableWords(int min, int max) {
    this.min = min;
    this.max = max;
    if (min < 0 || max < 0) {
      throw new IllegalArgumentException("Minimum or maximum length are not allowed to be negative (was " + min + ", " + max + ")");
    }
    if (max < min) {
      throw new IllegalArgumentException("Maximum length is not allowed to be less than minimum length.");
    }
    if (max == 0) {
      throw new IllegalArgumentException("Min and max are equal - generating/evaluating empty strings makes no sense.");
    }
  }

  @Override
  public void setStrategy(DataGenerationStrategy algorithm) {
    throw new UnsupportedOperationException(ReadableChars.class.getSimpleName() + " does not support setting data generation strategy.");
  }

  /**
   * Generates a sequence of readable characters, in length between the configured min and max values.
   *
   * @return The generated character sequence.
   */
  public String next() {
    int length = cInt(min, max);
    char[] c = new char[length];
    for (int i = 0; i < length; i++) {
      c[i] = chars.next();
    }
    String next = new String(c);
    history.add(next);
    observe(next);
    return next;
  }

  public void enableXml() {
    chars.enableXml();
  }

  public void asciiLettersAndNumbersOnly() {
    chars.asciiLettersAndNumbersOnly();
  }

  public void reduceBy(String charsToRemove) {
    chars.reduceBy(charsToRemove);
  }

  @Override
  /**
   * Evaluate that all characters in the given char sequence are in the readable set.
   *
   * @param word The char sequence to evaluate.
   * @return True if all characters are in the readable set, false otherwise.
   */
  public boolean evaluate(String word) {
    char[] wordChars = word.toCharArray();
    boolean result = true;
    for (char c : wordChars) {
      boolean b = chars.evaluate(c);
      result = result && b;
    }
    return result;
  }

  @Override
  public boolean evaluateSerialized(String item) {
    return evaluate(item);
  }

  public Collection<String> getHistory() {
    return history;
  }
}