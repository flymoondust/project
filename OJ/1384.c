#include<stdio.h>
#include<stdlib.h>
int main(void){
	int i,j,m,n,input,flag=0;
	//int flag1=0;
	//static int *matrix;
	//int *tmp;
	int tmp;
	while(scanf("%d %d",&m,&n)==2){
		if(scanf("%d",&input)==1){
//			matrix=(int *)malloc(m*n*sizeof(int));
			//printf("matrix addr %x",matrix);
			for(i=0;i<m;i++){
				for(j=0;j<n;j++){
					//if(scanf("%d",matrix+i*n+j)==1){
					if(scanf("%d",&tmp)==1){
						if(tmp==input)
						{
							flag=1;
						}
					}
				}
			}
			if(flag)
				printf("Yes\n");
			else
				printf("No\n");
			flag=0;
			//free(matrix);
		}
//		if(flag1==0)
//			tmp=matrix;
//		flag1=1;
//		printf("matrix[0][0] %d",*tmp);
	}
	return 0;
}
