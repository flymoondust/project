#include <stdio.h>
char * getages(){
	//static int a[3]={1,2,3};
	char *name="wang xi";
	char name1[]="wang xi";
	return name;
}

void main(){
	int i=0;
	char *b;
	b=getages();
	printf("name=");
	while(b[i]!=0){
		printf("%c",b[i++]);
	}
	printf("\n");
}
