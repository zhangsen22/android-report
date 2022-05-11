# _*_ coding: utf-8 _*_
"""
Time:     2021/9/29 10:48 下午
Author:   xuweiyu1
File:     update_all_script.py
Describe: 下载同步脚本
"""
import os
import sys


def download(password):
    install_wget(password)
    os.system("wget -N http://storage.jd.local/flutter-snapshot/mpaas/scripts/JDGroovyScript.jar")
    os.system("wget -N http://storage.jd.local/flutter-snapshot/mpaas/scripts/JDJavaScript.jar")
    os.system("wget -N http://storage.jd.local/flutter-snapshot/mpaas/scripts/jd_tools.snapshot")


def install_wget(password):
    if os.system("wget --version") != 0:
        install_brew(password)
        os.system("brew install wget")


def install_brew(password):
    if os.system("brew -v") != 0:
        if password == "":
                print("执行参数请携带密码！！！")
        else:
            command = "ruby -e %s" % "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
            os.system('echo %s | sudo -S %s' % (password, command))


if __name__ == '__main__':
    if len(sys.argv) > 1:
        download(sys.argv[1])
    else:
        download("")