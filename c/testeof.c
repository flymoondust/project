#include <stdio.h>
int main(){
	int a,b,c;
	while((c=scanf("%d %d",&a,&b))==2){
		printf("%d\n",a+b);
	}
	printf("scanf return %d EOF=%d\n",c,EOF);
	return 0;
}
