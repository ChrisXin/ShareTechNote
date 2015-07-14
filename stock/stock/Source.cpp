#include <stdio.h>
#include <afxinet.h>
#include <iostream>
#include <string>
#include <Windows.h>

using namespace std;

/*
int main(){
	string command = "";
	cout << "enter q to quit" << endl;
	cout << "Please enter stock code:" << endl;
	while (cin >> command){
	if (command == "q") return 0;
	string market = "";
	if (command.at(0) == '0'){ 
		market = "sz";
	}else{
		market = "sh";
	}
	//cout << "$$$$$$$" << command.at(0) << endl;
	CString stock[33];
	int count = 0;
	int index = 0;
	CInternetSession session("HttpClient");
	CString url = "http://hq.sinajs.cn/list=";
	string surl((LPCTSTR)url);
	surl = surl + market + command;
	url = surl.c_str();
	CHttpFile *pfile = dynamic_cast <CHttpFile*> (session.OpenURL(url));
	DWORD dwStatesCode;
	pfile->QueryInfoStatusCode(dwStatesCode);
	if (dwStatesCode == HTTP_STATUS_OK){
		CString content = "";
		CString data = "";
		while (pfile->ReadString(data)){
			//cout << LPCSTR(data) << endl;
			index = data.Find("\"");
			content = data.Mid(index + 1);
			content.TrimRight("\";");
		}
		CString object = "";
		while (!content.IsEmpty()){
			index = content.Find(",");
			if (index >= 0){
				object = content.Left(index);
				stock[count++] = object;
				object = content.Mid(index + 1);
				content = object;

			}
			else{
				stock[count++] = content;
				content = "";
			}
		}
	}
	if (stock[0] != ""){
	cout << endl
		<< "name:" << stock[0] << endl
		<< "todays openning price:" << stock[1] << endl
		<< "yesterdays closing price:" << stock[2] << endl
		<< "current:" << stock[3] << endl
		<< "max:" << stock[4] << endl
		<< "min:" << stock[5] << endl
		<< "time:" << stock[31] << endl;
	}
	else{
		cout << "sorry i cannot find it." << endl;
	}
	//Sleep(10000);

	}


}

*/