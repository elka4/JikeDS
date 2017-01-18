#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//字符串转大写
void strUper(char* s){
	char* p=s;
	while(*p){
		if(*p>='a'&&*p<='z'){
			*p-=32;
		}
		p++;
	}
}
int main(int argc, char **argv) {
	char* s=(char*)malloc(Max*sizeof(char));
	gets(s);
	strUper(s);
	printf("%s\n",s);
	free(s);
	s=(char*)malloc(Max*sizeof(char));
	gets(s);
	strupr(s);
	printf("%s\n",s);
	free(s);
	return 0;
}
