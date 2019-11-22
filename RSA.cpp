	#include <iostream>
	#include <vector>
	#include <sstream>
	#include <cmath>
	#include <ctime>
	#include <string>

	using namespace std;

	// declarations (for recursive functions)
	string sumTwoBigNumbers(string x1, string x2, int div);
	string subtractTwoBigNumbers(string x1, string x2, int div);
	string multiplyTwoBigNumbers(string x1, string x2, int div);
	int compareFirstStringWithTheSecond(string s1, string s2);
	vector<int> convertBigNumberToBinary(string x);
	string karatsubaMultiplication(string x1, string x2);
	string divide(string x1, string x2);

	vector<string> balanceTwoStrings(string x1, string x2) {
		vector<string> s(2);
		//s.reserve(2);
		int l1 = x1.length();
		int l2 = x2.length();
		int diff = abs(l1 - l2);

		if (l1 > l2) {
			x2 = string(diff, '0') + x2;
		}
		else if (l2 > l1) {
			x1 = string(diff, '0') + x1;
		}
		s[0] = x1;
		s[1] = x2;

		return s;
	}

	vector<unsigned long long> constructOneDimensionalArrayFromString(string n, int numberOfDigits) {
		int length = n.length();
		int numberOfCols = length / numberOfDigits;
		
		if (length % numberOfDigits > 0) { // there is a reminder
			numberOfCols += 1;
		}
		
		vector<unsigned long long> longArray(numberOfCols);
		//longArray.reserve(numberOfCols);
		int i = length;

		while (i > 0) {
			if (i > numberOfDigits) {
				longArray[numberOfCols - 1] = stoull( n.substr(i - numberOfDigits, numberOfDigits) );
				numberOfCols--;
				i -= numberOfDigits;
			}
			else {
				longArray[0] = stoull( n.substr(0, i) );
				break;
			}

		}

		return longArray;
	}

	string removeTrailingZeroes(string output) {
		while (output != "0" && output.substr(0, 1) == "0") {
			output = output.substr(1, output.length() - 1);
		}

		return output;
	}

	bool checkIfAllZeroes(string x) {
		int c = 0;
		int count = x.length();

		if (x == "0") {
			return true;
		}

		while (x.substr(c, 1) == "0") {
			c++;

			if (c == count) {
				return true;
			}
		}

		return false;
	}

	bool isOdd(string x) {
		if ( stoi( x.substr(x.length() - 1, 1) ) % 2 == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	bool isEven(string x) {
		if ( stoi( x.substr(x.length() - 1, 1) ) % 2 == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	string multiplyTens(string m, int num) {
		while (num > 0) {
			m = m + "0";
			num--;
		}

		return m;
	}

	string addNinesIfNeeded(int arrElementLength) {
		string x = "";

		while (arrElementLength > 0) {
			x = "9" + x;
			arrElementLength--;
		}

		return x;
	}

	string addZeroesIfNeeded(string res, int arrElementLength) {
		int dif = arrElementLength - res.length();

		while (dif > 0) {
			res = "0" + res;
			dif--;
		}

		return res;
	}

	vector<int> concat(vector<int> a, vector<int> b) {
		vector<int> c; // preallocate memory
		c.reserve( a.size() + b.size() ); 
		c.insert( c.end(), a.begin(), a.end() );
		c.insert( c.end(), b.begin(), b.end() );

		return c;
	}

	string repeatMultiplyByTwo(double num) {
		if (num < 56) {
			return to_string( (int)pow(2, num) );
		}

		double pre = pow(2, num);

		if (num == 56) {
			return to_string( (int)pre );
		}

		string out = "1";
		double num1 = num - 56;

		while (num1 >= 56) {
			out = karatsubaMultiplication(out, to_string( (int)pre) );
			num1 -= 56;
		}

		if (num1 != 0) {
			double a = pow(2, num1);
			out = karatsubaMultiplication(out, to_string( (int)a) );
		}

		return out;
	}

	string repeatStringMultiplyByTwo(string num) {
		string out = "1";
		double pre = stod(num);

		if (pre < 56) {
			return to_string( (int)pow(2, pre) );
		}
		else if (pre == 56) {
			return to_string(pre);
		}
		else {
			string num1 = subtractTwoBigNumbers(num, "56", 18);

			while (compareFirstStringWithTheSecond(num1, "56") == 1) {
				out = karatsubaMultiplication(out, num);
				num1 = subtractTwoBigNumbers(num, "56", 18);
			}

			if (num1 != "0") {
				double a = pow( 2, stod(num1) );
				out = karatsubaMultiplication( out, to_string(a) );
			}

			return out;
		}
	}

	// sum two big numbers

	string sumTwoBigNumbers(string x1, string x2, int div) {
		if ( checkIfAllZeroes(x1) && checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if ( checkIfAllZeroes(x2) ) {
			return x1;
		}
		else if ( checkIfAllZeroes(x1) ) {
			return x2;
		}

		string x11 = x1.substr(0, 1);
		string x22 = x2.substr(0, 1);

		if (x11 == "-" && x22 == "-") {
			return "-" + sumTwoBigNumbers(x1.substr(1, x1.length() - 1), x2.substr(1, x2.length() - 1), 18);
		}
		else if (x11 == "-") {
			return subtractTwoBigNumbers(x2, x1.substr(1, x1.length() - 1), 18);
		}
		else if (x22 == "-") {
			return subtractTwoBigNumbers(x1, x2.substr(1, x2.length() - 1), 18);
		}
		else {
			string output = "";
			vector<string> s = balanceTwoStrings(x1, x2);
			vector<unsigned long long> arr1 = constructOneDimensionalArrayFromString(s[0], div);
			vector<unsigned long long> arr2 = constructOneDimensionalArrayFromString(s[1], div);
			int length = arr1.size();
			int len1 = to_string(arr1[length - 1]).length();
			int len2 = to_string(arr2[length - 1]).length();

			for (int j = length - 1; j >= 0; j--) {
				if (j == 0) {
					if (len1 >= len2) {
						div = len1;
					}
					else {
						div = len2;
					}
				}

				string m = to_string(arr1[j] + arr2[j]);
				string m1 = m;
				int len = m.length();
				int g = j - 1;

				if (len > div && g >= 0) {
					m1 = m.substr(1, m.length() - 1);
					arr1[g]++;
					len = to_string(arr1[g]).length();

					while (len > div && g >= 0) {
						string p = to_string(arr1[g]);
						arr1[g] = stoull( p.substr(1, p.length() - 1) );
						g--;
						arr1[g]++;
						len = to_string(arr1[g]).length();
					}
				}

				int dif = abs(div - m1.length() );

				while (dif > 0 && j != 0) {
					m1 = "0" + m1;
					dif--;
				}

				output = m1 + output;
			}

			return output;
		}
	}

	string subtractTwoBigNumbers(string x1, string x2, int div) {
		if ( checkIfAllZeroes(x1) && checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if ( checkIfAllZeroes(x1) ) {
			if (x2.substr(0, 1) == "-") {
				return x2.substr(1, x2.length() - 1);
			}
			else {
				return "-" + x2;
			}
		}
		else if ( checkIfAllZeroes(x2) ) {
			return x1;
		}

		string x11 = x1.substr(0, 1);
		string x22 = x2.substr(0, 1);

		if (x11 == "-" && x22 == "-") {
			return subtractTwoBigNumbers(x2.substr(1, x2.length() - 1), x1.substr(1, x1.length() - 1), div);
		}
		else if (x11 == "-") {
			return "-" + sumTwoBigNumbers(x2, x1.substr(1, x1.length() - 1), div);
		}
		else if (x22 == "-") {
			return sumTwoBigNumbers(x1, x2.substr(1, x2.length() - 1), div);
		}
		else {
			string output = "";
			vector<string> s = balanceTwoStrings(x1, x2);	
			vector<unsigned long long> arr1 = constructOneDimensionalArrayFromString(s[0], div);
			vector<unsigned long long> arr2 = constructOneDimensionalArrayFromString(s[1], div);
			int length = arr1.size();
			int whichIsBigger = compareFirstStringWithTheSecond(x1, x2);
			int len1 = to_string(arr1[length - 1]).length();
			int len2 = to_string(arr2[length - 1]).length();

			if (whichIsBigger == 2) { // swap arrays
				vector<unsigned long long> arr3 = arr1;
				arr1 = arr2;
				arr2 = arr3;
			}

			for (int j = length - 1; j >= 0; j--) {
				if (j == 0) {
					if (len1 >= len2) {
						div = len1;
					}
					else {
						div = len2;
					}
				}

				unsigned long long y1 = arr1[j];
				unsigned long long y2 = arr2[j];
				string res = "";

				if (y1 < y2) {
					int g = j - 1;
					
					if (arr1[g] != 0) {
						arr1[g]--;
					}
					else {
						arr1[g] = stoull( addNinesIfNeeded(div) );
						int k = g - 1;
						
						while (arr1[k] == 0) {
							arr1[k] = stoull( addNinesIfNeeded(div) );
							k--;
						}

						arr1[k]--;
					}

					y1 = stoull( "1" + addZeroesIfNeeded(to_string(arr1[j]), div) );
				}

				res = addZeroesIfNeeded(to_string(y1 - y2), div);
				output = res + output;
			}

			while (output != "0" && output.substr(0, 1) == "0") {
				output = output.substr(1, output.length() - 1);
			}

			if (whichIsBigger == 2) {
				output = "-" + output;
			}

			return output;
		}
	}

	string multiplyTwoBigNumbers(string x1, string x2, int div) {
		vector<string> s = balanceTwoStrings(x1, x2);	
		vector<unsigned long long> arr1 = constructOneDimensionalArrayFromString(s[0], div);
		vector<unsigned long long> arr2 = constructOneDimensionalArrayFromString(s[1], div);
		int length = arr1.size();
		int d1 = 0;
		int d2 = 0;
		string output = "0";

		for (int i = length - 1; i >= 0; i--) {
			unsigned long long r = arr1[i];

			for (int j = length - 1; j >= 0; j--) {
				output = sumTwoBigNumbers(output, multiplyTens(to_string(r * arr2[j]), d1 + d2), 18);
				d2 += div;
			}

			d1 += div;
			d2 = 0;
		}

		return output;
	}

	string mod(string x, string n) { // optimize if possible
		while (x.substr(0, 1) == "-") {
			x = sumTwoBigNumbers(x, n, 18);
		}

		string out = x;
		int a = compareFirstStringWithTheSecond(x, n);

		if (a == 2) {
			return x;
		}
		else if (a == 0) {
			return "0";
		}
		else {
			string sub = multiplyTens( n, x.length() - n.length() );
			int f = compareFirstStringWithTheSecond(sub, out);

			while (true) {
				while (f == 1) {
					sub = sub.substr(0, sub.length() - 1);
					f = compareFirstStringWithTheSecond(sub, out);
				}

				while (f == 2) {
					out = subtractTwoBigNumbers(out, sub, 18);
					f = compareFirstStringWithTheSecond(sub, out);
				}

				if (out == n || f == 0) {
					return "0";
				}
				else if (compareFirstStringWithTheSecond(out, n) == 2) {
					return out;
				}
			}
		}
	}

	string modularRepeatMultiply(string x, int dif, string n) {
		if (dif == 0) {
			return "1";
		}

		while (dif > 0) {
			x = mod(karatsubaMultiplication(x, x), n);
			dif--;
		}

		return x;
	}

	string longDivision(string x1, string x2) { // optimize if possible
		if ( checkIfAllZeroes(x1) ) {
			return "0";
		}
		else if (x1.substr(0, 1) == "-") {
			return "-" + ( longDivision(x1.substr( 1, x1.length() ), x2) );
		}

		string output = "";
		string sub = "";
		unsigned int ind = 0;
		unsigned long long diviend = stoull( x1.substr(0, 1) );
		unsigned long long divisor = stoull(x2);

		while ( ind < x1.length() ) {
			int flag = 0;

			while (diviend < divisor) {
				if (flag >= 1) {
					ind++;
					output = output + "0";
				}

				if (ind + 1 > x1.length()) {
					break;
				}

				sub = sub + x1.substr(ind, 1);

				if (ind + 1 == x1.length()) {
					return removeTrailingZeroes(output + to_string( stoull(sub) / divisor) );
				}

				diviend = stoull(sub);
				flag++;
			}

			output = output + to_string(diviend / divisor);
			unsigned long long mod = diviend % divisor;
			sub = to_string(mod);
			diviend = mod;
			ind++;
		}

		return removeTrailingZeroes(output);
	}

	int compareFirstStringWithTheSecond(string s1, string s2) {
		if (s1 == s2) { // equal strings
			return 0;
		}
		else {
			if (s1.substr(0, 1) == "-" && s2.substr(0, 1) == "-") { // both negative
				int x = compareFirstStringWithTheSecond(s1.substr(1, s1.length() - 1), s2.substr(1, s2.length() - 1));

				if (x == 1) {
					return 2;
				}
				else {
					return 1;
				}
			}
			else if (s1 != "" && s1.substr(0, 1) == "-") { // only first is negative so it's smaller
				return 2;
			}
			else if (s2 != "" && s2.substr(0, 1) == "-") { // only second is negative so it's smaller
				return 1;
			}
			else {
				if ( s1.length() > s2.length() ) {
					return 1;
				}
				else if ( s1.length() < s2.length() ) {
					return 2;
				}
				else {
					while ( s1.substr(0, 1) == s2.substr(0, 1) ) {
						s1 = s1.substr(1, s1.length() - 1);
						s2 = s2.substr(1, s2.length() - 1);
					}

					if ( stoi( s1.substr(0, 1) ) > stoi( s2.substr(0, 1) ) ) {
						return 1;
					}
					else {
						return 2;
					}
				}
			}
		}
	}

	vector<int> convertBigNumberToBinary(string x) {
		int i = 0;
		string out = "1";
		vector<int> bin(1);
		//bin.reserve(1);

		if (x == "0") {
			bin[0] = 0;
			return bin;
		}

		while (compareFirstStringWithTheSecond(out, x) == 2) { // x > out
			out = karatsubaMultiplication(out, "411376139330301510538742295639337626245683966408394965837152256");
			i += 208;
		}

		while (compareFirstStringWithTheSecond(out, x) == 1) {
			out = longDivision(out, "2");
			i--;
		}

		if (compareFirstStringWithTheSecond(out, x) == 0) { // x = out
			bin[0] = i;
			return bin;
		}

		bin[0] = i;
		string y = subtractTwoBigNumbers(x, out, 18);

		return concat( bin, convertBigNumberToBinary(y) );
	}

	// karatsuba algorithm for multiplying two big numbers
	string karatsubaMultiplication(string x1, string x2) {
		if ( checkIfAllZeroes(x1) || checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if (x1 == "1") {
			return x2;
		}
		else if (x2 == "1") {
			return x1;
		}
		else if (x1 == "-1") {
			return "-" + x2;
		}
		else if (x2 == "-1") {
			return "-" + x1;
		}

		string x11 = x1.substr(0, 1);
		string x22 = x2.substr(0, 1);

		if (x11 == "-" && x22 == "-") {
			return karatsubaMultiplication(x1.substr(1, x1.length() - 1), x2.substr(1, x2.length() - 1));
		}
		else if (x11 == "-") {
			return "-" + karatsubaMultiplication(x1.substr(1, x1.length() - 1), x2);
		}
		else if (x22 == "-") {
			return "-" + karatsubaMultiplication(x1, x2.substr(1, x2.length() - 1));
		}
		else if (compareFirstStringWithTheSecond(x1, "15") == 2) {
			string out = x2;
			int i = stoi(x1) - 1;

			for (int j = 0; j < i; j++) {
				out = sumTwoBigNumbers(out, x2, 18);
			}

			return out;
		}
		else if (compareFirstStringWithTheSecond(x2, "15") == 2) {
			string out = x1;
			int i = stoi(x2) - 1;

			for (int j = 0; j < i; j++) {
				out = sumTwoBigNumbers(out, x1, 18);
			}

			return out;
		}
		else {
			vector<string> s = balanceTwoStrings(x1, x2);
			x1 = s[0];
			x2 = s[1];

			if (x1.length() % 2 == 1) {
				x1 = "0" + x1;
				x2 = "0" + x2;
			}

			if (x1.length() <= 9) {
				return to_string( stoull(x1) * stoull(x2) );
			}
			else if (x1.length() <= 25) {
				return multiplyTwoBigNumbers(x1, x2, 9);
			}
			else {
				int len = x1.length(); // or x2.length()
				int h = len / 2;
				string w1 = x1.substr(0, h);
				string w2 = x1.substr(h, len - h);
				string w3 = x2.substr(0, h);
				string w4 = x2.substr(h, len - h);
				string a1 = karatsubaMultiplication(w1, w3);
				string d1 = karatsubaMultiplication(w2, w4);
				string m1 = sumTwoBigNumbers(w1, w2, 18);
				string m2 = sumTwoBigNumbers(w3, w4, 18);
				string m3 = karatsubaMultiplication(m1, m2);
				string m4 = subtractTwoBigNumbers(m3, a1, 18);
				string e1 = subtractTwoBigNumbers(m4, d1, 18);

				return sumTwoBigNumbers( sumTwoBigNumbers( multiplyTens(a1, len), d1, 18 ), multiplyTens(e1, h), 18 );
			}
		}
	}

	string extendedEuclid(string x, string n) { // x^-1 mod n
		string a1 = "1";
		string a2 = "0";
		string a3 = n;
		string b1 = "0";
		string b2 = "1";
		string b3 = x;
		string t1 = "";
		string t2 = "";
		string t3 = "";
		string q = "";

		if (b3.length() <= 18) {
			while (true) {
				if (b3 == "0") {
					return "no inverse";
				}

				if (b3 == "1") {
					while (b2.substr(0, 1) == "-") {
						b2 = sumTwoBigNumbers(n, b2, 18);
					}

					return b2;
				}

				q = longDivision(a3, b3);

				t1 = subtractTwoBigNumbers(a1, karatsubaMultiplication(q, b1), 18);
				t2 = subtractTwoBigNumbers(a2, karatsubaMultiplication(q, b2), 18);
				t3 = subtractTwoBigNumbers(a3, karatsubaMultiplication(q, b3), 18);

				a1 = b1;
				a2 = b2;
				a3 = b3;

				b1 = t1;
				b2 = t2;
				b3 = t3;
			}
		}
		else {
			while (true) {
				if (b3 == "0") {
					return "no inverse";
				}

				if (b3 == "1") {
					if (b2.substr(0, 1) == "-") {
						b2 = subtractTwoBigNumbers(n, b2.substr(1, b2.length() - 1), 18);
					}

					return b2;
				}

				q = divide(a3, b3);

				t1 = subtractTwoBigNumbers(a1, karatsubaMultiplication(q, b1), 18);
				t2 = subtractTwoBigNumbers(a2, karatsubaMultiplication(q, b2), 18);
				t3 = subtractTwoBigNumbers(a3, karatsubaMultiplication(q, b3), 18);

				a1 = b1;
				a2 = b2;
				a3 = b3;

				b1 = t1;
				b2 = t2;
				b3 = t3;
			}
		}
	}

	string divide(string x1, string x2) {
		if (x2.length() <= 18) {
			return longDivision(x1, x2);
		}

		if (x1 == x2) {
			return "1";
		}
		else if (compareFirstStringWithTheSecond(x1, x2) == 2) {
			return "0";
		}

		string x11 = x1.substr(0, 1);
		string x22 = x2.substr(0, 1);

		if (x11 == "-" && x22 == "-") {
			return divide(x1.substr(1, x1.length() - 1), x2.substr(1, x2.length() - 1));
		}
		else if (x11 == "-") {
			return "-" + divide(x1.substr(1, x1.length() - 1), x2);
		}
		else if (x22 == "-") {
			return "-" + divide(x1, x2.substr(1, x2.length() - 1));
		}
		else {
			int dif = x1.length() - x2.length();
			string tens = "";

			if (dif > 0) {
				tens = "1" + string(dif, '0');
			}
			else {
				tens = "1";
			}

			string anotherx1 = x1;
			string sub = multiplyTens(x2, x1.length() - x2.length());
			string out = "0";

			while (true) {
				while (compareFirstStringWithTheSecond(anotherx1, sub) == 1) { // anotherx1 > sub
					out = sumTwoBigNumbers(out, tens, 18);
					anotherx1 = subtractTwoBigNumbers(anotherx1, sub, 18);
				}

				while (compareFirstStringWithTheSecond(anotherx1, sub) == 2) { // anotherx1 < sub
					sub = sub.substr(0, sub.length() - 1);

					if (tens.length() > 0) {
						tens = tens.substr(0, tens.length() - 1);
					}
				}

				int comp = compareFirstStringWithTheSecond(anotherx1, x2);

				if (comp == 0) {
					return sumTwoBigNumbers(out, "1", 18);
				}

				if (comp == 2) {
					return out;
				}
			}
		}
	}

	string repeatMultiply(string x, string num) {
		string out = "1";
		string i = "0";
		string modu = mod(num, "2");
		string anothernum = longDivision(num, "2");

		if (anothernum != "0") {
			while (compareFirstStringWithTheSecond(i, anothernum) == 2) {
				out = karatsubaMultiplication(out, x);
				i = sumTwoBigNumbers(i, "1", 18);
			}

			out = karatsubaMultiplication(out, out);
		}
		else {
			if (modu == "0") {
				return "1";
			}
			else {
				out = karatsubaMultiplication(out, x);
			}
		}

		return out;
	}

	string montgomeryForm(string x, string n, string R) {		
		return mod( karatsubaMultiplication( mod(x, n), mod(R, n) ), n );
	}

	string getR(string n) {
		string R = multiplyTens( "1", n.length() - 1);

		if (compareFirstStringWithTheSecond(R, n) == 2) {
			R += "0";
		}

		return R;
	}

	string modR(string x, string R) {
		while (x.substr(0, 1) == "-") {
			x = sumTwoBigNumbers(x, R, 18);
		}

		if (compareFirstStringWithTheSecond(x, R) == 2) {
			return x;
		}
		else if (x == R) {
			return "0";
		}
		else {
			int len = x.length();

			return x.substr(len - R.length(), R.length());
		}
	}

	string montgomeryMultiplication(string x1, string x2, string n, int zeroes, string R, string modKR) {
		x1 = karatsubaMultiplication(x1, x2); // x1 * x2
		x1 = sumTwoBigNumbers( x1, karatsubaMultiplication( modR(karatsubaMultiplication( modR(x1, R), modKR ), R), n ), 18 );
		x1 = x1.substr(0, x1.length() - zeroes);

		if (compareFirstStringWithTheSecond(n, x1) == 1) {
			return x1;
		}
		else if (n == x1) {
			return "0";
		}
		else {
			return mod(x1, n);
		}
	}

	string getK(string R, string RInverse, string n) {
		return divide(subtractTwoBigNumbers(karatsubaMultiplication(R, RInverse), "1", 18), n);
	}

	string montgomeryModularRepeatMultiply(string x, int dif, string n, int zeroes, string RInverse, string R, string modKR) { // x1^2 mod n
		if (dif == 0) {
			return "1";
		}

		while (dif > 0) {
			x = montgomeryMultiplication(x, x, n, zeroes, R, modKR);
			dif--;
		}

		return x;
	}

	// use this for encryption
	string modularPower(string x, string m, string n, int zeroes, string RInverse, string R, string modKR) { // x^m mod n
		vector<int> bin = convertBigNumberToBinary(m); // binary representation of exponent m
		int len = bin.size();
		string last = montgomeryForm(repeatMultiply( x, repeatMultiplyByTwo( (double)(bin[len - 1]) ) ), n, R);
		string out = last;

		for (int i = len - 2; i >= 0; i--) {
			last = montgomeryModularRepeatMultiply(last, bin[i] - bin[i + 1], n, zeroes, RInverse, R, modKR);
			out = montgomeryMultiplication(out, last, n, zeroes, R, modKR);
		}

		return mod( karatsubaMultiplication(out, RInverse), n);
	}

	// use this for decryption
	string enhancedModularPower(string x, string m, string n, string p, string q) { // x^m mod n
		// string vp = recursiveModularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p);
		// string vq = recursiveModularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q);

		string Rf = getR(p);
		string RInverse1 = extendedEuclid(Rf, p);
		int zeroes1 = Rf.length() - 1;
		string k1 = getK(Rf, RInverse1, p);
		string modKR1 = modR(k1, Rf);

		string Rs = getR(q);
		string RInverse2 = extendedEuclid(Rs, q);
		int zeroes2 = Rs.length() - 1;
		string k2 = getK(Rs, RInverse2, q);
		string modKR2 = modR(k2, Rs);
		
		string vp = modularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p, zeroes1, RInverse1, Rf, modKR1);
		string vq = modularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q, zeroes2, RInverse2, Rs, modKR2);
		//string vp = anModularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p);
		//string vq = anModularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q);
		string xp = karatsubaMultiplication( q, extendedEuclid(q, p) );
		string xq = karatsubaMultiplication( p, extendedEuclid(p, q) );

		return mod( sumTwoBigNumbers( karatsubaMultiplication(vp, xp), karatsubaMultiplication(vq, xq), 18 ), n );
	}

	// string testPrimality(string n) {

	// 	// miller-rabin algorithm
	// 	// divide by 2 until we get odd result
	// 	int i = 0;
	// 	string n1 = subtractTwoBigNumbers(n, "1", 18);
	// 	string q = longDivision(n1, "2");
	// 	int k = 0;
	// 	string a = "2";

	// 	while (i < 3) { // perform test three times
	// 		while ( isEven(q) ) {
	// 			q = longDivision(q, "2");
	// 			k++;
	// 		}
	// 		if (modularPower(a, q, n) == "1") {
	// 			return "inclusive";
	// 		}
	// 		for (int j = 0; j <= k - 1; j++) {
	// 			string g = karatsubaMultiplication(to_string(j), q);
	// 			string t = repeatStringMultiplyByTwo(g);
	// 			if (modularPower(a, t, n) == n1) {
	// 				return "inclusive";
	// 			}
	// 		}
	// 		i++;
	// 	}

	// 	return "composite";
	// }

	int main() {
		string p = "12369571528747655798110188786567180759626910465726920556567298659370399748072366507234899432827475865189642714067836207300153035059472237275816384410077871";
		string q = "2065420353441994803054315079370635087865508423962173447811880044936318158815802774220405304957787464676771309034463560633713497474362222775683960029689473";
		string e = "65537";

		// n = p * q
		string n = karatsubaMultiplication(p, q);

		// phiOfn = (p - 1) * (q - 1)
		string phiOfn = karatsubaMultiplication( subtractTwoBigNumbers(p, "1", 18), subtractTwoBigNumbers(q, "1", 18) );

		// e^-1 mod phiOfn
		string d = extendedEuclid(e, phiOfn);

		// encryption
		string message = "97568759409682067900874649773939678036876767";
		clock_t cur4 = clock();
		string r = getR(n);
		string rInverse = extendedEuclid(r, n);
		string nInverse = "-" + extendedEuclid(n, r);
		string modnInverseWithR = modR(nInverse, r);
		int zeroes = r.length() - 1;
		string c = modularPower(message, "65537", n, zeroes, rInverse, r, modnInverseWithR);
		clock_t cur2 = clock();

		cout << "encrypt public : " << float(cur2 - cur4) / CLOCKS_PER_SEC;
		cout << "\n";

		// decryption
		string m = enhancedModularPower(c, d, n, p, q);
		clock_t cur3 = clock();

		cout << "decrypt private : " << float(cur3 - cur2) / CLOCKS_PER_SEC;
		cout << "\n";



		string exp = message;
		string o = m;

		cout << o;
		cout << "\n";

		cout << exp;
		cout << "\n";

		if (o == exp) {
			cout << "yes";
		}
		else {
			cout << "no";
		}

		cout << "\n";

		return 0;
	}