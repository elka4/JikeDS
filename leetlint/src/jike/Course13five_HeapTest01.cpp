#include <iostream>
#include <cstdio>
using namespace std;
int main(int argc, char **argv) {
	int* array1=new int[10];
	int* array2=new int[10];
	printf("%d %d\n",array1,array2);
	delete[] array1;
	int* array3=new int[5];
	printf("%d\n",array3);
	delete[] array2;
	delete[] array3;
	return 0;
}
