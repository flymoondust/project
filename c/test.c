#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

void main(void)
{
	int fd;
	int i;
	char data[256];
	int retval;
	fd = open("/dev/fgj",O_RDWR);
	if(fd==-1)
	{
		perror("erroropen\n");
		exit(-1);
	}
	printf("open/dev/fgjsuccessfully\n");
	//写数据
	retval = write(fd,"fgj",3);
	if(retval==-1)
	{
		perror("writeerror\n");
		exit(-1);
	}
	//读数据
	retval=read(fd,data,3);
	if(retval==-1)
	{
		perror("readerror\n");
		exit(-1);
	}
	data[retval]=0;
	printf("readsuccessfully:%s\n",data);
	//关闭设备
	close(fd);
}

