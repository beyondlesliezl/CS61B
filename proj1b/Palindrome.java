public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private boolean helper(Deque a, Deque b) {
        if (a.size() == 0) {
            return true;
        }
        return (a.removeFirst() == b.removeFirst()) && helper(a, b);
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        if (d.size() == 1 || d.size() == 0) {
            return true;
        }
        Deque test = new LinkedListDeque();
        for (int i = word.length() - 1; i >= 0; i--) {
            test.addLast(word.charAt(i));
        }//得到倒序的word
        return helper(d, test);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
