#include <iostream>

using namespace std;

const int MAX_N = 100;

int a[MAX_N][MAX_N];

int level =0;

int pos[MAX_N];

void init()
{

  int ii,jj;

  cout << "input matrix level:";
  cin >> level;

  for(ii=0; ii<level; ii++)
  	pos[ii] = -1;


  for(ii=0; ii<level; ii++)
  	for(jj=0; jj < level;jj++) {
		cin >> a[ii][jj];
		if(a[ii][jj] !=0) 
			pos[ii] = jj;
	}
  
  return;
}

void solve()
{
	int ii,jj,kk;
	int cnt=0;

	for(ii=0; ii<level; ii++)
	{
	
		for(jj=ii; jj<level; jj++)
			if(pos[jj] <= ii)
				break;
		
		for(kk=jj; kk>ii; kk--)
		{
			swap(pos[kk],pos[kk-1]);
			cnt++;
		}
			
	}

	cout<<"Toltal move need " << cnt << " times\n";
}


int main(int argc, char** argv)
{

	init();
	solve();

	return 0;

}

