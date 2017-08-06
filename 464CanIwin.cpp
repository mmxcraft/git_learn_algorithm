#include <iostream>
#include <map>
#include <bitset>
#include <stdio.h>

using namespace std;

struct myComparer {
	bool operator() (const bitset<32> &b1, const bitset<32> &b2) const {
		return b1.to_ulong() < b2.to_ulong();
	}
};

class Solution {
public:
	bool canIWin(int maxChoosableInteger, int desiredTotal) {
		map<bitset<32>, bool, myComparer> poolUsage;
		bitset<32> pool(0);
		return canWin(pool, maxChoosableInteger, desiredTotal, poolUsage);
	}

	bool canWin(bitset<32> &pool, int maxInt, int tot, map<bitset<32>, bool, myComparer> &poolUsage ) {
		if (tot <= 0) 
			return false;
		map<bitset<32>, bool, myComparer>::iterator it;
		it = poolUsage.find(pool);
		if (it != poolUsage.end()) 
			return it->second;
		for (int i = 0; i < maxInt; i++) {
			if (pool[i]) continue;
			pool[i] = true;
			if (!canWin(pool, maxInt, tot - i - 1, poolUsage)) {
				pool[i] = false;
				poolUsage[pool] = true;
				return true;
			}
			pool[i] = false;
		}
		poolUsage[pool] = false;
		return false;
	}
};


class SolutionB {
public:
	bool canIWin(int maxChoosableInteger, int desiredTotal) {
		bitset<32> pool(0);
		return canWin(pool, maxChoosableInteger, desiredTotal);
	}

	bool canWin(bitset<32> pool, int maxInt, int tot) {
		if (tot <= 0)
			return false;

		for (int i = 0; i < maxInt; i++) {
			if (pool[i]) continue;
			pool[i] = true;
			if (!canWin(pool, maxInt, tot - i - 1) || tot < i+1) {
				return true;
			}
			pool[i] = false;
		}
		return false;
	}
};

class SolutionC {
public:
	bool canIWin(int maxChoosableInteger, int desiredTotal) {
		if (!desiredTotal) return 1;
		return canWin(~0 << maxChoosableInteger, maxChoosableInteger, desiredTotal);
	}
	bool canWin(int pool, int maxint, int tot) {
		if (tot <= 0) return 0;
		for (int i = 0; i < maxint; i++) {
			int mask = 1 << i;
			if (pool & mask) continue;
			pool |= mask;
			if (!canWin(pool, maxint, tot - i - 1)) return 1;
			pool ^= mask;
		}
		return 0;
	}
};

int main(int argc, char** argv)
{

	int maxInt, tot;
	SolutionC testC;
	SolutionB testB;

	//cout << "Input Maxint and Total: ";
	//cin >> maxInt >> tot;
	maxInt = 3;
	tot = 4;
	//cout << "TestC: " << testC.canIWin(maxInt, tot) << endl;
	cout << "TestB: " << testB.canIWin(maxInt, tot) << endl;

	getchar();
	return 0;
}