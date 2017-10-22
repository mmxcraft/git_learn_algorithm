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
	int getMoneyAmount(int n) {
		vector<vector<int>> dp(n + 1, vector <int>( n + 1, 0));

		for (int j = 2; j <= n; j++) 
		{
			for (int i = j - 1; i > 0; i--)
			{
				int globalMin = INT_MAX;
				for (int k = i + 1; k < j; k++) 
				{
					int localMAX = k + max(dp[i][k - 1], dp[k + 1][j]);
					globalMin = min(globalMin, localMAX);
				}
			dp[i][j] = i + 1 == j ? i : globalMin;
			}

		}

		return dp[1][n];
	}
};

class SolutionB {
public:
	int getMoneyAmount(int n) {
		vector<vector<int>> dp(n + 1, vector <int>(n + 1, 0));
		return calcAmout(dp, 1, n);
	}

	int calcAmout(vector<vector<int>> &dp, int s, int e) {
		if (s >= e) return 0;
		if (dp[s][e] != 0) return dp[s][e];
		int res = INT_MAX;
		for (int x = s; x <= e; x++)
		{
			int tmp = x + max(calcAmout(dp, s, x - 1), calcAmout(dp, x + 1, e));
			res = min(res, tmp);
		}
		dp[s][e] = res;
		return res;
	}
};


int main(int argc, char**argv)
{
	Solution test;

	cout << "amount(10) = " << test.getMoneyAmount(10) << endl;
	cout << "amount(11) = " << test.getMoneyAmount(11) << endl;
	cout << "amount(12) = " << test.getMoneyAmount(12) << endl;

	char aa;
	cin >> aa;
	return 0;
}
