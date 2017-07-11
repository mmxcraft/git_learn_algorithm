#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int n;
const int MAX = 100;
int a[MAX];


void init()
{
	cout << "Input n:";
	cin >> n;
	cout << endl;
	
	for (int ii = 0; ii < n; ii++)
		cin >> a[ii];
	
}

int resolve()
{
	//vector<int> aa(a, a+n);
	
	sort(a, a+n, greater<int>());
	//sort(aa.begin(),aa.end());	
	
	for (int ii = 0 ; ii < n-2; ii++){
		if (a[ii] < a[ii+1]+a[ii+2])
			return a[ii]+a[ii+1]+a[ii+2];
	}
	return 0;
}

int main(int argc, char** argv)
{
	
	init();
	
	/*n = 5;
	a[0] = 4;
	a[1] = 2;
	a[2] = 3;
	a[3] = 10;
	a[4] = 5;*/
	
	cout << "Max_len = " << resolve() << endl;
	
}
