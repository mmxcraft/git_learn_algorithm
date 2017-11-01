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
	int change(int amount, vector<int>& coins) {
		int sz = coins.size();
		vector<vector<int>> dp(sz+1, vector<int>(amount+1, 0));

		for (int i = 0; i <= sz; i++)
			dp[i][0] = 1;
		for (int i = 1; i <= amount; i++)
			dp[0][i] = 0;

		for(int i = 1; i<=sz; i++)
			for (int j = 1; j <= amount; j++)
			{
				dp[i][j] = dp[i - 1][j] + (j < coins[i-1]?0:dp[i][j - coins[i-1]]);
			}
		return dp[sz][amount];
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<int> numList{ 1,3,4,5};

	cout << test.change(7,numList) << endl;
	//cout << test.change(1232,numList) << endl;

	char aa;
	cin >> aa;
	return 0;
}
