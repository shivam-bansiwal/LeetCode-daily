// LeetCode 3813. Vowel-Consonant Score
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <string>
using namespace std;

class Solution {
public:
    int vowelConsonantScore(string s) {
        int vowels = 0;
        int consonants = 0;

        for (char c : s) {
            if (isalpha(c)) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return consonants == 0 ? 0 : vowels / consonants;
    }
};
