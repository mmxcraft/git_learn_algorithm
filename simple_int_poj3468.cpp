#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;
typedef long long ll;

#define lson l,m,rt<<1
#define rson m+1,r, rt<<1|1 

const int maxn = 100000;
ll sum[maxn<<2];
ll add[maxn<<2];
int test[10] = {5,3,7,9,6,4,1,2,0,0};
int pos=0;

void PushUp(int rt)
{
	sum[rt] = sum[rt<<1] + sum[rt<<1|1];
}

void PushDown(int rt, int m)
{
	if(add[rt]) {
		add[rt<<1] += add[rt];
		add[rt<<1|1] += add[rt];
		sum[rt<<1] += add[rt] * (m - (m>>1));
		sum[rt<<1|1] += add[rt] * (m>>1);
		add[rt] = 0;
	}
	
}

void build(int l, int r, int rt)
{
	add[rt] = 0;
	if(l == r) {
		//cin>> sum[rt];
		sum[rt] = test[pos++];
		return;
	}
	
	int m = (l+r) >> 1;
	build(lson);
	build(rson);
	PushUp(rt);
}

void update(int L, int R, int c, int l, int r, int rt)
{
	if(L <=l && r <=R) {
		add[rt] += c;
		sum[rt] += (ll)c *(r-l+1);
		return;
	}
	
	PushDown(rt, r-l+1);
	int m =(l+r)>>1;
	if(L<=m) 	update(L,R, c, lson);
	if(m<R)	update(L,R,c, rson);
	PushUp(rt);
}

int query(int L, int R, int l, int r, int rt)
{
	if(L<=l && r <= R)
		return sum[rt];
	PushDown(rt, r-l+1);
	int m =(l+r) >> 1;
	int ret =0;
	if(L <= m ) ret+= query(L,R, lson);
	if(R >m) ret += query(L,R,rson);
	return ret;
}

void output()
{
	int ii=1;
	while(sum[ii] !=0) {
		cout<<"sum[" <<ii << "]=" << sum[ii] <<", add[" << ii<< "]=" << add[ii] << endl;
		ii++;
	}
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
	
	update(1,5,1, 1,8,1);
	output();
	update(4,6,2, 1,8,1);
	output();
	cout<< "sum for 1-4 is:" << query(1,4,1,8,1)<<endl;
	output();
	cout<< "sum for 6-8 is:" << query(6,8,1,8,1)<<endl;
	output();
}


int main(int argc, char ** argv)
{
	init();
	return 0;

}

