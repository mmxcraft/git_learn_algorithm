#include <iostream>
#include <memory.h>
#include <string.h>

using namespace std;

int N;
const int MAX_COW=100000;
int dir[MAX_COW];
int temp_dir[MAX_COW];
char dir_cow[MAX_COW];
int f[MAX_COW];


void init()
{
	cout << "input N: ";
	cin >> N;
	cout << "intput Cow direction:";
	cin >> dir_cow;
	memset(dir,0,sizeof(dir));
	for(int ii=0; ii<N; ii++) {
		if(dir_cow[ii] == 'B')
			dir[ii]=1;
	}
}

int calc(int K)
{
	int ii,jj,kk;
	int res = 0;
	int sum = 0;
	memset(f,0, sizeof(f));
	for(ii=0; ii+K <=N; ii++) {
		if((dir[ii]+sum) % 2 != 0) {
			res++;
			f[ii]=1;
			sum++;
		}
		if(ii-K+1 >= 0) 
			sum -= f[ii-K+1];
		
	}
	
	for(;ii<N;ii++) {
		if((dir[ii]+sum)%2 !=0 )
			return -1;
		if(ii-K+1 >=0)
			sum -= f[ii-K+1];
	}
	
	return res;
}

int slow_calc(int K)
{
	int ii,jj;
	int res=0;
	
	memcpy(temp_dir,dir, sizeof(dir));
	
	for(ii=0; ii+K <=N; ii++) {
		if(temp_dir[ii] == 1) {
			res++;
			for(jj=ii+1; jj<ii+K; jj++)
				temp_dir[jj] = (temp_dir[jj] +1) %2; 
		}
	}
	
	for(;ii<N;ii++) {
		if(temp_dir[ii] == 1)
			return -1;
	}
	
	return res;
	
}

void solve()
{
	int ii;
	int min_step=N;
	int min_K = 1;
	int res;
	for(ii =1; ii <=N; ii++) {
		//res = calc(ii);
		res = slow_calc(ii);
		if(res < min_step && res > 0) {
			min_step = res;
			min_K = ii;
		}
	}
	
	if(min_step > 0) 
		cout << "M="<< min_step << ", K=" << min_K << endl;
	else
		cout << "No resolution for this pattern" << endl;
}


int main(int argc, char **argv)
{
	init();
	solve();

	return 0;
	
}