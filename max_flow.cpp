#include <iostream>
#include <vector>

using namespace std;

const int MAX_V= 50000;
const int MAX_DIS = 99999999;

struct edge {
	int to;
	int cap;
	int rev;
};

vector<edge> G[MAX_V];
bool used[MAX_V];

void add_edge(int from, int to, int cap)
{
	G[from].push_back((edge){to,cap,G[to].size()});
	G[to].push_back((edge){from,0,G[from].size()-1});
}

int dfs(int v, int t, int f)
{
	if(v==t) return f;
	used[v] = true;
	for(int i=0; i < G[v].size();i++)
	{
		edge &e  = G[v][i];
		if(!used[e.to] && e.cap>0) {
			int d = dfs(e.to, t, min(f,e.cap));
			if(d > 0) {
				e.cap -= d;
				G[e.to][e.rev].cap  += d;
				return d;
			}
		}
	}
	
	return 0;
}

int max_flow(int s, int t) {
	int flow = 0;
	for(;;) {
		memset(used,0, sizeof(used));
		if f = dfs(s,t, MAX_DIS);
		if (f == 0) return flow;
		flow += f;
	}
	
}

int main(int argc, char ** argv)
{
	cout << "input n m ";
	
	int n,m;
	
	cin >> n>>m;
	
	int ii;
	int from,to, cap;
	for(ii=0; ii<m;ii++) {
		cin >> from>>to>>cap;
		add_edge(from,to,cap);
	}
	
	cout << max_flow(1,4);
	return 0;

}
