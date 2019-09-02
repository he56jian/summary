//cam.h


#ifndef __CAM_H__
#define __CAM_H__

#include <stdint.h>

//wakeup source
#define WAKEUP_TEST		0x00
#define WAKEUP_PIR		0x01
#define WAKEUP_TIME_LAPSE	0x02
#define WAKEUP_SMS		0x03
#define WAKEUP_MASK		0x07

//system notification
#define NOTIF_NORMAL_BAT	0x00
#define NOTIF_LOW_BAT		0x01
#define NOTIF_INT_PWR		0x02
#define NOTIF_EXT_PWR		0x03
#define NOTIF_SWITCH		0x04
#define NOTIF_RESET		0x05
#define NOTIF_MASK		0xF8


//System event bit
#define EVT_BIT_WK_TEST         0x00
#define EVT_BIT_WK_PIR          0x01
#define EVT_BIT_WK_TIME_LAPSE   0x02
#define EVT_BIT_WK_SMS          0x03
#define EVT_BIT_NORMAL_BAT      0x04
#define EVT_BIT_LOW_BAT         0x05
#define EVT_BIT_INT_PWR         0x06
#define EVT_BIT_EXT_PWR         0x07
#define EVT_BIT_SWITCH          0x08
#define EVT_BIT_RESET           0x09

//System event
#define EVT_WK_TEST             (1<<EVT_BIT_WK_TEST)
#define EVT_WK_PIR              (1<<EVT_BIT_WK_PIR)
#define EVT_WK_TIME_LAPSE       (1<<EVT_BIT_WK_TIME_LAPSE)
#define EVT_WK_SMS              (1<<EVT_BIT_WK_SMS)
#define EVT_NORMAL_BAT          (1<<EVT_BIT_NORMAL_BAT)
#define EVT_LOW_BAT             (1<<EVT_BIT_LOW_BAT)
#define EVT_INT_PWR             (1<<EVT_BIT_INT_PWR)
#define EVT_EXT_PWR             (1<<EVT_BIT_EXT_PWR)
#define EVT_SWITCH              (1<<EVT_BIT_SWITCH)
#define EVT_RESET               (1<<EVT_BIT_RESET)

//Power off type
#define POWEROFF_PS		0	//Power save
#define POWEROFF_DONE		1	//Task is done

enum CamVersion
{
	CAM_VERSION_LIB,
	CAM_VERSION_MCU,
	CAM_VERSION_DAEMON,
	CAM_VERSION_ALL,
	CAM_VERSION_MAX
};

enum CamMode
{
	CAM_MODE_PHOTO,
	CAM_MODE_VIDEO,
	CAM_MODE_HYBRID,
	CAM_MODE_MAX
};

enum PhotoRes
{
	PHOTO_RES_1080P,
	PHOTO_RES_3MP,
	PHOTO_RES_5MP,
	PHOTO_RES_MAX
};

enum VideoRes
{
	VIDEO_RES_D1,
	VIDEO_RES_720P,
	VIDEO_RES_1080P,
	VIDEO_RES_1440P,
	VIDEO_RES_MAX
};



enum PirSense
{
	PIR_SENSE_OFF,
	PIR_SENSE_LOW,
	PIR_SENSE_NORMAL,
	PIR_SENSE_HIGH,
	PIR_SENSE_MAX
};

enum WorkTime
{
	WORK_TIME_1,
	WORK_TIME_2,
	WORK_TIME_3,
	WORK_TIME_4,
	WORK_TIME_MAX
};

enum NetworkMode
{
	NET_MODE_NO_SERVICE,
	NET_MODE_GSM,
	NET_MODE_GPRS,
	NET_MODE_EDGE,
	NET_MODE_WCDMA,
	NET_MODE_HSDPA,	//HSDPA only(WCDMA)
	NET_MODE_HSUPA,	//HSUPA only(WCDMA)
	NET_MODE_HSPA,	//(HSDPA, HSUPA and WCDMA)
	NET_MODE_LTE,
	NET_MODE_TDS_CDMA,
	NET_MODE_TDS_HSDPA,
	NET_MODE_TDS_HSUPA,
	NET_MODE_TDS_HSPA,
	NET_MODE_CDMA,
	NET_MODE_EVDO,
	NET_MODE_HYBRID,	//(CMDA and EVDO)
	NET_MODE_1XLTE,	//(CDMA and LTE)
	NET_MODE_EHRPD = 23,
	NET_MODE_HYBRID_EHRPD, //(CDMA and eHRPD)
	NET_MODE_MAX
};

