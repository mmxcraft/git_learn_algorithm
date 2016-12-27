#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <string.h>


using namespace std;

int W, H, N;
const int MAX_N=500;

int X1[MAX_N] = {1, 1, 4, 9,10};
int X2[MAX_N] = {6, 10,4, 9, 10};
int Y1[MAX_N] = {4, 8, 1, 1, 6};
int Y2[MAX_N] = {4, 8, 10, 5, 10};

bool fld[MAX_N*6][MAX_N*6];

int dx[4] = {-1,0,1,0};
int dy[4] = {0,-1,0,1};


void ex_init()
{
	W = 10;
	H = 10;
	N = 5;

}

int compress(int *x1, int *x2, int w)
{
	vector<int> xs;
	for(int ii=0; ii<N; ii++)
		for(int d=-1; d<=1; d++) {
			int tx1=x1[ii]+d, tx2=x2[ii]+d;
			if(1<= tx1 && tx1<=w) xs.push_back(tx1);
			if(1<= tx2 && tx2<=w) xs.push_back(tx2);
		}
	sort(xs.begin(),xs.end());
	xs.erase(unique(xs.begin(),xs.end()),xs.end());

	for(int ii=0; ii<N; ii++) {
		x1[ii]= find(xs.begin(),xs.end(), x1[ii]) -xs.begin();
		x2[ii]= find(xs.begin(),xs.end(), x2[ii]) -xs.begin();
	}

	return xs.size();
}

void solve()
{
	W = compress(X1, X2, W);
	H = compress(Y1, Y2, H);

	memset(fld, 0, sizeof(fld));

	for(int ii=0; ii<N; ii++)
		for(int y = Y1[ii]; y <= Y2[ii]; y++)
			for(int x = X1[ii]; x<= X2[ii]; x++)
				fld[y][x] = true;
	int ans = 0;

	for(int y=0; y<H; y++)
		for(int x=0; x<W; x++) {
			if(fld[y][x]) continue;
			ans++;
			queue<pair<int, int> > que;
			que.push(make_pair(x,y));
			while(!que.empty()) {
				int sx=que.front().first, sy = que.front().second;
				que.pop();
				for(int ii=0; ii<4; ii++) {
					int tx=sx+dx[ii], ty=sy+dy[ii];
					if(tx<0 || tx>=W || ty<0 || ty >= H) continue;
					if(fld[ty][tx]) continue;
					que.push(make_pair(tx,ty));
					fld[ty][tx] = true;
				}	
			}
		}
	cout << ans << endl;

}
int main(int argc, char ** argv)
{
	ex_init();
	solve();

	return 0;

}
