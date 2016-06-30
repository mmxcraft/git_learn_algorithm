#include <stdio.h>
#include <string.h>
#include <algorithm>
#include <iostream>

const int MAX_M=16;

int M, X;
double P;
double dp[2][(1 << MAX_M) + 1];

void solve() {
	int n = 1 << M;
	double *prv = dp[0], *nxt = dp[1];
	memset(prv, 0, sizeof(double) * (n + 1));
	prv[n] = 1.0;
	for (int r = 0; r < M; r++) {
		for (int i = 0; i <= n; i++) {
			int jub = std::min(i, n - i);
			double t = 0.0;
			for (int j = 0; j <= jub; j++) {
				t = std::max(t, P * prv[i + j] + (1 - P) * prv[i - j]);
			}
			nxt[i] = t;
		}
		std::swap(prv, nxt);
	}
	int i = (long long)X * n / 1000;
	printf("%.6f\n", prv[i]);
}

int main (int argc, char** argv)
{
	std::cout<< "Input M, X:";
	std::cin>>M>>X;
	P=0.4;

	solve();

}


