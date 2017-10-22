#include <iostream>
#include <map>
#include <vector>
#include <algorithm> 

using namespace std;


class Solution {
public:
	int shoppingOffers(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
		int spSize = special.size();
		int itemNum = price.size();
		int minPrice = 0;
		for (int i = 0; i < itemNum; i++)
		{
			minPrice += needs[i] * price[i];
		}

		if (minPrice == 0)
			return 0;

		bool validOffer = true;
		for (int i = 0; i < spSize; i++) {
			bool validOffer = true;
			for (int j = 0; j < itemNum; j++)
			{
				if (special[i][j] > needs[j]) {
					validOffer = false;
					break;
				}
			}
			if (validOffer) {
				for (int j = 0; j < itemNum; j++) {
					needs[j] -= special[i][j];
				}
				minPrice = min(shoppingOffers(price, special, needs) + special[i][itemNum], minPrice);
				for (int j = 0; j < itemNum; j++) {
					needs[j] += special[i][j];
				}
			}
		}
		return minPrice;
	}
};

int main(int argc, char **argv)
{
	vector<int> P;
	vector<vector<int> > Offer(2, vector<int>(7,0));
	vector<int> needs;

	P.push_back(6);
	P.push_back(3);
	P.push_back(6);
	P.push_back(9);
	P.push_back(4);
	P.push_back(7);
	Offer[0][0] = 1;
	Offer[0][1] = 2;
	Offer[0][2] = 5;
	Offer[0][3] = 3;
	Offer[0][4] = 0;
	Offer[0][5] = 4;
	Offer[0][6] = 29;
	Offer[1][0] = 3;
	Offer[1][1] = 1;
	Offer[1][2] = 3;
	Offer[1][3] = 0;
	Offer[1][4] = 2;
	Offer[1][5] = 2;
	Offer[1][6] = 19;

	needs.push_back(4);
	needs.push_back(1);
	needs.push_back(5);
	needs.push_back(2);
	needs.push_back(2);
	needs.push_back(4);

	Solution test;
	cout << "minPrice =" << test.shoppingOffers(P, Offer, needs) << endl;
	char a;
	cin >> a;

	return 0;
}



