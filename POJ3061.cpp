#include <iostream>
#include <algorithm>

using namespace std;

int n;
int s;
const int N=100000;

int a[N];

void init()
{
	cout << "Input n,s: ";
	cin >> n >> s;
	
	for (int ii=0; ii<n; ii++)
		cin >> a[ii];
}

void solve()
{
	int sum=0;
	int t=0;
	int min=n;
	int beg=0;
	
	for(int ii=0; ii<n; ii++)
	{
		sum += a[ii]; 
		t++;
		if(sum >=s ) {
			int sum1=sum;
			for(int jj=beg; jj<ii;jj++)	{
				sum1 -= a[jj];
				if(sum1 >=s) {
					t--;
					sum = sum1;
					beg++;
				}
				else 
					break;
			}
		if (t<min)
			min =t;
		} else
			continue;
		
	}
	
	if(t == n) 
		cout<< "0\n";
	else
		cout << min << endl;
	
}

void better_solve()
{
	int res=n;
	int beg=0,t=0,sum=0;
	for(;;)
	{
		while(t < n && sum <s )
			sum += a[t++];
		if(sum < s) break;
		res = min(res, t-beg);
		sum -= a[beg++];
	}
	
	if(res >= n)
		res =0;
	
	cout << "res" << endl;
	
}

int main(int argc, char** argv)
{
	init();
	solve();

	return 0;
}