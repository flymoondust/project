#include <stdio.h>
#include <stdlib.h>
typedef struct node {
	    struct node *next,*pre;
		int data;
}node;
typedef struct Link{
	    node *tail;
		node *head;
}Link;
typedef Link *link;

void insert(link l,node *cur,node *p);
void linkclear(link l);
link createlink(int a[],int n);
void printl(link l);
