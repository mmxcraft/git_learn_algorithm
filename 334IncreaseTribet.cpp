#include <iostream>
#include <vector>
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	bool increasingTriplet(vector<int>& nums) {
		if (nums.size() < 2) return false;
		int min = nums[0];
		int mid = INT32_MAX;
		for (int i = 1; i < nums.size(); i++) {
			if (nums[i] > mid)
				return true;
			if (nums[i] > min && nums[i] < mid) {
				mid = nums[i];
				continue;
			}
			if (nums[i] < min)
				min = nums[i];
		}

	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<int> num{ 4,10,4,3,8,9 };
	cout << "Have 3 = " << test.increasingTriplet(num) << endl;


	char aa;
	cin >> aa;
	return 0;
}
