#include <iostream>
#include <vector>


using namespace std;

typedef long long ll;

vector <int> A;

void init()
{

	A.push_back(3);
	A.push_back(6);
	A.push_back(5);
	A.push_back(9);
	A.push_back(13);
	A.push_back(8);
	A.push_back(1);

}


ll merge_cnt(vector<int> &a)
{
	int n =a.size();
	if(n <=1) return 0;

	ll cnt =0;
	vector<int> b(a.begin(),a.begin()+n/2);
	vector<int> c(a.begin()+n/2,a.end());

	cnt += merge_cnt(b);
	cnt += merge_cnt(c);

	int ai=0, bi=0, ci=0;
	while(ai<n) {
		if(bi < b.size() && (ci == c.size() || b[bi] <= c[ci]))
			a[ai++] = b[bi++];
		else {
			cnt += n/2 - bi;
			a[ai++] = c[ci++];
		}

	}

	return cnt;
}

int main(int argc, char ** argv)
{
	init();
	cout << merge_cnt(A) << endl;
	
	return 0;

}
