
obj-m := mydm1.o
#KERNELDIR := /lib/modules/3.13.0-32-generic/build
KERNELDIR := /lib/modules/$(shell uname -r)/build
PWD := $(shell pwd)


modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules
modules_install:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules_install

clean:
	rm *.o *.ko *.mod.c *.order *.symvers
