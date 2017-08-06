#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	bool PredictTheWinner(vector<int>& nums) {
		int sz = nums.size();
		if (sz == 1) return true;

		vector<vector<int>> dp(sz, vector<int>(sz, 0));

		for (int i = 0; i<sz; i++)
			dp[i][i] = nums[i];
		for (int len = 1; len < sz; len++)
		{
			for (int i = 0; i+len < sz; i++)
				dp[i][i + len] = max(nums[i] - dp[i+1][i+len], nums[i + len] - dp[i][i + len-1]);
		}

		return dp[0][sz - 1] >= 0;

	}
};

int main(int argc, char** argv)
{
	Solution s;
	vector<int> test;
	test.push_back(1);
	test.push_back(5);
	//test.push_back(233);
	test.push_back(2);

	cout << s.PredictTheWinner(test) << endl;


	return 0;
}