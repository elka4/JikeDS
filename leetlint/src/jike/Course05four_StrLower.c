#include <stdio.h>
#include <string.h>
#include <stdlib.h>
const int Max=32;
//字符串转小写
void strLower(char* s){
	char* p=s;
	while(*p){
		if(*p>='A'&&*p<='Z'){
			*p+=32;
		}
		p++;
	}
}
int main(int argc, char **argv) {
	char* s=(char*)malloc(Max*sizeof(char));
	gets(s);
	strLower(s);
	printf("%s\n",s);
	free(s);
	s=(char*)malloc(Max*sizeof(char));
	gets(s);
	strlwr(s);
	printf("%s\n",s);
	free(s);
	return 0;
}
