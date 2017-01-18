#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//字符串的连接
void strCat(char* dest,char* src){
	int len=strlen(dest);
	char* p=dest+len;
	strcpy(p,src);
}
int main(int argc, char **argv) {
	char* dest=(char*)malloc(Max*sizeof(char));
	char* src=(char*)malloc(Max*sizeof(char));
	gets(dest);
	gets(src);
	strCat(dest,src);
	printf("%s\n",dest);
	free(dest);
	free(src);
	dest=(char*)malloc(Max*sizeof(char));
	src=(char*)malloc(Max*sizeof(char));
	gets(dest);
	gets(src);
	strcat(dest,src);
	printf("%s\n",dest);
	free(dest);
	free(src);
	return 0;
}
