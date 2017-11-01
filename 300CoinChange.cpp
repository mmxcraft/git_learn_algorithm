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
	int coinChange(vector<int>& coins, int amount) {
		int sz = coins.size();
		vector<int> dp(amount+1, INT32_MAX);
		dp[0] = 0;

		for(int i=1; i<= amount; i++)
			for (int j = 0; j < sz; j++) {
				if (coins[j] == i)
					dp[i] = 1;
				else if (coins[j] < i) {
					dp[i] = min(dp[i - coins[j]] + 1, dp[i]);
				}
			}
		return dp[amount];
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<int> numList{ 1,3,4,5,8,10,20,50, 100 };

	cout << test.coinChange(numList, 7) << endl;
	cout << test.coinChange(numList, 1232) << endl;

	char aa;
	cin >> aa;
	return 0;
}
