

using namespace std;

class Solution {
public:
	static bool mysort(vector<int> &p1, vector<int> &p2) {
		return p1[1] < p2[1];
	}
    int findLongestChain(vector<vector<int> >& pairs) {

		sort(pairs.begin(), pairs.end(),mysort);

		int cnt = 1;
		for (int i = 1; i < pairs.size(); i++)
		{
			if (pairs[i][0] > pairs[i - 1][1])
				cnt++;
		}
		return cnt;
    }
}; 

int main(int argc,char **argv)
{
	Solution s;
	vector<vector<int> > test;
	vector<int> tmp,tmp1,tmp2;
	tmp.push_back(3);
	tmp.push_back(4);
	test.push_back(tmp);
	
	tmp1.push_back(2);
	tmp1.push_back(3);
	test.push_back(tmp1);
	
	tmp2.push_back(1);
	tmp2.push_back(2);
	test.push_back(tmp2);
	

	cout<<s.findLongestChain(test)<<endl;
	
	
	
}