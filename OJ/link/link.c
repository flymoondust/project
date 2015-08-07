#include <stdio.h>
#include <stdlib.h>
#include "link.h"

const int nsize = sizeof(node);
void insert(link l,node *cur,node *p){
	if(p!=l->tail)
		p->next->pre=p->pre;
	p->pre->next=p->next; //把结点p拿出来
	p->next=cur->next;
	p->pre=cur;
	if(cur->next!=NULL)
		cur->next->pre=p;
	cur->next=p;//把拿出来的结点p放入cur后面
}

void linkclear(link l){
	node *p,*tmp=l->head;
	while(tmp){
		p=tmp->next;
		free(tmp);
		tmp=p;	
	}	
}

link createlink(int a[],int n){
	int i;
	link l=(link)malloc(sizeof(Link));
	l->head=(node *)malloc(nsize);
	l->head->pre=l->head->next=NULL;
	l->tail=l->head;
	for(i=0;i<n;i++){
		node *tmp=(node *)malloc(nsize);
		tmp->data=a[i];
		tmp->next=NULL;
		tmp->pre=l->tail;
		l->tail->next=tmp;
		l->tail=tmp;
	}
	return l;
}

void printl(link l){
	if(l && l->head && l->head->next){
		node *cur=l->head->next;
		while(cur){
			printf("%d ",cur->data);
			cur=cur->next;
		}
	}
	printf("\n");
}
