#include <iostream>
#include <map>
#include <vector>
#include <algorithm> 
#include <stdio.h>
#include <string.h>

using namespace std;

class Solution {
public:
	int uniquePaths(int m, int n) 
	{
		int *dp = new int[m*n];
		for (int i = 0; i<m; i++)
			dp[i*n] = 1;
		for (int j = 0; j<n; j++)
			dp[j] = 1;

		for (int i = 1; i<m; i++)
			for (int j = 1; j<n; j++)
				dp[i*n+j] = dp[(i-1)*n+j] + dp[i*n+j-1];
		int result = dp[m*n-1];
		delete[] dp;
		return result;
	}	
};

int main(int argc, char**argv)
{
	Solution test;
	cout << test.uniquePaths(6,8) << endl;
	char aa;
	cin >> aa;
	return 0;
}

