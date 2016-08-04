#include <iostream>
#include <algorithm>

using namespace std;

int N;

const int MAX_N=3000;

int A[MAX_N],B[MAX_N],C[MAX_N],D[MAX_N];

int CD[MAX_N*MAX_N];


void init()
{
	cout << "input N:";
	cin >> N;
	
	for(int jj=0; jj<N;jj++)
		cin>>A[jj];
	for(int jj=0; jj<N;jj++)
		cin>>B[jj];
	for(int jj=0; jj<N;jj++)
		cin>>C[jj];
	for(int jj=0; jj<N;jj++)
		cin>>D[jj];

}

void solve()
{
	for (int ii=0; ii<N;ii++)
		for(int jj=0;jj<N;jj++)
			CD[ii*N+jj] = C[ii] +D[jj];
	
	sort(CD,CD+N*N);
	
	long long res =0;
	for(int ii=0; ii<N; ii++)
		for(int jj=0; jj<N; jj++) {
			int cd = -(A[ii]+B[jj]);
			res += upper_bound(CD,CD+N*N,cd) -lower_bound(CD,CD+N*N,cd);
		}
			
	cout << "Total num =" << res;
}


int main(int argc, char **argv) 
{
	init();
	solve();
	
	return 0;
}