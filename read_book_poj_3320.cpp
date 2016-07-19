#include <iostream>
#include <map>


using namespace std;
const int max_page=100000;

int page;
int point;
int page_point[max_page];

map<int,int> kp_map;

void init()
{
	cout << "page_num= ";
	cin >> page;
	for(int ii=0; ii<page;ii++){
		cin>>page_point[ii];
		kp_map[page_point[ii]]=0;
	}
	
	point = kp_map.size();
	
}

void solve()
{
	int ii,jj;
	int min_page=page;
	int total_point=0;

	for(ii=0,jj=0; ii<page; ii++) {
		if(kp_map[page_point[ii]] == 0) 
			total_point++;
		kp_map[page_point[ii]]++;
		
		if(total_point == point) {
			if(min_page > ii-jj+1)
				min_page = ii-jj+1;
			kp_map[page_point[jj]]--;
			if(kp_map[page_point[jj]] == 0)
				total_point--;
			jj++;
		}
	}
	
	cout <<"min page=" << min_page << endl;
}

int main(int argc, char**argv)
{
	init();
	solve();
	return 0;
}
