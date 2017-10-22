#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <map>
#include <functional> 
#include <algorithm>
#include <utility>  
#include <climits>
#include <stdlib.h>


using namespace std;

class Solution {
public:
	int maxEnvelopes(vector<pair<int, int>>& envelopes) {
		
		sort(envelopes.begin(), envelopes.end(), myCompareF);
		int sz = envelopes.size();
		int res = 1;

		int minF = envelopes[0].first;
		int minS = envelopes[0].second;

		for (int i = 1; i < sz; i++) 
		{
			if (envelopes[i].second > minS && envelopes[i].first > minF) {
				res++;
				minS = envelopes[i].second;
				minF = envelopes[i].first;
			}
		}

		int tmp = res;
		res = 1;
		minF = envelopes[0].first;
		minS = envelopes[0].second;

		sort(envelopes.begin(), envelopes.end(), myCompareS);

		for (int i = 1; i < sz; i++)
		{
			if (envelopes[i].second > minS && envelopes[i].first > minF) {
				res++;
				minS = envelopes[i].second;
				minF = envelopes[i].first;
			}
		}


		return max(tmp,res);
	}

	int myCompareF(pair<int, int> &p1, pair<int, int> &p2) {
		if (p1.first == p2.first)
			return p1.second - p2.second;
		return p1.first - p2.first;
	}
	int myCompareS(pair<int, int> &p1, pair<int, int> &p2) {
		if (p1.second == p2.second)
			return p1.first - p2.first;
		return p1.second - p2.second;
	}

};


int main(int argc, char**argv)
{
	Solution test;
	vector<int>  nums{ 1,17,5,10,13,15,10,5,16,8 };
	vector<int>  nums1{ 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	vector<int>  nums2{ 1,7,4,9,2,5 };


	cout << "wiggleMaxLength(0) = " << test.wiggleMaxLength(nums) << endl;
	cout << "wiggleMaxLength(1) = " << test.wiggleMaxLength(nums1) << endl;
	cout << "wiggleMaxLength(2) = " << test.wiggleMaxLength(nums2) << endl;



	char aa;
	cin >> aa;
	return 0;
}
