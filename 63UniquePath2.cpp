#include <iostream>
#include <map>
#include <vector>

using namespace std;

class Solution {
public:
	int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid)
	{
		int m, n;
		m = obstacleGrid.size();
		n = obstacleGrid[0].size();

		vector<vector<int> > dp(m, vector<int>(n, 1));
		int i, j;

		for(i=0;i<n;i++)
			if (obstacleGrid[0][i] == 1) {
				for (j = i; j < n; j++)
					dp[0][j] = 0;
				break;
			}
		for (j = 0; j < m; j++)
			if (obstacleGrid[j][0] == 1) {
				for(i=j; i<m;i++)
					dp[i][0] = 0;
			break;
			}
		for (i = 1; i<m; i++)
			for (j = 1; j<n; j++)
				if (obstacleGrid[i][j])
					dp[i][j] = 0;
				else {
					int left, up;
					left = (obstacleGrid[i - 1][j] == 1) ? 0 : dp[i - 1][j];
					up = (obstacleGrid[i][j - 1] == 1) ? 0 : dp[i][j - 1];
					dp[i][j] = left + up;
				}

				return dp[m - 1][n - 1];
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<vector<int>> grid{ {1,0} };
	
	cout << "Path=" << test.uniquePathsWithObstacles(grid) << endl;
	char aa;
	cin >> aa;
	return 0;
}

