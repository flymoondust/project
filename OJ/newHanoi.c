#include<stdio.h>

static int count;
const int length=5;

int picktop(char cur[],char A){
    int i=0;
    for(;i<length;i++){
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
	    //i=picktop(cur,A);
	    //cur[i]=C;
		cur[n-1]=C;
   		//printf("move %d disk %d : %c -> %c\n",++count,i+1,A,C);
   		printf("move %d disk %d : %c -> %c\n",++count,n,A,C);
	    hanoi(cur,n-1,B,A,C);
}

char locdif(char A,char B){
	char loc='A';
	while(loc == A || loc==B)
		loc++;
	printf("diff(%c %c)=%c\n",A,B,loc);
	return loc;
}
void MakeTower(char cur[],int n){
	int i=n-1;
	char tmp;
	if(n==1)
		return;
	if(n==2){
		if(cur[0]!=cur[1]){
			tmp=cur[0];
			cur[0]=cur[1];
   			printf("move %d disk %d : %c -> %c\n",++count,n-1,tmp,cur[1]);
		}
		return;
	}
	if(cur[i]!=cur[i-1] && cur[i]!=cur[i-2] && cur[i-1]!=cur[i-2]){
		MakeTower(cur,n-2);
		tmp=cur[i-1];
		cur[i-1]=cur[i];
   		printf("move %d disk %d : %c -> %c\n",++count,i,tmp,cur[i]);
		hanoi(cur,n-2,cur[i-2],tmp,cur[i]);
		return;
	}
	MakeTower(cur,n-1);
	if(cur[i-1]!=cur[i]){
		tmp=locdif(cur[i-1],cur[i]);
		hanoi(cur,n-1,cur[i-1],tmp,cur[i]);
	}
}

int locneed(char cur[],char obj[]){
	int i=length-1;
	while(cur[i]==obj[i])
		i--;
	return i;
}

void newhanoi(char cur[],char obj[]){
	int i=locneed(cur,obj);
	int tmp;
	char temp;
	if(i>1)
		MakeTower(cur,i-1);
	while(i>0){
		printf("cur[%d]=%c cur[%d-1]=%c obj[%d]=%c\n",i,cur[i],i,cur[i-1],i,obj[i]);
		if(cur[i]!=obj[i]){
			if(cur[i-1]==cur[i]){
				temp=locdif(cur[i],obj[i]);
				hanoi(cur,i,cur[i-1],obj[i],temp);
			}
			else if(cur[i-1]==obj[i]){
				temp=locdif(cur[i],obj[i]);
				hanoi(cur,i,cur[i-1],obj[i],temp);
			}
		}
		temp=cur[i];
		cur[i]=obj[i];
   		printf("move %d disk %d : %c -> %c\n",++count,i+1,temp,obj[i]);
		i--;
	}	
	if(cur[0]!=obj[0]){
		temp=cur[0];
		cur[0]=obj[0];
   		printf("move %d disk 1 : %c -> %c\n",++count,temp,obj[0]);
	}
}
int main(){
	char cur[5]="AAABB";
	char obj[5]="CABBB";
	newhanoi(cur,obj);	
	return 0;
}
