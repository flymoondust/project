#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
	pthread_t ntid;
	int i=1;
	struct foo{
		int a,b,c,d;
	};
	void printfoo(const char *s,const struct foo *fp){
		printf("%s",s);
		printf("  structure at 0x%x\n",fp);
		printf("  foo.a=%d\n",fp->a);
		printf("  foo.b=%d\n",fp->b);
		printf("  foo.c=%d\n",fp->c);
		printf("  foo.d=%d\n",fp->d);
	}
	void printids(const char *s){
		pid_t pid;
		pthread_t tid;
	    tid=pthread_self();
		pid=getpid();
		printf("%s %d pid %u tid %u (0x%x)\n",s,i++,pid,(unsigned int)tid,(unsigned int)tid);
	}

	void * thr_fn(void *arg){
		struct foo foo= {1,2,3,4};
		printids("new thread ");
		printfoo("thread 1 :\n",&foo);
		//printf("ntid %u (0x%x)\n",*(unsigned int*)arg,*(unsigned int*)arg);
		pthread_exit((void *)&foo);
	}	

	void * thr_fn1(void *arg){
		printf("thread 2 i=%i\n : ID is 0x%x\n",i,pthread_self());
		printids("new thread ");
		pthread_exit((void *)0);
	}

	int main(void){
		int err;
		struct foo *fp;
		err=pthread_create(&ntid,NULL,thr_fn,&ntid);
		if(err!=0){
			perror("can't create thread 1");
			exit(0);
		}
		err=pthread_join(ntid,(void *)&fp);
		if(err!=0)
			perror("can't join thread 1");
		printids("main thread ");
		err=pthread_create(&ntid,NULL,thr_fn1,NULL);
		if(err!=0){
			perror("can't create thread 2");
			exit(0);
		}
//		sleep(1);
		//err=pthread_join(ntid,(void *)&fp);
		printfoo("parent:\n",fp);
		if(err!=0)
			perror("can't join with new tread");
		return 0;
	}
