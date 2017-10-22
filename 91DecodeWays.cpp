#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
	int numDecodings(string s) {
		int sz = s.size();
		if (sz == 0)
			return 0;

		int end1, end2, end3;
		int prevend1, prevend2, prevend3;

		end1 = end2 = end3 = 0;

		if (s[0] == '1')
			end1 = 1;
		else if (s[0] == '2')
			end2 = 1;
		else if (s[0] == '0')
			return 0;
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

		}

		return end1 + end2 + end3;

	}
};

int main(int argc, char**argv)
{
	Solution test;
	string s("110");
	cout << "Decode nums=" << test.numDecodings(s) << endl;
	char aa;
	cin >> aa;
	return 0;
}
