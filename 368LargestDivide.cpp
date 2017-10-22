#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <map>
#include <functional> 
#include <algorithm>
#include <stdlib.h>

using namespace std;

class Solution {
public:
	vector<int> largestDivisibleSubset(vector<int>& nums) {
		int sz = nums.size();
		sort(nums.begin(), nums.end());
		vector<int> T(sz, 0);
		vector<int> parent(sz, 0);

		int m = 0;
		int mi = 0;

		for (int i = sz-1; i >= 0 ; --i) 
		{
			for (int j = i; j < sz; j++) 
			{
				if (nums[j] % nums[i] == 0 && T[i] < 1 + T[j]) 
				{
					T[i] = 1 + T[j];
					parent[i] = j;

					if (T[i] > m) 
					{
						m = T[i];
						mi = i;
					}
				}

			}
		}

		vector<int> ret;
		for (int i = 0; i < m; i++)
		{
			ret.push_back(nums[mi]);
			mi = parent[mi];
		}
	
		return ret;
	}
};

class SolutionB {
public:
	vector<int> largestDivisibleSubset(vector<int>& nums) {
		int sz = nums.size();
		sort(nums.begin(), nums.end());
		vector<int> T(sz, 0);
		vector<int> parent(sz, 0);

		int m = 0;
		int mi = 0;

		for (int i = 0; i < sz; i++)
		{
			for (int j = i; j >=0; j--)
			{
				if (nums[i] % nums[j] == 0 && T[i] < 1 + T[j])
				{
					T[i] = 1 + T[j];
					parent[i] = j;

					if (T[i] > m)
					{
						m = T[i];
						mi = i;
					}
				}

			}
		}

		vector<int> ret;
		for (int i = 0; i < m; i++)
		{
			ret.push_back(nums[mi]);
			mi = parent[mi];
		}

		return ret;
	}
};


int main(int argc, char**argv)
{
	Solution test;
	SolutionB testb;

	vector<int> num{ 3,2,1,5,6,4,18,54,55,108 };
	vector<int> res;
	res= test.largestDivisibleSubset(num);

	for (int i = 0; i < res.size(); i++)
		cout << res[i] << ",";
	cout << endl;

	res = testb.largestDivisibleSubset(num);

	for (int i = 0; i < res.size(); i++)
		cout << res[i] << ",";
	cout << endl;

	char aa;
	cin >> aa;
	return 0;
}
