package net.mahdirazavi.test.stringmatching;


public class RabinKarp {

    static final long prime = 101;

    public static void search(String text, String pattern) {
        System.out.println("\nRabinKarp String matching algorithm starts... ");

        int n = text.length();
        int m = pattern.length();
        long key = getSubKey(pattern, m);
        long oldHash = getSubKey(text.substring(0, m), m);
        if (key == oldHash && equal(text, pattern, 0))
//            return "Yes";
            System.out.println("String found at " + (0) + " position!!");
        for (int i = m; i < n; i++) {
            oldHash = getNewHash(text, i - m, i, oldHash, m);
            if (key == oldHash && equal(text, pattern, i - m + 1))
//                return "Yes";
                System.out.println("String found at " + (i - m + 1) + " position!!");
        }
//        return "No";
    }

    private static long getNewHash(String str, int oldIndex, int newIndex, long oldHash, int m)//get newhash in constant time
    {
        long newHash = ((oldHash - str.charAt(oldIndex) + 96) / prime) + ((str.charAt(newIndex) - 96) * (long) Math.pow(prime, m - 1));
        return newHash;
    }

    private static long getSubKey(String sub, int m)//hashing function
    {
        long key = 0;
        for (int i = 0; i < m; i++) {
            key += (sub.charAt(i) - 96) * (long) Math.pow(prime, i);
        }
        return key;
    }

    private static boolean equal(String str, String sub, int index)//to check two string are equal or not
    {
        for (int i = 0; i < sub.length(); i++)
            if (str.charAt(index + i) != sub.charAt(i))
                return false;
        return true;
    }
}