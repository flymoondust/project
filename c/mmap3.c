#include<sys/mman.h>
#include<sys/types.h>
#include<fcntl.h>
#include<stdio.h>
#include<unistd.h>
typedef struct{
	    char name[4];
	    int age;
}people;
void main(int argc,char **argv)//map a normal file as shared mem:
{
	    int fd,i,ret;
	    people *p_map;
	    fd=open(argv[1],O_CREAT|O_RDWR,00777);
		lseek(fd,40,SEEK_END);//验证！！！！！！
		ret=write(fd,"wx",1);
//		printf("ret= %d\n",ret);
		close(fd);
		fd=open(argv[1],O_CREAT|O_RDWR,00777);
	    p_map=(people*)mmap(NULL,sizeof(people)*10,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
		printf("%%x p_map= %x\t%%p p_map= %p\n",p_map,p_map);
	    for(i=0;i<10;i++)
	    {
	        printf("name: %s age: %d;\n",(*(p_map+i)).name,(*(p_map+i)).age);
	    }
	    munmap(p_map,sizeof(people)*10);
}