enum LteRegStatus			//定义枚举变量；
{
	LTE_REG_FAILED,
	LTE_REG_HOME,
	LTE_REG_REGISTERING,
	LTE_REG_DENIED,
	LTE_REG_UNKNOWN,
	LTE_REG_ROAMING,
	LTE_REG_MAX
};

enum LteConnectStatus
{
	LTE_CONNECT_FAILED = -1,
	LTE_DISCONNECTED = 0,
	LTE_CONNECTED,
	LTE_CONNECTING,
	LTE_CONNECT_MAX
};

struct TriggerParam_t
{
	unsigned char TriggerMode:2;	//0:pir, 1:time lapse, 2:both
	unsigned char Res1:6;
	unsigned char PirSense:2;	//0:auto, 1:high, 2:low
	unsigned char Res2:6;
	unsigned char PirDelay:6;
	unsigned char PirDelayUnit:2;	//0:sec, 1:min, 2:hour
	unsigned char TimeLapse:6;
	unsigned char TimeLapseUnit:2;	//0:sec, 1:min, 2:hour
};

struct TimePoint_t				//定义
{
	unsigned char Hour:5;
	unsigned char Res1:3;
	unsigned char Min:6;
	unsigned char Res2:2;
};


struct WorkTime_t
{
	unsigned char WeekMask:7;
	unsigned char OnOff:1;
	struct TimePoint_t Start;
	struct TimePoint_t End;
};


struct SensorData_t
{
	unsigned char BrightLow;
	unsigned char BrightMedium;
	unsigned char BrightHigh:2;
	unsigned char Battery:4;
	unsigned char Res:2;
	unsigned char Temperature;
};

#define CAM_NAME_MAX_LEN		9
struct CamParam_t							//定义参数结构，
{
	unsigned char Mode:2;						//定义Mode变量占据2字节；
	unsigned char FlashPower:2;					//定义FlashPower变量占据2字节；
	unsigned char Shutter:1;
	unsigned char BurstSpeed:1;
	unsigned char VideoRes:2;

	unsigned char PhotoRes:4;
	unsigned char BurstNum:4;

	unsigned char VideoLength:6;
	unsigned char Rename:1;
	unsigned char OverWrite:1;

	unsigned char SendOption:4;
	unsigned char Res1:4;

	unsigned char Language:5;
	unsigned char Gps:1;
	unsigned char Passwd:1;
	unsigned char Buzzer:1;
	
	unsigned char Password[4];
	unsigned char CamName[CAM_NAME_MAX_LEN];
};

struct SendParam_t
{
	unsigned char Mode:2;		//0:install, 1:daily report, 2:install+daily report, 3:off
	unsigned char RemoteCtrl:4;	//0:off, 1:real time, 2:delay 0.5h, 3:delay 1h, 4:delay 2h, 5:delay 3h
					//6:delay 4h, 7:delay 6h, 8:delay 12h
	unsigned char Res:2;
	unsigned char MaxNumber;	//0:unlimited, 1~99
	struct TimePoint_t Report;
};

typedef struct SysParam_t
{
	struct CamParam_t Cam;
	struct WorkTime_t WorkTime[4];
	struct TriggerParam_t Trigger;
	struct SendParam_t Send;
}SysParam;


#define REVISION_MAX_LENGTH		24
#define SW_VERSION_MAX_LENGTH		24
#define IMEI_MAX_LENGTH 		24

typedef struct
{
	char Revision[REVISION_MAX_LENGTH];
	char SwVersion[SW_VERSION_MAX_LENGTH];
	char Imei[IMEI_MAX_LENGTH];
}LteModuleInfo;

typedef struct
{
	char Registration;
	char Signal;
	char Mode;
	char Connection;
}LteStatus;

