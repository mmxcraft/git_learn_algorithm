#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;

const int MAX_N = 1000;
bool used[MAX_N];
char str[MAX_N];
char rst[MAX_N];

void arrange(int pos, int n)
{
	if (pos == n)
	{
		cout << rst << endl;
	}
	
	for (int ii = 0; ii < n; ii++)
	{
		if (!used[ii]) {
			rst[pos] = str[ii];
			used[ii] = true;
			arrange(pos+1, n);
			used[ii] = false;
		}
	}
}

int main(int argc, char **argv)
{
	cout <<" input the string:";
	cin >> str;
	
	int len = strlen(str);
	for (int ii = 0; ii < len; ii++)
		used[ii]=false;
	
	arrange(0,len);
}