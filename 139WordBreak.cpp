#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	bool wordBreak(string s, vector<string>& wordDict) {
		if (s.empty()) return false;
		
		int sz = s.size();
		vector<int> dp(sz, 0) ;
		int max_pos = 0;
		
		for (int i = 0; i < wordDict.size();) {
			int pos = 0;
			size_t found = 0;
			string str1 = s.substr(max_pos, sz);

			while (str1.find(wordDict[i]) == 0) {
					pos += wordDict[i].size();
					str1 = str1.substr(wordDict[i].size(), str1.size());
			}
			if (pos != 0) {
				pos += max_pos;
				if (pos == sz)
					return true;
				max_pos = pos;
				i = 0;
				continue;
			}

			string str(s);
			while (found == 0) {
				found = str.find(wordDict[i]);
				if (found == string::npos)
					break;
				else if (found == 0) {
					pos += wordDict[i].size();
					str = str.substr(found + wordDict[i].size(), str.size());
				}
				else if (pos + found <= max_pos) {
					pos = wordDict[i].size() + found + pos;
					str = str.substr(found + wordDict[i].size(), str.size());
				}
				else
					continue;

			}

			if (pos == sz) return true;
			if (max_pos < pos)
			{
				i = 0;
				max_pos = pos;
			}

			i++;
		}
		return false;

	}
};

class SolutionBetter {
public:
	bool wordBreak(string s, vector<string>& wordDict) {
		if (s.empty()) return false;
		set<string> setDict(wordDict.begin(), wordDict.end());

		int sz = s.size();
		vector<bool> dp(sz+1, false);
		dp[0] = true;
		for (int i = 1; i < sz; i++) {
			for(int j = i-1; j>=0; j--)
			if (dp[j]) {
				string word = s.substr(j, i - j);
				if (setDict.find(word) != setDict.end()) {
					dp[i] = true;
					break;
				}

			}
		}

		return dp[sz];
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<string> wordDict{ "cc","ac"};
	string str("ccaccc");

	cout << "result = " << test.wordBreak(str, wordDict) << endl;

	char aa;
	cin >> aa;
	return 0;
}
