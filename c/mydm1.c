#include <linux/init.h> 
#include <linux/module.h>
#include <linux/types.h>
#include <linux/mm.h>
#include <linux/fs.h>
#include <linux/errno.h>
#include <linux/sched.h>
#include <linux/cdev.h>
#include <asm/io.h>
#include <asm/uaccess.h>
 
MODULE_LICENSE("Dual BSD/GPL"); 

int devMajor = 224;

static unsigned char simple_inc = 0;
static unsigned char demoBuffer[256];

int simple_open( struct inode *inode, struct file *filp )
{
	printk("huyanping  : open Success!\n");
	if(simple_inc>0)
	{
		return -1;
	}
	
	simple_inc = simple_inc + 1;
	return 0;
}

int simple_release(struct inode *inode, struct file *filp)
{
	printk("huyanping  : release Success%d!\n",devMajor);
	simple_inc = simple_inc - 1;
	return 0;
}

ssize_t simple_read(struct file *filp,char __user *buf,size_t count,loff_t *f_pos)
{
	printk("huyanping  : read Success%d!\n", devMajor);
	/*把数据复制到应用程序空间*/
	if( copy_to_user(buf,demoBuffer,count) )
	{
		count=-EFAULT;
	}
	return count;
}

ssize_t simple_write(struct file *filp, const char __user *buf,size_t count,loff_t *f_pos)
{
	printk("huyanping  : write Success%d!\n",devMajor);
	/*把数据复制到内核空间*/
	if(copy_from_user(demoBuffer + *f_pos,buf,count))
	{
		count=-EFAULT;
	}
	return count;
}

struct file_operations simple_fops={
.owner=THIS_MODULE,
.read=simple_read,
.write=simple_write,
.open=simple_open,
.release=simple_release,
};

/*******************************************************
MODULEROUTINE
*******************************************************/
static void  __exit simple_cleanup_module(void)
{
	unregister_chrdev(devMajor,"mydm1");
	printk("huyanping  : simple_cleanup_module!\n");
	//return 0;
}

static int __init simple_init_module(void)
{
	int ret;
	//根据设备号与设备名注册字符设备
	ret=register_chrdev( devMajor,"mydm1",&simple_fops);
	if(ret<0)
	{
		printk("huyanping  : Unabletoregistercharacterdevice%d!\n",devMajor);
		return ret;
	}
	else{
		printk("huyanping  : Success%d!\n",devMajor );
	}
	return 0;
}

module_init(simple_init_module);
module_exit(simple_cleanup_module);
