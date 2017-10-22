#include <iostream>
#include <stdio.h>
#include <string.h>
/*POJ 1182 */

using namespace std;

const int MAX_N = 50100;
const int MAX_K = 120000;

int par[MAX_N*3];
int rank[MAX_N*3];
int T[MAX_K], X[MAX_K], Y[MAX_K];

int N, K;

void init() {
	cin >> N;
	cin >> K;
	for (int i = 0; i < K; i++) {
		cin >> T[i];
		cin >> X[i];
		cin >> Y[i];
	}
	for ( int i = 0; i < 3*N; i++) 
		par[i] = i;

	memset(rank, 0, sizeof(rank));
}

int find(int x)
{
	if (par[x] == x)
		return x;
	else
		return par[x] = find(par[x]);
}

void unite(int x, int y)
{
	x = find(x);
	y = find(y);
	if (x == y) return;
	if (rank[x] < rank[y])
		par[x] = y;
	else {
		par[y] = x;
		if (rank[x] == rank[y]) rank[x]++;
	}
		
}

bool same(int x, int y)
{
	return find(x) == find(y);
}

void solve(){

	init();

	int ans = 0;
	for (int i = 0; i < K; i++) {
		int t = T[i];
		int x = X[i] - 1; 
		int y = Y[i] - 1;

		if ( x < 0 || x >= N || y < 0 || y >= N) {
			ans++;
			//cout << "t=" << t << ", x="<<x+1<<", y="<<y+1<<endl;
			continue;
		}


		if (t  == 1) {
			if (x == y)
				continue;
			if (same(x, y+N) || same(x, y+2*N)) {
					//cout << "t=" << t << ", x="<<x+1<<", y="<<y+1<<endl;
					ans++;
			}
			else {
				unite(x,y);
				unite(x + N, y + N);
				unite(x + 2*N, y + 2 * N);
			}
		} else if (t == 2) {
			if (same(x, y) || same (x, y+N)){
				//cout << "t=" << t << ", x="<<x+1<<", y="<<y+1<<endl;
				ans++;
			}
			else {
				unite(x + N, y);
				unite(x + 2*N, y+N);
				unite(x, y+ 2*N);
			}

		}
	}

	cout << ans << endl;

}


int main(int argc, char **argv)
{
	solve();
}
