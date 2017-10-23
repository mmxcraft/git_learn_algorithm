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

	}
};

int main(int argc, char**argv)
{
	Solution test;
	vector<pair<int, int>>  nums{ { 1,17 },{ 1,10 },{ 2,15 },{ 2,5 },{ 3,8 },{ 4,10 } };
	vector<pair<int, int>>  nums1{ { 1, 2 },{ 3, 4 },{ 5, 6 },{ 7, 8 } };
	vector<pair<int, int>> nums2{ { 1,7 },{ 4,9 },{ 2,5 } };


	cout << "maxEnvelopes(0) = " << test.maxEnvelopes(nums) << endl;
	cout << "maxEnvelopes(1) = " << test.maxEnvelopes(nums1) << endl;
	cout << "maxEnvelopes(2) = " << test.maxEnvelopes(nums2) << endl;



	char aa;
	cin >> aa;
	return 0;
}
