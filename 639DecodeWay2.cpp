#include <iostream>
#include <string>
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	int numDecodings(string s) {
		int sz = s.size();
		if (sz == 0)
			return 0;

		unsigned long long end1, end2, end3;
		unsigned long long prevend1, prevend2, prevend3;

		end1 = end2 = end3 = 0;

		if (s[0] == '1')
			end1 = 1;
		else if (s[0] == '2')
			end2 = 1;
		else if (s[0] == '0')
			return 0;
		else if (s[0] == '*') {
			end1 = 1;
			end2 = 1;
			end3 = 7;
		}
		else
			end3 = 1;


		prevend1 = end1;
		prevend2 = end2;
		prevend3 = end3;
		for (int i = 1; i<sz; i++) {
			if (s[i] == '1') {
				end1 = prevend1 + prevend2 + prevend3;
				end2 = 0;
				end3 = prevend1 + prevend2;
			}
			else if (s[i] == '2') {
				end1 = 0;
				end2 = prevend1 + prevend2 + prevend3;
				end3 = prevend1 + prevend2;
			}
			else if (s[i] == '0') {
				if (end3 != 0 && end1 == 0 && end2 == 0)
					return 0;
				else {
					end1 = 0;
					end2 = 0;
					end3 = prevend1 + prevend2;
				}

			}
			else if (s[i] == '*') {
				end1 = prevend1 + prevend2 + prevend3;
				end2 = prevend1 + prevend2 + prevend3;
				end3 = 4 * (prevend1 * 2 + prevend2 * 2 + prevend3) + 3 * (prevend1 * 2 + prevend2 + prevend3) + 2 * (prevend1 + prevend2);
			}
			else {
				if (s[i] < '7')
					end3 = prevend1 * 2 + prevend2 * 2 + prevend3;
				else
					end3 = prevend1 * 2 + prevend2 + prevend3;
				end1 = 0;
				end2 = 0;

			}
			prevend1 = end1;
			prevend2 = end2;
			prevend3 = end3;
			//int test = (end1 + end2 + end3) % (1000000007);
			//cout << test << endl;

		}

		int ret = (end1 + end2 + end3) % (1000000007);
		return ret;

	}
};


class Solution1 {
public:
	int numDecodings(string s) {
		int n = s.size(), p = 1000000007;
		long res[2];
		res[0] = ways(s[0]);
		res[1] = ways(s[1]) * res[0] + ways(s[0], s[1]);
		for (int i = 2; i < n; i++) {
			long temp = res[1];
			res[1] = (ways(s[i])*res[1] + ways(s[i - 1], s[i]) * res[0]) % p;;
			res[0] = temp;

		}
		return (int)res[1];
	}
private:
	int ways(char ch) {
		if (ch == '*') return 9;
		if (ch == '0') return 0;
		return 1;
	}
	int ways(char ch1, char ch2) {
		char str[3];
		str[0] = ch1;
		str[1] = ch2;
		str[2] = 0;

		int temp = atoi(str);
		
		if (ch1 != '*' && ch2 != '*') {
			if (temp >= 10 && temp <= 26)
				return 1;
		}
		else if (ch1 == '*' && ch2 == '*') {
			return 15;
		}
		else if (ch1 == '*') {
			if (ch2 >= '0' && ch2 <= '6')
				return 2;
			else
				return 1;
		}
		else {
			if (ch1 == '1') {
				return 9;
			}
			else if (ch1 == '2') {
				return 6;
			}
		}
		return 0;

	}

};


int main(int argc, char**argv)
{
	Solution test;
	Solution1 test1;
	string s("**********");
	cout << "Decode nums=" << test1.numDecodings(s) << endl;
	cout << "Decode nums=" << test.numDecodings(s) << endl;

	
	char aa;
	cin >> aa;
	return 0;
}
