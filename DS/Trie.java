import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Trie {
  public static class TrieNode {
    public char character;
    public boolean isCompleteWord;
    public Map<Character, TrieNode> children;
    public TrieNode(char character, boolean isCompleteWord) {
      this.character = character;
      this.isCompleteWord = isCompleteWord;
      this.children = new HashMap<>();
    }
    public TrieNode(char character) {
      this(character, false);
    }
    public TrieNode getChild(char character) {
      return children.get(character);
    }
    public TrieNode addChild(char character, boolean isCompleteWord) {
      if (!children.containsKey(character)) {
        children.put(character, new TrieNode(character, isCompleteWord));
      }
      TrieNode child = children.get(character);
      child.isCompleteWord = child.isCompleteWord || isCompleteWord;
      return child;
    }
    public TrieNode addChild(char character) {
      return addChild(character, false);
    }
    public boolean hasChild(char character) {
      return children.containsKey(character);
    }
    public boolean hasChildren() {
      return !children.isEmpty();
    }
    public List<Character> suggestChildren() {
      return new ArrayList<>(children.keySet());
    }
  }
  private final TrieNode head;
  public Trie() {
    head = new TrieNode('*');
  }
  public Trie addWord(String word) {
    TrieNode curr = head;
    for (int i = 0; i < word.length(); i++) {
      boolean isComplete = (i == word.length() - 1);
      curr = curr.addChild(word.charAt(i), isComplete);
    }
    return this;
  }
  public boolean doesWordExist(String word) {
    TrieNode lastNode = getLastCharacterNode(word);
    return lastNode != null && lastNode.isCompleteWord;
  }
  public boolean hasPrefix(String prefix) {
    return getLastCharacterNode(prefix) != null;
  }
  public List<Character> suggestNextCharacters(String word) {
    TrieNode lastNode = getLastCharacterNode(word);
    if (lastNode == null) {
      return new ArrayList<>();
    }
    return lastNode.suggestChildren();
  }
  public Trie deleteWord(String word) {
    deleteWord(head, word, 0);
    return this;
  }
  private boolean deleteWord(TrieNode current, String word, int index) {
    if (index == word.length()) {
      if (!current.isCompleteWord) {
        return false;
      }
      current.isCompleteWord = false;
      return !current.hasChildren();
    }
    char ch = word.charAt(index);
    TrieNode node = current.getChild(ch);
    if (node == null) {
      return false;
    }
    boolean shouldDeleteCurrentNode = deleteWord(node, word, index + 1) && !node.isCompleteWord;
    if (shouldDeleteCurrentNode) {
      current.children.remove(ch);
      return !current.hasChildren();
    }
    return false;
  }
  private TrieNode getLastCharacterNode(String word) {
    TrieNode curr = head;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (!curr.hasChild(ch)) {
        return null;
      }
      curr = curr.getChild(ch);
    }
    return curr;
  }
}