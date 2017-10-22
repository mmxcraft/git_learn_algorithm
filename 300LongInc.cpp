#include <iostream>
#include <vector>
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	int lengthOfLIS(vector<int>& nums) {
		int sz;
		sz = nums.size();
		if (sz < 1)
			return 0;
		else if (sz == 1)
			return 1;

		vector<int> Lis(sz,0);
		int len = 1;
		Lis[0] = nums[0];
		int i, j;
		for (i = 1; i < sz; i++) {
			for (j = 0; j < len; j++) {
				if (nums[i] < Lis[j]) {
					if ((j > 0 && nums[i] > Lis[j - 1]) || j == 0) {
						Lis[j] = nums[i];
						break;
					}
				}
			}
			if (nums[i] > Lis[len - 1]) {
				Lis[j] = nums[i];
				len++;
			}
		}

		return len;
	}
};

class Solution1 {
public:
	int lengthOfLIS(vector<int>& nums) {
		vector<int> res;
		for (int i = 0; i < nums.size(); i++) {
			vector<int>::iterator it;
			it = lower_bound(res.begin(), res.end(), nums[i]);
			if (it == res.end())
				res.push_back(nums[i]);
			else
				*it = nums[i];
		}
		return res.size();
	}
};


int main(int argc, char**argv)
{
	Solution test;
	vector<int> num{ 4,10,4,3,8,9};
	cout << "Len=" << test.lengthOfLIS(num) << endl;


	char aa;
	cin >> aa;
	return 0;
}
