#include <stdio.h>
#include "link.h"

void sort1(int A[],int length){
	int i=1,j,key;
	for(;i<length;i++){
		j=i-1;
		key=A[i];
		while(j>=0 && A[j]> key){
			A[j+1]=A[j];
			j--;
		}
		A[j+1]=key;
	}
}

void sort2(link l){
	if(l && l->head && l->head->next){
		node *cur=l->head->next->next;
		int ch=0;
		while(cur){
			node *tmp=cur->pre,*tmp1;
			int key=cur->data;
			while(tmp!=l->head){
				if(tmp->data < key){
					tmp1=cur->next;
					if(ch==0)
						break;
					printf("cur=%d,tmp=%d\n",cur->data,tmp->data);
					insert(l,tmp,cur);
					ch=0;
					break;
				}
				else{
					ch=1;
					tmp=tmp->pre;
				}
			}
			if(tmp == l->head){
				printf("insert head\n");
				insert(l,tmp,cur);
			}
			cur=tmp1;
		}
	}
    printf("sort end\n");
}

int main(){
	int i=0;
	int A[7]={2,4,6,3,10,7,9};
	link l=createlink(A,7);
	printf("before sort A[]: ");
	for(i=0;i<7;i++)
		printf("%d ",A[i]);
	sort1(A,7);
	printf("\nafter sort A[]: ");
	for(i=0;i<7;i++)
		printf("%d ",A[i]);
	printf("\n");
	
	printl(l);	
	sort2(l);
	printl(l);	
	linkclear(l);
	return 0;
}