#define APN_MAX_LENGTH 			32
#define ACC_MAX_LENGTH 			32
#define PSW_MAX_LENGTH 			32

typedef struct
{
	char Apn[APN_MAX_LENGTH];
	char Account[ACC_MAX_LENGTH];
	char Passwd[PSW_MAX_LENGTH];
}LteSimInfo;

/*
typedef union cam_time
{
	uint64_t d64;
	struct
	{
		uint8_t sec;
		uint8_t min;
		uint8_t hour;
		uint8_t mday;
		uint8_t mon;
		uint8_t year;
		uint8_t reserved;
	}b;
}cam_time_t;
*/
typedef union cam_time
{
	struct
	{
		uint8_t year;
		uint8_t mon:4;
		uint8_t res:3;
		uint8_t sync:1;
		uint8_t mday;
		uint8_t hour:5;
		uint8_t week:3;
		uint8_t min;
		uint8_t sec;
	}b;
}cam_time_t;

struct FileDateTime_t
{
	uint8_t year;
	uint8_t mon;
	uint8_t day;
	uint8_t hour;
	uint8_t min;
	uint8_t sec;
};

typedef struct ShortFrame_t
{
	unsigned char TriggerSrc:3;	//offset: 0
	unsigned char Res0:1;
	unsigned char SendMode:2;
	unsigned char Res1:2;

	struct SensorData_t Sensor;	//offset: 1
	cam_time_t Time;		//offset: 5
	unsigned char CaptureMode:2;	//offset: 11
	unsigned char FlashPwr:2;
	unsigned char BurstSpeed:1;
	unsigned char Shutter:1;
	unsigned char VideoRes:2;
	unsigned char PhotoRes:4;	//offset: 12
	unsigned char Burst:4;
	unsigned char RecordTime:6;	//offset: 13
	unsigned char Rename:1;
	unsigned char OverWrite:1;
	unsigned char Name[CAM_NAME_MAX_LEN];	//offset: 14
	unsigned short DirId;		//offset: 23
	unsigned short FileId;
	unsigned short UploadedL;
	unsigned short UploadedH;
	struct FileDateTime_t FileTime;	//offset: 31
}ShortFrame;

static inline int struct_tm_to_cam_time(struct tm *tm, cam_time_t *cam_t)
{
	if((tm == NULL) || (cam_t == NULL))
	{
		return -1;
	}

	cam_t->b.sec = tm->tm_sec;
	cam_t->b.min = tm->tm_min;
	cam_t->b.hour = tm->tm_hour;
	cam_t->b.mday = tm->tm_mday;
	cam_t->b.week = tm->tm_wday;
	cam_t->b.mon = tm->tm_mon + 1;
	cam_t->b.year = tm->tm_year + 100;

	return 0;
}

static inline int cam_time_to_struct_tm(cam_time_t *cam_t, struct tm *tm)
{
	if((tm == NULL) || (cam_t == NULL))
	{
		return -1;
	}

	tm->tm_sec = cam_t->b.sec;
	tm->tm_min = cam_t->b.min;
	tm->tm_hour = cam_t->b.hour;
	tm->tm_mday = cam_t->b.mday;
	tm->tm_mon = cam_t->b.mon - 1;
	tm->tm_year = cam_t->b.year + 100;

	return 0;
}

static inline void print_cam_time(cam_time_t time)
{
	printf("Cam time: %04u-%02u-%02u %02u:%02u:%02u\n",
		time.b.year + 2000,//1900,
		time.b.mon,
		time.b.mday,
		time.b.hour,
		time.b.min,
		time.b.sec);
}

static inline void print_cam_param(struct CamParam_t *Cam)
{
	printf("Mode: %d\n"\
		"FlashPower: %d\n"\
		"Shutter: %d %d\n"\

		"Photo: %d %d\n"\
		"Video: %d %d\n"\
		"Rename: %d %s\n"\
		"OverWrite: %d\n"\
		"SendOption: %d\n"\
		"Password: %d %d%d%d%d\n",
		Cam->Mode,
		Cam->FlashPower,
		Cam->Shutter, Cam->BurstSpeed,
		Cam->PhotoRes, Cam->BurstNum,
		Cam->VideoRes, Cam->VideoLength,
		Cam->Rename, Cam->CamName,
		Cam->OverWrite,
		Cam->SendOption,
		Cam->Passwd, Cam->Password[0], Cam->Password[1], Cam->Password[2], Cam->Password[3]);
}

