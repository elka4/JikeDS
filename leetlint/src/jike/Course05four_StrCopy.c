#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//字符串的拷贝
void strCopy(char* dest,char* src){
	char* p=dest;
	char* q=src;
	while(*p++=*q++){

	}
}
int main(int argc, char **argv) {
	char* dest=(char*)malloc(Max*sizeof(char));
	char* src=(char*)malloc(Max*sizeof(char));
	gets(dest);
	gets(src);
	strCopy(dest,src);
	printf("%s\n",dest);
	free(dest);
	free(src);
	dest=(char*)malloc(Max*sizeof(char));
	src=(char*)malloc(Max*sizeof(char));
	gets(dest);
	gets(src);
	strcpy(dest,src);
	printf("%s\n",dest);
	free(dest);
	free(src);
	return 0;
}
