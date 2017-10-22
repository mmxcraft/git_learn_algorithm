#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <functional> 
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	int findKthLargest(vector<int>& nums, int k) {
		int sz = nums.size();
		std::priority_queue<int, vector<int>, greater<int> > mypq;
		for (int i = 0; i < sz; i++) {
			if (mypq.size() < k)
				mypq.push(nums[i]);
			else {
				if (nums[i] > mypq.top())
				{
					mypq.pop();
					mypq.push(nums[i]);
				}
			}
		}

		//for (int i = 0; i < k-1; i++) mypq.pop();
		return mypq.top();
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<int> num{ 3,2,1,5,6,4 };
	cout << "result = " << test.findKthLargest(num,2) << endl;

	char aa;
	cin >> aa;
	return 0;
}
