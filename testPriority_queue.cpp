
#include <iostream>       // std::cout
#include <queue>          // std::priority_queue
#include <functional>
#include <vector>

int main ()
{
	std::priority_queue<int, std::vector<int>, std::greater<int> > mypq;

	mypq.push(30);
	mypq.push(100);
	mypq.push(25);
	mypq.push(40);

	std::cout << "Popping out elements...";
	while (!mypq.empty())
	{
		std::cout << ' ' << mypq.top();
		mypq.pop();
	}
	std::cout << '\n';

	return 0;
}


