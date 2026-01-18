// LeetCode 3813. Vowel-Consonant Score
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int vowelConsonantScore(String s) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return consonants == 0 ? 0 : vowels / consonants;
    }
}