static inline void print_work_time(struct WorkTime_t *WorkTime)
{
	printf("WeekMask: %d\n"\
		"OnOff: %d\n"\
		"Start: %d:%d\n"\
		"End: %d:%d\n",
		WorkTime->WeekMask,
		WorkTime->OnOff,
		WorkTime->Start.Hour, WorkTime->Start.Min,
		WorkTime->End.Hour, WorkTime->End.Min);
}

static inline void print_trigger_param(struct TriggerParam_t *Trigger)
{
	printf("TriggerMode: %d\n"\
		"PirSense: %d\n"\
		"Pir: %d %d\n"\
		"TimeLapse: %d %d\n",
		Trigger->TriggerMode,
		Trigger->PirSense,
		Trigger->PirDelay, Trigger->PirDelayUnit,
		Trigger->TimeLapse, Trigger->TimeLapseUnit);
}

int CamInit(void);

void CamDeinit(void);

char *CamVersion(int Ver);

int CamGetEvent(void);
int CamGetEvent2(void);
void CamClearEvent(void);

int CamRedLedEnable(void);
int CamRedLedDisable(void);

int CamSetPirSense(int Sense);
int CamGetPirSense(void);
int CamSetPirDelay(int Delay);
int CamGetPirDelay(void);
int CamSetCamParam(struct CamParam_t *Cam);
int CamGetCamParam(struct CamParam_t *Cam);
int CamSetTriggerParam(struct TriggerParam_t *Trigger);
int CamGetTriggerParam(struct TriggerParam_t *Trigger);

int CamSetSendParam(struct SendParam_t *Send);
int CamGetSendParam(struct SendParam_t *Send);

int CamSetWorkTime(int WTimeNo, struct WorkTime_t *WorkTime);
int CamGetWorkTime(int WTimeNo, struct WorkTime_t *WorkTime);



int CamSetTime(cam_time_t Time);
int CamGetTime(cam_time_t *Time);

int CamSysParamReset(void);

int CamGetSysParam(SysParam **pSysParam);
int CamGetShortFrame(ShortFrame **pShortFrm);

int CamMasterPoweroff(int type);
int CamMasterReset(void);

int CamBrightness(void);
int CamTemperature(void);

int CamLtePoweron(void);
int CamLtePoweroff(void);
int CamLteReset(void);
int CamLteModuleIsReady(void);
int CamGetLteModuleInfo(LteModuleInfo *Info);
int CamGetLteSimInfo(LteSimInfo *Info);
int CamSetLteSimInfo(LteSimInfo *Info);
int CamGetLteStatus(LteStatus *Status);
int CamGetLteMode(void);
int CamLteConnect(void);
int CamLteDisconnect(void);

int CamWifiPoweron(void);
int CamWifiPoweroff(void);
int CamWifiReset(void);

int CamUpgradeMcu(void);



#if 0
#define ZRT_CAM_Init(Client)					CamInit
#define ZRT_CAM_Exit() 							CamDeinit
#define ZRT_CAM_Verison(Ver)					CamVersion

#define ZRT_CAM_Event_Flag 						CamGetEvent
#define ZRT_CAM_Event_ClearAllFlag 				CamClearEvent

#define ZRT_CAM_PIR_Enable 						CamSetPirSense(SEN_NORMALL)
#define ZRT_CAM_PIR_Disable 					CamSetPirSense(SEN_OFF)
#define ZRT_CAM_SET_PIR_TIME 					CamSetPirDelay
#define ZRT_CAM_LED_R_Enable 					CamRedLed(1)
#define ZRT_CAM_LED_R_Disable 					CamRedLed(0)

#define ZRT_CAM_Set_Time 						CamSetTime
#define ZRT_CAM_Get_Time 						CamGetTime
#endif
#endif //__CAM_H__
