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

int pickNum = 0;

int guess(int num) {
	if (num < pickNum)
		return -1;
	else if (num > pickNum)
		return 1;
	else
		return 0;
}

class Solution {
public:
	int guessNumber(int n) {
		return guessN(1, n);
	}

	int guessN(int low, int high)
	{
		if (low >= high) return low;
		int mid = low + (high-low) / 2;
		if (guess(mid) == 0) return mid;
		else if (guess(mid) == -1) return guessN(mid + 1, high);
		else return guessN(low, mid - 1);
	}
};

int main(int argc, char**argv)
{
	Solution test;
	pickNum = 1702766719;

	cout << "guessNumber(10) = " << test.guessNumber(2126753390) << endl;
	cout << "guessNumber(11) = " << test.guessNumber(2126753290) << endl;
	cout << "guessNumber(12) = " << test.guessNumber(2126753490) << endl;

	char aa;
	cin >> aa;
	return 0;
}
