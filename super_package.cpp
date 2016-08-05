#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;
typedef pair<ll, ll> pp;

vector<pp>  ps;
const int MAX_N=40;
const ll INF = (1<<63);
ll w[MAX_N], v[MAX_N];

int n;

void init()
{
	pp.resize(1 << (MAX_N/2));
	
}


void solve() 
{
	int n2 = n/2;
	
	for (int ii=0; ii < 1<<n2; ii++) {
		ll sw=0; 
		ll sv=0;
		for(int jj=0; jj <n2; jj++) {
			if(ii>>jj &1) {
				sw += w[jj];
				sv += v[jj];
			}
		}
		ps[ii] = make_pair(sw,sv);
	}
	
	sort(ps, ps+ (1<< n2));
	
	int m =1;
	for(int ii=1; ii < 1<<n2; ii++) {
		if(ps[m-1].second < ps[ii].second)
			ps[m++] = ps[ii];
	}
	
	ll res =0;
	
	for(int ii=0; ii < 1<<(n-n2); ii++) {
		ll sw=0,sv=0;
		for(int jj=0;jj < n-n2; jj++) {
			if(ii >> jj &1) {
				sw+= w[n2+jj];
				sv+= v[n2+jj];
			}
		}
		
		if(sw <=W) {
			ll tv = (low_bound(ps, ps+m, make_pair(W-sw,INF)) -1)->second;
			res = max(res, sv+tv);
		}
	}
	
	cout << res << endl;
		
}


int main(int argc, char ** argv)
{

	init();
	solve();
	return 0;

}