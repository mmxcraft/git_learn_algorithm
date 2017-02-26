#include <iostream>
using namespace std;
int g[101][101], match[101],vis[101], n, m;

int dfs(int u){
	int i;
	for (i=1; i<=n; i++){
		if (vis[i]==0 && g[u][i]==1){
			vis[i]=1;  //标记点i已访问过
			//如果点i未配对或找到了新的配对 
			if (match[i]==0 || dfs(match[i])){ 
				match[i] = u; match[u] = i;  //更新配对关系
				return 1;
			} 
		}
	}
	return 0;
}

void init()
{
	int i, t1,t2;
	cin >> n >> m;  //n个点m条边
	for (i=1; i<=m; i++){  //读入边
		cin >> t1 >> t2;
		g[t1][t2]=1; g[t2][t1]=1;  //这里是无向图 
	}
}

void test_init()
{
	n=6;
	m=5;
	g[1][4]=1; g[4][1]=1;
	g[1][5]=1; g[5][1]=1;
	g[2][4]=1; g[4][2]=1;
	g[2][6]=1; g[6][2]=1;
	g[3][4]=1; g[4][3]=1;
}
int main(){
	
	test_init();
	int i, j,sum=0;
	for (i=1; i<=n; i++) match[i]=0;
	for (i=1; i<=n; i++){
		for(j=1; j<=n; j++) vis[j]=0;   //清空上次搜索时的标记
		if (dfs(i)) sum++;  //寻找增广路，如果找到，配对数加1 
	}
	cout << sum; 
	return 0;
}


