#include <windows.h>  
#include <stdio.h> 
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

#pragma comment(lib,"Urlmon")  

void downloadcsv(){
	HRESULT urldown;
	string url = "http://table.finance.yahoo.com/table.csv?s=002244.sz";
	string file = "c:\\1.csv";
	urldown = URLDownloadToFile(NULL, url.c_str(),
		file.c_str(), 0, NULL);
	switch (urldown) {
	case E_OUTOFMEMORY:
		cout << "fail£¡\n" << endl;
		break;
	case S_OK:
		cout << "success£¡\n" << endl;
		break;
	case INET_E_DOWNLOAD_FAILURE:
		cout << "cant find it£¡\n" << endl;
		break;
	}
}

double getdata(){
	ifstream file("c:\\1.csv"); // declare file stream: http://www.cplusplus.com/reference/iostream/ifstream/
	string value;
	double acc = 0;
	int i = 0;
	double arr[21] = {0};
	int day = 0;
	bool con = true;
	while (!file.eof() && i < 606)
	{
		getline(file, value, ','); // read a string until next comma: http://www.cplusplus.com/reference/string/getline/
		
		int count = 0;
		string gabage;
		double price = 0;
		/*for (int j = 0; j < 0; ++j){
			ss >> gabage;
			cout << "$$$$$$$$$" <<gabage << endl;
		}*/
		//ss >> price;
		//cout << price << endl;

		if (i == 10 || (i > 10 && (i - 10) % 6 == 0)) { 
			istringstream ss(value);
			double n = 0;
			ss >> n;
			//cout << value << endl; 
			++day;
			acc = acc + n;
		}
		++i;
		
		if (day % 5 == 0 && con){
			cout << "day  " << day  << endl;
			cout << "day % 5 " << day % 5 <<  endl;
			if(acc != 0 )	arr[day / 5] = acc / 5;
			cout << "set " << day / 5 << "acc:" << acc / 5 << endl;
			acc = 0;
		}
	}
	double final = 0;
	for (int i = 0; i < 21; ++i){
		final = final + arr[i];
		cout << "final:" << final << endl;
	}
	final = final / 20;
	return final;
}

int main() {

	downloadcsv();
	Sleep(1000);
	cout << "MA20:" << getdata() << endl;
	Sleep(100000);
}