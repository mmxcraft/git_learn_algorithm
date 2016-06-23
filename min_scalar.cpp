#include <iostream>
#include <algorithm>

using namespace std;
const int MAX=1000;

int X[MAX], Y[MAX];

int main(int argc, char** argv)
{
  long long result;
  int num;
  cin >> num;
  for(int ii = 0; ii< num; ii++)
	cin >> X[ii];

  for(int ii=0; ii < num; ii++)
  	cin >> Y[ii];
 
  /*for(int ii=0; ii<num;ii++)
  {
  	cout<<"X["<<ii<<"]="<< X[ii] << ",";
  	cout<<"Y["<<ii<<"]="<< Y[ii] << "\n";

  }*/

  sort(X,X+num);
  sort(Y,Y+num);

  result = 0;

  for(int ii=0; ii<num; ii++)
  {
  	result = X[ii] * Y[num-ii-1] + result;
  }

  cout << "min Scalar is: " << result << endl;

  return 0;

}


