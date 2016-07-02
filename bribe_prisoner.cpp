#include <iostream>
#include <algorithm>

using namespace std;

const int MAX_Q=200;

int a[MAX_Q];
int dp[MAX_Q][MAX_Q];
int P, Q;


void init()
{

  cin>>P;
  cin>> Q;

  for (int ii = 1; ii <=Q; ii++)
  	cin >> a[ii];

  a[0]=0;
  a[Q+1] = P+1;

}


void solve()
{

	int ii,jj,kk;
	int ww;
	int tt;

	for(ii=0; ii<=Q; ii++)
		dp[ii][ii+1]=0;

	for(kk=2; kk<= Q+1; kk++){
		for(ii=0; ii+kk <= Q+1; ii++) {
			jj = ii +kk;
			tt = 999999;
			for(ww=ii+1; ww <jj; ww++)
				tt = min(tt, dp[ii][ww]+dp[ww][jj]);
			dp[ii][jj] = tt + a[jj] - a[ii] -2;
		}
	}

	cout << dp[0][Q+1] << endl;

}



int main(int argc, char **argv)
{

init();
solve();

return 0;

}
