#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//求字符串的长度
int strLength(char* str){
	int count=0;
	char* p=str;
	while(*p++){
		count++;
	}
	return count;
}
int main(int argc, char **argv) {
	char* str=(char*)malloc(sizeof(char)*Max);
	scanf("%s",str);
	printf("%d %d\n",strLength(str),strlen(str));
	return 0;
}
