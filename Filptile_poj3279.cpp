#include <iostream>

using namespace std;

const int MAX_M=100;
const int MAX_N=100;

int M,N;
int tile[MAX_M][MAX_N];
int filp[MAX_M][MAX_N];

int opt[MAX_M][MAX_N];

const int dx[5]={-1,0,0,0,1};
const int dy[5]={0,-1,0,1,0};


void init(){
	cout << "Input M, N: ";
	cin >> M >> N;
	for (int ii=0; ii<M; ii++)
		for(int jj=0; jj<N; jj++)
			cin >>tile[ii][jj];
}



int get (int x, int y) 
{
	
	int c = tile[x][y];
	for (int ii=0; ii<5;ii++) {
		int x2=x+dx[ii];
		int y2=y+dy[ii];
		if(0<=x2 && x2<M && 0<=y2 && y2<N) {
			c+= flip[x2][y2];
		}
		
	}
	
	return c%2;
}

int  calc()
{
	for(int ii=1; ii<M; ii++) {
		for(int jj=0; jj<N;jj++) {
			if(get(i-1,j) !=0)
				filp[i][j]=1;
		}
	}
	
	for(int jj=0; jj< N; jj++) {
		if(get(M-1,j) !=0) 
			return -1;
	}
	
	int res=0;
	for(int ii=0;ii<M;ii++)
		for(int jj=0; jj<N; jj++)
			res += flip[ii][jj];
	return res;
}


void solve()
{
	
	int res=-1;
	
	for(int ii=0; ii< 1<< N; ii++) {
		memset(flip,0,sizeof(filp));
		for(int jj=0; jj < N; jj++) {
			flip[0][N-jj-1] = ii>>jj &1;
		}
		
		int num = calc();
		if(num >= 0 && (res <0 || res> num)) {
			res =num;
			memcpy(opt,flip,sizeof(flip));
		}
	}
	
	if(res<0)
		cout << "IMPOSSIBLE\n";
	else
		for(int ii=0;ii<M;ii++)
			for(int jj=0;jj<N;jj++) {
				cout << opt[ii][jj];
				if(jj == N-1)
					cout << "\n";
			}
}


int main(int argc, char**argv) 
{
	init();
	solve();
	return 0;
}




