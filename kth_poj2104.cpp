#include <iostream>
#include <vector>
#include <stdio.h>
#include <string.h>

using namespace std;

#define lson l,m,rt<<1
#define rson m+1,r, rt<<1|1 

const int maxn = 100000;
vector<int> dat[maxn];



void PushUp(int rt)
{
	
	data[rt] = data[rt].push_back(data[rt>>1]);
	data[rt] = data[rt].push_back(data[rt>>1|1);
	sort(data,data.begin(),data.end());
}

void build(int l, int r, int rt)
{
	if(l == r) {
		cin>> sum[rt];
		return;
	}
	
	int m = (l+r) >> 1;
	build(lson);
	build(rson);
	PushUp(rt);
}

void update(int p, int add, int l, int r, int rt)
{
	if(l == r) {
		sum[rt] += add;
		return;
	}
	
	int m =(l+r)>>1;
	if(p<=m) 
		update(p,add, lson);
	else 
		update(p,add,rson);
	PushUp(rt);
}

int query(int L, int R, int, k, int l, int r, int rt)
{
	if(L<=l && r <= R)
		return dat[k];
	int m =(l+r) >> 1;
	int ret =0;
	if(L <= m ) ret+= query(L,R, lson);
	if(R >m) ret += query(L,R,rson);
	return ret;
}

void init()
{	
	memset(sum,0,sizeof(sum));
	build(1,8,1);
	int ii = 1;
	while(sum[ii] !=0) {
		cout<<"sum[" <<ii << "]=" << sum[ii] << endl;
		ii++;
		
	}
}


int main(int argc, char ** argv)
{
	init();
	return 0;

}