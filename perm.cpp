// testC.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <stdio.h>

char str[]="abcd";
char prt_str[100]={0};
int flag[100]={0};

int perm(int s, int n);


int _tmain(int argc, _TCHAR* argv[])
{
	perm(0,4);
	getchar();
	return 0;
}

int perm(int s, int n)
{
	int ii;
	if(s>n)
		return 0;
	if(s == n){
		for(ii=0; ii< n; ii++)
		{
			printf("%c",prt_str[ii]);
		}
		printf("  ");
		return 0;
	}

	for(ii=0; ii<n;ii++)
	{
		if(flag[ii] == 0)
		{
			prt_str[s] = str[ii];
			flag[ii] = 1;
			perm(s+1, n);
			flag[ii]=0;
		}
	}
	return 0;
}
