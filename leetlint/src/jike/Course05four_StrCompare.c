#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//比较两个字符串的大小
int strCompare(char* s1,char* s2){
	char* p=s1;
	char* q=s2;
	while(*p==*q&&*p&&*q){
		p++;
		q++;
	}
	if(*p-*q==0){
		return 0;
	}else{
		return *p>*q?1:-1;
	}
}
int main(int argc, char **argv) {
	char* s1=(char*)malloc(Max*sizeof(char));
	char* s2=(char*)malloc(Max*sizeof(char));
	gets(s1);
	gets(s2);
    printf("%d\n",strCompare(s1,s2));
    free(s1);
    free(s2);
    s1=(char*)malloc(Max*sizeof(char));
    s2=(char*)malloc(Max*sizeof(char));
    gets(s1);
    gets(s2);
    printf("%d\n",strcmp(s1,s2));
    free(s1);
    free(s2);
	return 0;
}
