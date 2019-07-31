
#扫描目标主机开发的TCP端口


#快速解析要扫描的目标主机名和端口的方法
import optparse
from socket import *

def connScan(tgtHost,tgtPort):
	try:
		connSkt = socket(AF_INET,SOCK_STREAM)
		connSkt.connect((tgtHost,tgtPort))
		print('%d/tcp open'%tgtPort)
		connSkt.close()
	except Exception as e:
		print('%d/tct closed'%tgtPort)
	else:
		pass
	finally:
		pass
def portScan(tgtHost,tgtPorts):
	try:
		tgtIP = gethostbyname(tgtHost)
	except Exception as e:
		print('Cannot resolve "%s":Unkonwn host'%tgtHost)
		return
	try:
		tgtName = getHostbyaddr(tgtIP)
		print('\n Scan REsults for:'+ tgtName[0])
	except Exception as e:
		print('\n Scan REsults for:'+ tgtIP)
	setdefaulttimeout(1)
	for tgtPort in tgtPorts:
		print("Scanning port" + tgtPort)
		connScan(tgtHost,int(tgtPort))


parser = optparse.OptionParser('usage %prog -H <target host> -p <target port>')
parser.add_option('-H',dest='tgtHost',type='string',help='specify target host')
parser.add_option('-p',dest='tgtPort',type='int',help='specify target port')
(options,args) = parser.parse_args()
tgtHost = options.tgtHost
tgtPort = options.tgtPort
if (tgtHost == None)|(tgtPort == None):
	print(parser.usage)
	exit(0)

