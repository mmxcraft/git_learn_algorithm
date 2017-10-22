#include <iostream>
#include <map>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int minPathSum(vector<vector<int>>& grid) {
		int m, n;
		m = grid.size();
		n = grid[0].size();

		vector<vector<int>> dp(m, vector<int>(n,0));

		int i, j;
		dp[0][0] = grid[0][0];
		for (i = 1; i < n; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (j = 1; j < m; j++) {
			dp[j][0] = dp[j - 1][0] + grid[j][0];
		}
		for(i=1; i<m; i++)
			for (j = 1; j < n; j++)
			{
				dp[i][j] = min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
			}
		return dp[m - 1][n - 1];

	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<vector<int>> grid{ {1,2,3},{3,4,5},{1,2,3} };
	
	cout << "Path=" << test.minPathSum(grid) << endl;
	char aa;
	cin >> aa;
	return 0;
}

