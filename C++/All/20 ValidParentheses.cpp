// LeetCode 20. Valid Parentheses
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <stack>
#include <string>
using namespace std;

class Solution {
public:
    bool isValid(string s) {
        stack<char> st;

        for (char c : s) {
            // Push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            }
            // Handle closing brackets
            else {
                if (st.empty()) return false;

                char top = st.top();
                st.pop();

                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // Valid if stack is empty at the end
        return st.empty();
    }
};
