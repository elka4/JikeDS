#include <cstdio>
#include <iostream>
using namespace std;
void fun() {
	int a = 0;
	int b = 1;
	int c = 2;
	printf("fun : %d %d %d\n", &a, &b, &c);
}
int main(int argc, char **argv) {
	int a = 0;
	int b = 1;
	int c = 2;
	printf("main : %d %d %d\n", &a, &b, &c);
	fun();
	return 0;
}
