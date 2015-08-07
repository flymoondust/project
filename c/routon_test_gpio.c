#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define DEVICE_NAME "/dev/routon_test_gpio"

#define ROUTON_DIRECTION_INPUT  1987
#define ROUTON_DIRECTION_OUTPUT 1122

int main(int argc, char** argv)
{
	int fd = -1;
	int gp_val = 1;
	int gp_num = 0, res = 0;
	int gp_dir = 0, gp_dir_val = 0;

	if(argc == 1)
	{
		printf("!!!!!! USAGE:   ./routon_test_gpio GPIO_RW_NUM GPIO_VALUE GPIO_CMD GPIO_DIR_VALUE\n	\
			!!!!!! OR:      ./routon_test_gpio GPIO_RW_NUM GPIO_VALUE\n	\
			!!!!!! CAUTION: GPIO_CMD must be 1987(input) or 1122(output)\n	\
			!!!!!! EX.: routon_test_gpio 17 1 1122 1 (routon_test_gpio 231 1)");
		return 0;
	}

	printf("\n######Fu Xueshu %s open: %s\n", __FUNCTION__, DEVICE_NAME);
	fd = open(DEVICE_NAME, O_RDWR);
	if(fd == -1) {
		printf("!!!!!! Failed to open device %s.\n", DEVICE_NAME);
		return -1;
	}
	usleep(10000);

	gp_num = atoi(argv[1]);
	res = gp_num;
	read(fd, &res, sizeof(res));//读取gp_num的值，回存到res中
	usleep(10000);
	printf("######Fu Xueshu read GPIO(%d) value: %d\n", gp_num, res);

	gp_val = atoi(argv[2]);
	printf("######Fu Xueshu write GPIO(%d) value: %d\n", gp_num, gp_val);
	usleep(10000);
        write(fd, &gp_num, gp_val);//写入gp_val的值到gp_num中

	usleep(10000);
	if(argc == 5)
	{
		gp_dir = atoi(argv[3]);//command: 1987 or 1122
		gp_dir_val = atoi(argv[4]) + gp_num * 10;
		printf("######Fu Xueshu make argument %d(0x%x)\n", gp_dir_val, gp_dir_val);
		usleep(10000);
		if(gp_dir != ROUTON_DIRECTION_INPUT && gp_dir != ROUTON_DIRECTION_OUTPUT)
		{
			printf("!!!!!! GPIO command ERROR !!!!!!\n");
			return -1;
		}
		printf("######Fu Xueshu ioctl GPIO(%d) \"%s\" value: %d\n", gp_dir_val / 10, gp_dir == ROUTON_DIRECTION_INPUT ? "INPUT" : "OUTPUT", gp_dir_val % 10);
		usleep(10000);
		ioctl(fd, gp_dir, gp_dir_val);
	}
	/*else
	{
		printf("!!!!!! Missing some arguments !!!!!!\n");
		return -1;
	}*/

	close(fd);
	return 0;
}
