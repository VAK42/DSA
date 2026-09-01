import java.util.BitSet;
public class BloomFilter {
  private final BitSet bitSet;
  private final int size;
  public BloomFilter(int size) {
    this.size = size;
    this.bitSet = new BitSet(size);
  }
  public BloomFilter() {
    this(100);
  }
  private int hash1(String item) {
    int hash = 0;
    for (int i = 0; i < item.length(); i++) {
      hash = (hash << 5) + hash + item.charAt(i);
      hash = Math.abs(hash % size);
    }
    return hash;
  }
  private int hash2(String item) {
    int hash = 5381;
    for (int i = 0; i < item.length(); i++) {
      hash = ((hash << 5) + hash) + item.charAt(i);
      hash = Math.abs(hash % size);
    }
    return hash;
  }
  private int hash3(String item) {
    int hash = 0;
    for (int i = 0; i < item.length(); i++) {
      hash = (hash * 31) + item.charAt(i);
      hash = Math.abs(hash % size);
    }
    return hash;
  }
  public void insert(String item) {
    bitSet.set(hash1(item));
    bitSet.set(hash2(item));
    bitSet.set(hash3(item));
  }
  public boolean mayContain(String item) {
    return bitSet.get(hash1(item)) && bitSet.get(hash2(item)) && bitSet.get(hash3(item));
  }
}