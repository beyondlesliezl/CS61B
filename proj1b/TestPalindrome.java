import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
       boolean d = palindrome.isPalindrome("ab") ;
       boolean c = palindrome.isPalindrome("ccc");
       boolean a = palindrome.isPalindrome("");
       boolean b = palindrome.isPalindrome("b");
       boolean e = palindrome.isPalindrome("racecar");
       assertFalse(d);
       assertTrue(c);
       assertTrue(a);
       assertTrue(b);
       assertTrue(e);
    }

    @Test
    public void new_testIsPalindrome(){
       CharacterComparator cc = new OffByOne();
        boolean a = palindrome.isPalindrome("", cc) ;  //true
        boolean b = palindrome.isPalindrome("a", cc);  //true
        boolean c = palindrome.isPalindrome("aa", cc); //false
        boolean d = palindrome.isPalindrome("rq", cc); //true
        boolean e = palindrome.isPalindrome("&%", cc); //true
        assertTrue(a);
        assertTrue(b);
        assertTrue(d);
        assertTrue(e);
        assertFalse(c);

    }
}
