#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <map>
#include <functional> 
#include <algorithm>
#include <climits>
#include <stdlib.h>


using namespace std;

class Solution {
public:
	int wiggleMaxLength(vector<int>& nums) {
		int sz = nums.size();
		if (sz <=1) return sz;

		int len = 1;
		int curFlag = 0;
		int posMax=0, negMin=0;

		for (int i = 0; i < sz-1; i++) {
			int flag;

			if (curFlag == 0)
				flag = nums[i + 1] - nums[i];
			else if (curFlag > 0) {
				flag = nums[i + 1] - posMax;
			}else {
				flag = nums[i + 1] - negMin;
			}


			if (flag < 0)
			{
				if (curFlag > 0 || curFlag == 0) {
					len++;
					curFlag = flag;
					negMin = nums[i + 1];
				}
				else if (negMin > nums[i + 1])
					negMin = nums[i + 1];
			}
			else if (flag > 0)
			{
				if (curFlag < 0 || curFlag == 0) {
					len++;
					curFlag = flag;
					posMax = nums[i + 1];
				}
				else if (posMax < nums[i + 1]) 
					posMax = nums[i + 1];
			}
			else
				continue;
		}

		return len;
	}
};

class SolutionB { //Better DP example
public:
	int wiggleMaxLength(vector<int>& nums) {
		int sz = nums.size();
		if (sz <= 1) return sz;
		
		vector<int> up(sz, 0);
		vector<int> down(sz, 0);
		up[0] = down[0] = 1;
		
		for (int i = 1; i < sz; i++)
		{
			if (nums[i] > nums[i - 1]) {
				up[i] = down[i - 1] + 1;
				down[i] = down[i - 1];
			}
			else if (nums[i] < nums[i - 1]) {
				up[i] = up[i - 1];
				down[i] = up[i - 1] + 1;
			} else {
				up[i] = up[i - 1];
				down[i] = down[i - 1];
			}

		}

		return max(up[sz - 1], down[sz - 1]);

	}
};

int main(int argc, char**argv)
{
	Solution test;
	SolutionB testb;
	vector<int>  nums{ 1,17,5,10,13,15,10,5,16,8 };
	vector<int>  nums1{ 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	vector<int>  nums2{ 1,7,4,9,2,5 };


	cout << "wiggleMaxLength(0) = " << test.wiggleMaxLength(nums) << endl;
	cout << "wiggleMaxLength(1) = " << test.wiggleMaxLength(nums1) << endl;
	cout << "wiggleMaxLength(2) = " << test.wiggleMaxLength(nums2) << endl;

	cout << "wiggleMaxLength(0) = " << testb.wiggleMaxLength(nums) << endl;
	cout << "wiggleMaxLength(1) = " << testb.wiggleMaxLength(nums1) << endl;
	cout << "wiggleMaxLength(2) = " << testb.wiggleMaxLength(nums2) << endl;


	char aa;
	cin >> aa;
	return 0;
}
