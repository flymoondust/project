#include <stdio.h>

static int count;
const int n=4;
int picktop(char cur[],char A){
	int i=0;
	for(;i<n;i++){
		if(cur[i]!=A)
			continue;
		else
			break;
	}
	return i;
}

void hanoi(char cur[],int n,char A,char B,char C){
	int i=-1;
	if(n==1){
		i=picktop(cur,A);
		cur[i]=C;
		printf("move %d disk %d : %c -> %c\n",++count,n,A,C);
		return;
	}
	hanoi(cur,n-1,A,C,B);
//	i=picktop(cur,A);
//	cur[i]=C;
//	printf("move %d disk %d : %c -> %c\n",++count,i+1,A,C);
	cur[n-1]=C;
	printf("move %d disk %d : %c -> %c\n",++count,n,A,C);
	hanoi(cur,n-1,B,A,C);
}
int main(){
	char cur[]="AAAA";
	printf("hanoi game is starting...\n");
	hanoi(cur,n,'A','B','C');	
	printf("hanoi game is over...\n");
	return 0;
}
