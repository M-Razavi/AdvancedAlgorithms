package net.mahdirazavi.test.stringmatching;


public class RabinKarp implements Searchable {

    static final long prime = 101;

    public void search(String text, String pattern) {
        System.out.println("\nRabinKarp String matching algorithm starts... ");

        int n = text.length();
        int m = pattern.length();
        long key = getSubKey(pattern);
        long oldHash = getSubKey(text.substring(0, m));
        if (key == oldHash && equal(text, pattern, 0))
            System.out.println("String found at " + (0) + " position!!");
        for (int i = m; i < n; i++) {
            oldHash = getNewHash(text, i - m, i, oldHash, m);
            if (key == oldHash && equal(text, pattern, i - m + 1)) {
                System.out.println("'" + pattern + "' found at index " + (i - m + 1));
            }
        }
    }

    private long getNewHash(String str, int oldIndex, int newIndex, long oldHash, int m)//get newhash in constant time
    {
        long newHash = ((oldHash - str.charAt(oldIndex) + 96) / prime) + ((str.charAt(newIndex) - 96) * (long) Math.pow(prime, m - 1));
        return newHash;
    }

    private long getSubKey(String sub)//hashing function
    {
        long key = 0;
        for (int i = 0; i < sub.length(); i++) {
            key += (sub.charAt(i) - 96) * (long) Math.pow(prime, i);
        }
        return key;
    }

    private boolean equal(String str, String sub, int index)//to check two string are equal or not
    {
        for (int i = 0; i < sub.length(); i++)
            if (str.charAt(index + i) != sub.charAt(i))
                return false;
        return true;
    }
}