java -jar signapk.jar platform.x509.pem platform.pk8 ../bin/SystemUI2.apk ../bin/systemuinew.apk
./adb push ../bin/systemuinew.apk /system/app/SystemUI.apk
