#include <iostream>

using namespace std;

const int MAX_N=100;
const int MAX_W=100;

int n,W;

int w[MAX_N],v[MAX_N];
int dp[MAX_N+1][MAX_W+1];

void init()
{
	/*cout << "Input n:";
	cin >> n;
	cout << endl;
	cout << "Array of w, v:" << endl;
	for(int ii=0; ii<n; ii++)
		cin >> w[ii]>>v[ii];
	cout << "Max weight W:";
	cin >> W;*/
	
	n = 4;
	W = 5;
	w[0] = 2;
	w[1] = 1;
	w[2] = 3;
	w[3] = 2;
	
	v[0] = 3;
	v[1] = 2;
	v[2] = 4;
	v[3] = 2;
	
}

void compute_dp()
{
	for(int jj=0;jj<n;jj++)
		dp[n][jj] = 0;
	for(int ii=n-1; ii>=0; ii--)
		for(int jj=0; jj<=W; jj++)
			if(jj < w[ii])
				dp[ii][jj] = dp[ii+1][jj];
			else
				dp[ii][jj] = max(dp[ii+1][jj],dp[ii+1][jj-w[ii]]+v[ii]);
}

void compute_dp1(){
	for(int jj = 0; jj < n; jj++)
		dp[0][jj] = 0;
	for (int ii = 0 ; ii < n; ii++)
		for (int jj = 0; jj <= W; jj++)
		{
			if (jj < w[ii])
				dp[ii+1][jj] = dp[ii][jj];
			else
				dp[ii+1][jj] = max(dp[ii][jj], dp[ii][jj-w[ii]]+v[ii]);
				
		}
}

void compute_dp_full_package()
{
	for(int j=0; j<n; j++)
}

int rec(int i, int j)
{
	int res;
	if(i == n)
		return 0;
	else if(j < w[i])
		rec(i+1,j);
	else 
		res = max(rec(i+1,j),rec(i+1,j-w[i])+v[i]);
	return res;
}




int main(int argc, char ** argv)
{
	init();
	compute_dp1();
	
	cout<< dp[n][W];
	//rec(0,W);

	return 0;

}