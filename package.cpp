#include <iostream>

using namespace std;

const int MAX_N=100;
const int MAX_W=100;

int n,w;

int w[MAX_N],v[MAX_N];
int dp[MAX_N+1][MAX_W+1];

void init()
{
	cout << "Input n:";
	cin >> n;
	cout << endl;
	cout << "Array of w, v:" << endl;
	for(int ii=0; ii<n; ii++)
		cin >> w[ii]>>v[ii];
	cout << "Max weight w:";
	cin >> w;
	
}

void compute_dp()
{
	for(int jj=0;jj<n;jj++)
		dp[n][jj] = 0;
	for(int ii=n-1; ii>=0; ii--)
		for(int jj=0; jj<=w; jj++)
			if(jj < w[ii])
				dp[ii][jj] = dp[ii+1][jj];
			else
				dp[ii][jj] = max(dp[ii+1][jj],dp[ii+1][jj-w[ii]]+v[ii]);
}

int rec(int i, int j)
{
	if(i == n)
		return 0;
	else if(j < w[i])
		res(i+1,j)
	else 
		res = max(res(i+1,j),res(i+1,j-w[i])+v[i]);
	return res;
}




int main(int argc, char ** argv)
{
	init();
	rec(0,w);

	return 0;

}