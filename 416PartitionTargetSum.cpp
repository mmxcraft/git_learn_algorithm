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
	bool canPartition(vector<int>& nums) {
		int sum = 0;
		for (int num  : nums)
			sum += num;
		if ((sum & 1) != 0) return false;

		sum = sum / 2;

				
		return true;
	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<int> numList{ 1,3,4,5 };

	cout << test.canPartition(numList) << endl;

	char aa;
	cin >> aa;
	return 0;
}
