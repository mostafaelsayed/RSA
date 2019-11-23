package RSALong;

import java.util.Scanner;
import java.lang.Long;
import java.lang.Integer;
import java.lang.Double;
import java.lang.Math;
import static java.lang.System.out;
import static java.lang.System.arraycopy;

class RSA {
	public static String[] balanceTwoStrings(String x1, String x2) {
		String[] s = new String[2];
		int l1 = x1.length();
		int l2 = x2.length();
		int dif = Math.abs(l1 - l2);
		String zeroes = "";

		if (l2 != l1) {
			for (int i = 0; i < dif; i++) {
				zeroes = zeroes.concat("0");
			}

			if (l1 > l2) {
				x2 = zeroes.concat(x2);
			}
			else {
				x1 = zeroes.concat(x1);
			}

			s[0] = x1;
			s[1] = x2;

			return s;
		}
		else {
			s[0] = x1;
			s[1] = x2;

			return s;
		}
	}

	public static String[] constructOneDimensionalStringArrayFromString(String n, int numberOfDigits) {
		int length = n.length();
		int numberOfCols = length / numberOfDigits;

		if (length % numberOfDigits > 0) { // there is a reminder
			numberOfCols += 1;
	 	}

	 	String[] stringArray = new String[numberOfCols];
	 	int i = length;

	 	while (i > 0) {
	 		if (i > numberOfDigits) {
	 			stringArray[numberOfCols - 1] = n.substring(i - numberOfDigits, i);
	 			numberOfCols--;
	 			i -= numberOfDigits;
	 		}
	 		else {
	 			stringArray[0] = n.substring(0, i);
	 			break;
	 		}

	 	}
	 	return stringArray;
	}

	public static long[] constructOneDimensionalArrayFromString(String n, int numberOfDigits) {
		int length = n.length();
		int numberOfCols = length / numberOfDigits;

		if (length % numberOfDigits > 0) { // there is a reminder
			numberOfCols += 1;
	 	}

	 	long[] longArray = new long[numberOfCols];
	 	int i = length;

	 	while (i > 0) {
	 		if (i > numberOfDigits) {
	 			longArray[numberOfCols - 1] = Long.parseLong( n.substring(i - numberOfDigits, i) );
	 			numberOfCols--;
	 			i -= numberOfDigits;
	 		}
	 		else {
	 			longArray[0] = Long.parseLong( n.substring(0, i) );
	 			break;
	 		}

	 	}
	 	return longArray;
	}

	public static boolean checkIfAllZeroes(String x) {
		int c = 0;
		int count = x.length();

		if ( x.equals("0") ) {
			return true;
		}

		while ( x.substring(c, c + 1).equals("0") ) {
			c++;

			if (c == count) {
				return true;
			}
		}

		return false;
	}

	// sum two big numbers
	public static String sumTwoBigNumbers(String x1, String x2, int div) {
		if ( checkIfAllZeroes(x1) && checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if ( checkIfAllZeroes(x2) ) {
			return x1;
		}
		else if ( checkIfAllZeroes(x1) ) {
			return x2;
		}

		String x11 = x1.substring(0, 1);
		String x22 = x2.substring(0, 1);

		if ( x11.equals("-") && x22.equals("-") ) {
			return "-".concat( sumTwoBigNumbers(x1.substring( 1, x1.length() ), x2.substring( 1, x2.length() ), 18) );
		}
		else if ( x11.equals("-") ) {
			return subtractTwoBigNumbers(x2, x1.substring( 1, x1.length() ), 18);
		}
		else if ( x22.equals("-") ) {
			return subtractTwoBigNumbers(x1, x2.substring( 1, x2.length() ), 18);
		}
		else {
			String output = "";
			String[] s = balanceTwoStrings(x1, x2);
			long[] arr1 = constructOneDimensionalArrayFromString(s[0], div);
			long[] arr2 = constructOneDimensionalArrayFromString(s[1], div);
			int length = arr1.length;
			int len1 = Long.toString(arr1[length - 1]).length();
			int len2 = Long.toString(arr2[length - 1]).length();
			
			for (int j = length - 1; j >= 0; j--) {
				if (j == 0) {
					if (len1 >= len2) {
						div = len1;
					}
					else {
						div = len2;
					}
				}

				String m = Long.toString(arr1[j] + arr2[j]);
				String m1 = m;
				int len = m.length();
				int g = j - 1;

				if (len > div && g >= 0) {
					m1 = m.substring( 1, m.length() );
					arr1[g]++;
					len = Long.toString(arr1[g]).length();

					while (len > div && g >= 0) {
						String p = Long.toString(arr1[g]);
						arr1[g] = Long.parseLong( p.substring(1, p.length() ) );
						g--;
						arr1[g]++;
						len = Long.toString(arr1[g]).length();
					}
				}

				int dif = div - m1.length();

				while (dif > 0 && j != 0) {
					m1 = "0".concat(m1);
					dif--;
				}

				output = m1.concat(output);
			}

			return output;
		}
	}

	public static String subtractTwoBigNumbers(String x1, String x2, int div) {
		if ( checkIfAllZeroes(x1) && checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if ( checkIfAllZeroes(x1) ) {
			if ( x2.substring(0, 1).equals("-") ) {
				return x2.substring(1, x2.length() );
			}
			else {
				return "-".concat(x2);
			}
		}
		else if ( checkIfAllZeroes(x2) ) {
			return x1;
		}

		String x11 = x1.substring(0, 1);
		String x22 = x2.substring(0, 1);

		if (x11.equals("-") && x22.equals("-") ) {
			return subtractTwoBigNumbers(x2.substring( 1, x2.length() ), x1.substring( 1, x1.length() ), div);
		}
		else if (x11.equals("-") ) {
			return "-".concat( sumTwoBigNumbers(x2, x1.substring( 1, x1.length() ), div) );
		}
		else if (x22.equals("-") ) {
			return sumTwoBigNumbers(x1, x2.substring( 1, x2.length() ), div);
		}
		else {
			String output = "";
			String[] s = balanceTwoStrings(x1, x2);	
			long[] arr1 = constructOneDimensionalArrayFromString(s[0], div);
			long[] arr2 = constructOneDimensionalArrayFromString(s[1], div);
			int length = arr1.length;
			int whichIsBigger = compareFirstStringWithTheSecond(x1, x2);
			int len1 = Long.toString(arr1[length - 1]).length();
			int len2 = Long.toString(arr2[length - 1]).length();

			if (whichIsBigger == 2) { // swap arrays
				long[] arr3 = arr1;
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

				long y1 = arr1[j];
				long y2 = arr2[j];
				String res = "";

				if (y1 < y2) {
					int g = j - 1;
					
					if (arr1[g] != 0) {
						arr1[g]--;
					}
					else {
						arr1[g] = Long.parseLong( addNinesIfNeeded(div) );
						int k = g - 1;
						
						while (arr1[k] == 0) {
							arr1[k] = Long.parseLong( addNinesIfNeeded(div) );
							k--;
						}

						arr1[k]--;
					}

					y1 = Long.parseLong( "1".concat( addZeroesIfNeeded(Long.toString(y1), div) ) );
				}

				res = addZeroesIfNeeded(Long.toString(y1 - y2), div);
				output = res.concat(output);
			}

			while (!output.equals("0") && output.substring(0, 1).equals("0") ) {
				output = output.substring(1, output.length() );
			}

			if (whichIsBigger == 2) {
				output = "-".concat(output);
			}

			return output;
		}
	}

	public static String addNinesIfNeeded(int arrElementLength) {
		String x = "";

		while (arrElementLength > 0) {
			x = "9".concat(x);
			arrElementLength--;
		}

		return x;
	}

	public static String addZeroesIfNeeded(String res, int arrElementLength) {
		int dif = arrElementLength - res.length();

		while (dif > 0) {
			res = "0".concat(res);
			dif--;
		}

		return res;
	}

	public static String multiplyTwoBigNumbers(String x1, String x2, int div) {
		String[] s = balanceTwoStrings(x1, x2);	
		long[] arr1 = constructOneDimensionalArrayFromString(s[0], div);
		long[] arr2 = constructOneDimensionalArrayFromString(s[1], div);
		int length = arr1.length;
		int d1 = 0;
		int d2 = 0;
		String output = "0";

		for (int i = length - 1; i >= 0; i--) {
			Long r = arr1[i];

			for (int j = length - 1; j >= 0; j--) {
				output = sumTwoBigNumbers(output, multiplyTens(Long.toString(r * arr2[j]), d1 + d2), 18);
				d2 += div;
			}

			d1 += div;
			d2 = 0;
		}

		return output;
	}

	public static String multiplyTens(String m, int num) {
		while (num > 0) {
			m = m.concat("0");
			num--;
		}

		return m;
	}

	public static String longDivision(String x1, String x2) { // optimize if possible
		if ( checkIfAllZeroes(x1) ) {
			return "0";
		}
		else if ( x1.substring(0, 1).equals("-") ) {
			return "-".concat( longDivision(x1.substring( 1, x1.length() ), x2) );
		}

		String output = "";
		String sub = "";
		int ind = 0;
		long diviend = Long.parseLong( x1.substring(0, 1) );
		long divisor = Long.parseLong(x2);

		while ( ind < x1.length() ) {
			int flag = 0;

			while (diviend < divisor) {
				if (flag >= 1) {
					ind++;
					output = output.concat("0");
				}

				if ( ind + 1 > x1.length() ) {
					break;
				}

				sub = sub.concat( x1.substring(ind, ind + 1) );

				if ( ind + 1 == x1.length() ) {
					return removeTrailingZeroes( output.concat( Long.toString(Long.parseLong(sub) / divisor) ) );
				}

				diviend = Long.parseLong(sub);
				flag++;
			}

			output = output.concat( Long.toString(diviend / divisor) );
			Long mod = diviend % divisor;
			sub = Long.toString(mod);
			diviend = mod;
			ind++;
		}

		return removeTrailingZeroes(output);
	}

	public static String removeTrailingZeroes(String output) {
		while ( !output.equals("0") && output.substring(0, 1).equals("0") ) {
			output = output.substring( 1, output.length() );
		}

		return output;
	}

	public static boolean isOdd(String x) {
		int len = x.length();

		if (Integer.parseInt( x.substring(len - 1, len) ) % 2 == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isEven(String x) {
		int len = x.length();

		if (Integer.parseInt( x.substring(len - 1, len) ) % 2 == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static int compareFirstStringWithTheSecond(String s1, String s2) {
		if ( s1.equals(s2) ) { // equal strings
			return 0;
		}
		else {
			if ( s1.substring(0, 1).equals("-") && s2.substring(0, 1).equals("-") ) { // both negative
				int x = compareFirstStringWithTheSecond( s1.substring( 1, s1.length() ), s2.substring( 1, s2.length() ) );

				if (x == 1) {
					return 2;
				}
				else {
					return 1;
				}
			}
			else if ( !s1.equals("") && s1.substring(0, 1).equals("-") ) { // only first is negative so it's smaller
				return 2;
			}
			else if ( !s2.equals("") && s2.substring(0, 1).equals("-") ) { // only second is negative so it's smaller
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
					while ( s1.substring(0, 1).equals( s2.substring(0, 1) ) ) {
						s1 = s1.substring(1, s1.length() );
						s2 = s2.substring(1, s2.length() );
					}

					if ( Integer.parseInt( s1.substring(0, 1) ) > Integer.parseInt( s2.substring(0, 1) ) ) {
						return 1;
					}
					else {
						return 2;
					}
				}
			}
		}
	}

	public static int[] concat(int[] a, int[] b) {
		int aLen = a.length;
		int bLen = b.length;
		int[] c = new int[aLen + bLen];
		arraycopy(a, 0, c, 0, aLen);
		arraycopy(b, 0, c, aLen, bLen);

		return c;
	}

	public static int[] convertBigNumberToBinary(String x) {
		int i = 0;
		String out = "1";
		int[] bin = new int[1];

		if ( x.equals("0") ) {
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
		String y = subtractTwoBigNumbers(x, out, 18);

		return concat( bin, convertBigNumberToBinary(y) );
	}

	public static String repeatMultiply(String x, String num) {
		String out = "1";
		String i = "0";
		String mod = mod(num, "2");
		String anothernum = longDivision(num, "2");

		if ( !anothernum.equals("0") ) {
			while (compareFirstStringWithTheSecond(i, anothernum) == 2) {
				out = karatsubaMultiplication(out, x);
				i = sumTwoBigNumbers(i, "1", 18);
			}

			out = karatsubaMultiplication(out, out);
		}
		else {
			if ( mod.equals("0") ) {
				return "1";
			}
			else  {
				out = karatsubaMultiplication(out, x);
			}
		}

		return out;
	}

	public static String repeatMultiplyByTwo(double num) {
		if (num < 56) {
			return Integer.toString( (int)Math.pow(2, num) );
		}

		double pre = Math.pow(2, num);

		if (num == 56) {
			return Integer.toString( (int)pre );
		}

		String out = "1";
		double num1 = num - 56;

		while (num1 >= 56) {
			out = karatsubaMultiplication(out, Integer.toString( (int)pre ) );
			num1 -= 56;
		}

		if (num1 != 0) {
			double a = Math.pow(2, num1);
			out = karatsubaMultiplication(out, Integer.toString( (int)a ) );
		}

		return out;
	}

	public static String mod(String x, String n) { // optimize if possible
		while ( x.substring(0, 1).equals("-") ) {
			x = sumTwoBigNumbers(x, n, 18);
		}

		if (n.equals("1")) {
			return "0";
		}

		String out = x;
		int a = compareFirstStringWithTheSecond(x, n);

		if (a == 2) {
			return x;
		}
		else if (a == 0) {
			return "0";
		}
		else {
			String sub = multiplyTens( n, x.length() - n.length() );
			int f = compareFirstStringWithTheSecond(sub, out);

			while (true) {
				while (f == 1) {
					sub = sub.substring(0, sub.length() - 1);
					f = compareFirstStringWithTheSecond(sub, out);
				}

				while (f == 2) {
					out = subtractTwoBigNumbers(out, sub, 18);
					f = compareFirstStringWithTheSecond(sub, out);
				}

				if ( out.equals(n) || f == 0) {
					return "0";
				}
				else if (compareFirstStringWithTheSecond(out, n) == 2) {
					return out;
				}
			}
		}
	}

	// karatsuba algorithm
	public static String karatsubaMultiplication(String x1, String x2) {
		if ( checkIfAllZeroes(x1) || checkIfAllZeroes(x2) ) {
			return "0";
		}
		else if ( x1.equals("1") ) {
			return x2;
		}
		else if ( x2.equals("1") ) {
			return x1;
		}
		else if ( x1.equals("-1") ) {
			if (x2.substring(0, 1).equals("-")) {
				return x2.substring(1, x2.length());
			}
			else {
				return "-".concat(x2);
			}
		}
		else if ( x2.equals("-1") ) {
			if (x1.substring(0, 1).equals("-")) {
				return x1.substring(1, x1.length());
			}
			else {
				return "-".concat(x1);
			}
		}

		String x11 = x1.substring(0, 1);
		String x22 = x2.substring(0, 1);

		if ( x11.equals("-") && x22.equals("-") ) {
			return karatsubaMultiplication( x1.substring( 1, x1.length() ), x2.substring( 1, x2.length() ) );
		}
		else if ( x11.equals("-") ) {
			return "-".concat( karatsubaMultiplication(x1.substring( 1, x1.length() ), x2) );
		}
		else if ( x22.equals("-") ) {
			return "-".concat( karatsubaMultiplication( x1, x2.substring( 1, x2.length() ) ) );
		}
		else if (compareFirstStringWithTheSecond(x1, "15") == 2) {
			String out = x2;
			int i = Integer.parseInt(x1) - 1;

			for (int j = 0; j < i; j++) {
				out = sumTwoBigNumbers(out, x2, 18);
			}

			return out;
		}
		else if (compareFirstStringWithTheSecond(x2, "15") == 2) {
			String out = x1;
			int i = Integer.parseInt(x2) - 1;

			for (int j = 0; j < i; j++) {
				out = sumTwoBigNumbers(out, x1, 18);
			}

			return out;
		}
		else {
			String[] s = balanceTwoStrings(x1, x2);
			x1 = s[0];
			x2 = s[1];

			if (x1.length() % 2 == 1) {
				x1 = "0".concat(x1);
				x2 = "0".concat(x2);
			}

			if (x1.length() <= 9) {
				return Long.toString( Long.parseLong(x1) * Long.parseLong(x2) );
			}
			else if (x1.length() <= 25) { // number between 20 and 30
				return multiplyTwoBigNumbers(x1, x2, 9);
			}
			else {
				int len = x1.length(); // or x2.length()
				int h = len / 2;
				String w1 = x1.substring(0, h);
				String w2 = x1.substring(h, len);
				String w3 = x2.substring(0, h);
				String w4 = x2.substring(h, len);
				String a1 = karatsubaMultiplication(w1, w3);
				String d1 = karatsubaMultiplication(w2, w4);
				String m1 = sumTwoBigNumbers(w1, w2, 18);
				String m2 = sumTwoBigNumbers(w3, w4, 18);
				String m3 = karatsubaMultiplication(m1, m2);
				String m4 = subtractTwoBigNumbers(m3, a1, 18);
				String e1 = subtractTwoBigNumbers(m4, d1, 18);

				return sumTwoBigNumbers( sumTwoBigNumbers( multiplyTens(a1, len), d1, 18 ), multiplyTens(e1, h), 18 );
			}
		}
	}

	public static String divide(String x1, String x2) {
		if (x2.length() <= 18) {
			return longDivision(x1, x2);
		}

		if ( x1.equals(x2) ) {
			return "1";
		}
		else if (compareFirstStringWithTheSecond(x1, x2) == 2) {
			return "0";
		}

		String x11 = x1.substring(0, 1);
		String x22 = x2.substring(0, 1);

		if ( x11.equals("-") && x22.equals("-") ) {
			return divide( x1.substring( 1, x1.length() ), x2.substring( 1, x2.length() ) );
		}
		else if ( x11.equals("-") ) {
			return "-".concat( divide(x1.substring( 1, x1.length() ), x2) );
		}
		else if ( x22.equals("-") ) {
			return "-".concat( divide( x1, x2.substring( 1, x2.length() ) ) );
		}
		else {
			int dif = x1.length() - x2.length();
			String tens = "1";
			
			for (int p = 0; p < dif; p++) {
				tens = tens.concat("0");
			}

			String anotherx1 = x1;
			String sub = multiplyTens(x2, dif);
			String out = "0";
		
			while (true) {
				while (compareFirstStringWithTheSecond(anotherx1, sub) == 1) { // anotherx1 > sub
					out = sumTwoBigNumbers(out, tens, 18);
					anotherx1 = subtractTwoBigNumbers(anotherx1, sub, 18);				
				}

				while (compareFirstStringWithTheSecond(anotherx1, sub) == 2) { // anotherx1 < sub
					sub = sub.substring(0, sub.length() - 1);

					if (tens.length() > 0) {
						tens = tens.substring(0, tens.length() - 1);
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

	public static int integerExtendedEuclid(int x, int n) {
		int a1 = 1;
		int a2 = 0;
		int a3 = n;
		int b1 = 0;
		int b2 = 1;
		int b3 = x;
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int q = 0;

		while (true) {
			if (b3 == 0) {
				return -1;
			}

			if (b3 == 1) {
				while (b2 < 0) {
					b2 += n;
				}

				return b2;
			}

			q = a3 / b3;

			t1 = a1 - q * b1;
			t2 = a2 - q * b2;
			t3 = a3 - q * b3;

			a1 = b1;
			a2 = b2;
			a3 = b3;

			b1 = t1;
			b2 = t2;
			b3 = t3;
		}
	}

	// use this for decryption
	public static String enhancedModularPower(String x, String m, String n, String p, String q) { // x^m mod n
		// String vp = recursiveModularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p);
		// String vq = recursiveModularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q);

		String Rf = getR(p);
		String RInverse1 = extendedEuclid(Rf, p);
		int zeroes1 = Rf.length() - 1;
		String k1 = getK(Rf, RInverse1, p);
		String modKR1 = modR(k1, Rf);

		String Rs = getR(q);
		String RInverse2 = extendedEuclid(Rs, q);
		int zeroes2 = Rs.length() - 1;
		String k2 = getK(Rs, RInverse2, q);
		String modKR2 = modR(k2, Rs);
		
		String vp = modularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p, zeroes1, RInverse1, Rf, modKR1);
		String vq = modularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q, zeroes2, RInverse2, Rs, modKR2);
		//String vp = anModularPower(x, mod( m, subtractTwoBigNumbers(p, "1", 18) ), p);
		//String vq = anModularPower(x, mod( m, subtractTwoBigNumbers(q, "1", 18) ), q);
		String xp = karatsubaMultiplication( q, extendedEuclid(q, p) );
		String xq = karatsubaMultiplication( p, extendedEuclid(p, q) );

		return mod( sumTwoBigNumbers( karatsubaMultiplication(vp, xp), karatsubaMultiplication(vq, xq), 18 ), n );
	}

	// use this for encryption
	public static String modularPower(String x, String m, String n, int zeroes, String RInverse, String R, String modKR) { // x^m mod n
		if (n.equals("0")) {
			return "mod 0 is undefined";
		}
		else if (m.equals("0")) {
			return "1";
		}
		else if (m.equals("1")) {
			return mod(x, n);
		}

		int[] bin = convertBigNumberToBinary(m); // binary representation of exponent m
		int len = bin.length;
		String last = montgomeryForm(repeatMultiply( x, repeatMultiplyByTwo( (double)(bin[len - 1]) ) ), n, R);
		String out = last;		
		
		for (int i = len - 2; i >= 0; i--) {
			last = montgomeryModularRepeatMultiply(last, bin[i] - bin[i + 1], n, zeroes, RInverse, R, modKR);
			out = montgomeryMultiplication(out, last, n, zeroes, R, modKR);
		}

		return mod( karatsubaMultiplication(out, RInverse), n);
	}

	// public static String anModularPower(String x, String m, String n) { // x^m mod n
	// 	String a = mod(m, "2");

	// 	if ( !a.equals("0") ) {
	// 		String b = longDivision(m, "2");

	// 		if (compareFirstStringWithTheSecond("65537", b) <= 1) {
	// 			String out = modularPower(x, b, n);

	// 			return mod(karatsubaMultiplication( mod(x, n), mod(karatsubaMultiplication(out, out), n) ), n);
	// 		}
	// 		else {
	// 			String j = anModularPower(x, b, n);

	// 			return mod(karatsubaMultiplication( mod(x, n), mod(karatsubaMultiplication(j, j), n) ), n);
	// 		}
	// 	}
	// 	else {
	// 		String b = longDivision(m, "2");

	// 		if (compareFirstStringWithTheSecond("65537", b) <= 1) {
	// 			String out = modularPower(x, b, n);

	// 			return mod(karatsubaMultiplication(out, out), n);
	// 		}
	// 		else {
	// 			String j = anModularPower(x, b, n);
				
	// 			return mod(karatsubaMultiplication(j, j), n);
	// 		}
	// 	}
		
	// }

	// public static String recursiveModularPower(String x, String m, String n) {
	// 	String a = mod(m, "2");

	// 	if ( !a.equals("0") ) {
	// 		String b = longDivision(m, "2");

	// 		if (compareFirstStringWithTheSecond("65537", b) <= 1) {
	// 			String f1 = modularPower(x, b, n);

	// 			return mod(karatsubaMultiplication( mod(x, n), mod(karatsubaMultiplication(f1, f1), n) ), n);
	// 		}
	// 		else {
	// 			String j = recursiveModularPower(x, b, n);

	// 			return mod(karatsubaMultiplication( mod(x, n), mod(karatsubaMultiplication(j, j), n) ), n);
	// 		}
	// 	}
	// 	else {
	// 		String b = longDivision(m, "2");

	// 		if (compareFirstStringWithTheSecond("65537", b) <= 1) {
	// 			String f1 = modularPower(x, b, n);

	// 			return mod(karatsubaMultiplication(f1, f1), n);
	// 		}
	// 		else {
	// 			String j = recursiveModularPower(x, b, n);
				
	// 			return mod(karatsubaMultiplication(j, j), n);
	// 		}
	// 	}
	// }

	public static String modularRepeatMultiply(String x, int dif, String n) {
		if (dif == 0) {
			return "1";
		}

		while (dif > 0) {
			x = mod(karatsubaMultiplication(x, x), n);
			dif--;
		}
		
		return x;
	}

	public static String montgomeryForm(String x, String n, String R) {		
		return mod( karatsubaMultiplication( mod(x, n), mod(R, n) ), n );
	}
	
	public static String getR(String n) {
		String R = multiplyTens( "1", n.length() - 1);

		if (compareFirstStringWithTheSecond(R, n) == 2) {
			R = R.concat("0");
		}

		return R;
	}

	public static String modR(String x, String R) {
		while ( x.substring(0, 1).equals("-") ) {
			x = sumTwoBigNumbers(x, R, 18);
		}

		if (compareFirstStringWithTheSecond(x, R) == 2) {
			return x;
		}
		else if ( x.equals(R) ) {
			return "0";
		}
		else {
			int len = x.length();

			return x.substring(len - R.length(), len);
		}
	}

	public static String montgomeryMultiplication(String x1, String x2, String n, int zeroes, String R, String modKR) {
		x1 = karatsubaMultiplication(x1, x2); // x1 * x2
		x1 = sumTwoBigNumbers( x1, karatsubaMultiplication( modR(karatsubaMultiplication( modR(x1, R), modKR ), R), n ), 18 );
		x1 = x1.substring(0, x1.length() - zeroes);

		if (compareFirstStringWithTheSecond(n, x1) == 1) {
			return x1;
		}
		else if ( n.equals(x1) ) {
			return "0";
		}
		else {
			//return subtractTwoBigNumbers(x1, n, 18);
			return mod(x1, n);
		}
	}

	public static String getK(String R, String RInverse, String n) {
		return divide(subtractTwoBigNumbers(karatsubaMultiplication(R, RInverse), "1", 18), n);
	}

	public static String montgomeryModularRepeatMultiply(String x, int dif, String n, int zeroes, String RInverse, String R, String modKR) { // x1^2 mod n
		if (dif == 0) {
			return "1";
		}

		while (dif > 0) {
			x = montgomeryMultiplication(x, x, n, zeroes, R, modKR);
			dif--;
		}

		return x;
	}

	public static String toomCook(String x1, String x2) {
		String[] x = balanceTwoStrings(x1, x2);

		x1 = x[0];
		x2 = x[1];

		int len = x1.length();

		if (len > 54) { // recursively call toomCook again
			String secondx1 = x1.substring(len - 54, len);
			String secondx2 = x2.substring(len - 54, len);
			String firstx1 = x1.substring(0, len - 54);
			String firstx2 = x2.substring(0, len - 54);
			String h1 = toomCook(secondx2, secondx1);
			String h2 = multiplyTens(toomCook(firstx2, firstx1), 108);
			String h3 = toomCook(firstx1, secondx2);
			String h4 = toomCook(firstx2, secondx1);
			String s1 = sumTwoBigNumbers(h1, h2, 18);
			String s2 = multiplyTens(sumTwoBigNumbers(h3, h4, 18), 54);

			return sumTwoBigNumbers(s1, s2, 18);
		}
		else {
			String[] m1 = constructOneDimensionalStringArrayFromString(x1, 18);
			String[] m2 = constructOneDimensionalStringArrayFromString(x2, 18);

			if (m1.length == 3) {
				String m11 = removeTrailingZeroes(m1[0]);
				String m22 = removeTrailingZeroes(m1[1]);
				String p0 = removeTrailingZeroes(m1[2]);
				String p00 = sumTwoBigNumbers(m11, p0, 18);
				String p1 = sumTwoBigNumbers(p00, m22, 18);
				String pn1 = subtractTwoBigNumbers(p00, m22, 18);
				String sub1 = sumTwoBigNumbers(pn1, m11, 18);
				String pn2 = subtractTwoBigNumbers(sumTwoBigNumbers(sub1, sub1, 18), p0, 18);

				String m33 = removeTrailingZeroes(m2[0]);
				String m44 = removeTrailingZeroes(m2[1]);
				String q0 = removeTrailingZeroes(m2[2]);
				String q00 = sumTwoBigNumbers(m33, q0, 18);
				String q1 = sumTwoBigNumbers(q00, m44, 18);
				String qn1 = subtractTwoBigNumbers(q00, m44, 18);
				String sub2 = sumTwoBigNumbers(qn1, m33, 18);
				String qn2 = subtractTwoBigNumbers(sumTwoBigNumbers(sub2, sub2, 18), q0, 18);

				String r0 = karatsubaMultiplication(p0, q0);
				String r1 = karatsubaMultiplication(p1, q1);
				String rn1 = karatsubaMultiplication(pn1, qn1);
				String rn2 = karatsubaMultiplication(pn2, qn2);
				String rinf = karatsubaMultiplication(m11, m33);

				String r3 = longDivision(subtractTwoBigNumbers(rn2, r1, 18), "3");
				String r11 = longDivision(subtractTwoBigNumbers(r1, rn1, 18), "2");
				String r2 = subtractTwoBigNumbers(rn1, r0, 18);
				r3 = sumTwoBigNumbers(longDivision(subtractTwoBigNumbers(r2, r3, 18), "2"), sumTwoBigNumbers(rinf, rinf, 18), 18);
				r2 = subtractTwoBigNumbers(sumTwoBigNumbers(r2, r11, 18), rinf, 18);
				r11 = subtractTwoBigNumbers(r11, r3, 18);
				
				// String sum1 = "0";
				// String sum2 = "0";
				// String sum3 = "0";
				// String sum4 = "0";

				// int len1 = r0.length();
				
				// if (len1 > 18) {
				// 	sum1 = sumTwoBigNumbers(r11, r0.substring(0, len1 - 18), 18).concat(r0.substring(len1 - 18, len1) );
				// }
				// else {
				// 	sum1 = sumTwoBigNumbers(r11, r0, 18);
				// }

				// int len2 = sum1.length();

				// if (len2 > 36) {
				// 	sum2 = sumTwoBigNumbers(r2, sum1.substring(0, len2 - 36), 18).concat(sum1.substring(len2 - 36, len2) );
				// }
				// else {
				// 	sum2 = sumTwoBigNumbers(r2, sum1, 18);
				// }
				
				// int len3 = sum2.length();

				// if (len3 > 54) {
				// 	sum3 = sumTwoBigNumbers(r3, sum2.substring(0, len3 - 54), 18).concat(sum2.substring(len3 - 54, len3) );
				// }
				// else {
				// 	sum3 = sumTwoBigNumbers(r3, sum2, 18);
				// }
				
				// int len4 = sum3.length();

				// if (len4 > 72) {
				// 	sum4 = sumTwoBigNumbers(rinf, sum3.substring(0, len4 - 72), 18).concat(sum3.substring(len4 - 72, len4) );
				// }
				// else {
				// 	sum4 = sumTwoBigNumbers(rinf, sum3, 18);
				// }
				String sum1 = sumTwoBigNumbers(multiplyTens(r11, 18), multiplyTens(r2, 36), 18);
				String sum2 = sumTwoBigNumbers(multiplyTens(r3, 54), multiplyTens(rinf, 72), 18);

				return sumTwoBigNumbers(sum1, sumTwoBigNumbers(sum2, r0, 18), 18);
			}
			else if (m1.length == 2) {
				String m22 = removeTrailingZeroes(m1[0]);
				String p0 = removeTrailingZeroes(m1[1]);
				String p1 = sumTwoBigNumbers(p0, m22, 18);
				String pn1 = subtractTwoBigNumbers(p0, m22, 18);
				String pn2 = subtractTwoBigNumbers(sumTwoBigNumbers(pn1, pn1, 18), p0, 18);

				String m44 = removeTrailingZeroes(m2[0]);
				String q0 = removeTrailingZeroes(m2[1]);
				String q1 = sumTwoBigNumbers(q0, m44, 18);
				String qn1 = subtractTwoBigNumbers(q0, m44, 18);
				String qn2 = subtractTwoBigNumbers(sumTwoBigNumbers(qn1, qn1, 18), q0, 18);

				String r0 = karatsubaMultiplication(p0, q0);
				String r1 = karatsubaMultiplication(p1, q1);
				String rn1 = karatsubaMultiplication(pn1, qn1);
				String rn2 = karatsubaMultiplication(pn2, qn2);
				String r3 = longDivision(subtractTwoBigNumbers(rn2, r1, 18), "3");
				String r11 = longDivision(subtractTwoBigNumbers(r1, rn1, 18), "2");
				String r2 = subtractTwoBigNumbers(rn1, r0, 18);
				r3 = longDivision(subtractTwoBigNumbers(r2, r3, 18), "2");
				r2 = sumTwoBigNumbers(r2, r11, 18);
				r11 = subtractTwoBigNumbers(r11, r3, 18);

				// String sum1 = "0";
				// String sum2 = "0";
				// String sum3 = "0";

				// int len1 = r0.length();

				// if (len1 > 18) {
				// 	sum1 = sumTwoBigNumbers(r11, r0.substring(0, len1 - 18), 18).concat(r0.substring(len1 - 18, len1) );
				// }
				// else {
				// 	sum1 = sumTwoBigNumbers(r11, r0, 18);
				// }

				// int len2 = sum1.length();
				
				// if (len2 > 36) {
				// 	sum2 = sumTwoBigNumbers(r2, sum1.substring(0, len2 - 36), 18).concat(sum1.substring(len2 - 36, len2) );
				// }
				// else {
				// 	sum2 = sumTwoBigNumbers(r2, sum1, 18);
				// }

				// int len3 = sum2.length();

				// if (len3 > 54) {
				// 	sum3 = sumTwoBigNumbers(r3, sum2.substring(0, len3 - 54), 18).concat(sum2.substring(len3 - 54, len3) );
				// }
				// else {
				// 	sum3 = sumTwoBigNumbers(r3, sum2, 18);
				// }

				String sum1 = sumTwoBigNumbers(multiplyTens(r11, 18), multiplyTens(r2, 36), 18);
				String sum2 = multiplyTens(r3, 54);

				return sumTwoBigNumbers(sum1, sumTwoBigNumbers(sum2, r0, 18), 18);

				//return sum3;
			}
			else if (m1.length == 1) {
				return karatsubaMultiplication( removeTrailingZeroes(m1[0]), removeTrailingZeroes(m2[0]) );
			}
			else {
				return "0";
			}
			// else {
			// 	while (x1.length() < 54) {
			// 		x1 = "0".concat(x1);
			// 		x2 = "0".concat(x2);
			// 	}

			// 	return toomCook(x1, x2);
			// }
		}
	}

	public static String testPrimality(String n, int numberOfTests) {

		if ( isEven(n) || ( !n.equals("5") && n.substring(n.length() - 1, n.length()).equals("5") ) ) {
			return "composite";
		}
		else if (n.equals("5") || n.equals("3") || n.equals("2")) {
			return "inconclusive";
		}
		// miller-rabin algorithm
		// divide by 2 until we get odd result
		String n1 = subtractTwoBigNumbers(n, "1", 18);
		String q = n1;
		int k = 0;
		String a = "2";
		int f = numberOfTests;

		while ( isEven(q) ) {
			q = longDivision(q, "2");
			k++;
		}

		String r = getR(n);
		String rInverse = extendedEuclid(r, n);
		String nInverse = "-".concat( extendedEuclid(n, r) );
		String modnInverseWithR = modR(nInverse, r);
		int zeroes = r.length() - 1;

		while (numberOfTests > 0) { // perform test multiple times
			String y = modularPower(a, q, n, zeroes, rInverse, r, modnInverseWithR);

			if ( y.equals("1") || y.equals(n1) ) {
				f--;
				out.println("asd");
			}
			else {
				String g = y;

				for (int j = 1; j < k; j++) {
					int b = (int)Math.pow(2, j);

					for (int i = 0; i < b - 1; i++) {
						y = mod(karatsubaMultiplication(y, g), n);
					}

					if ( y.equals(n1) ) {
						f--;
						out.println("df");
						break;
					}
					else {
						y = g;
					}
				}
			}

			numberOfTests--;

			if (numberOfTests > 0) {
				a = Integer.toString(Integer.parseInt(a) + 1);
			}
		}

		if (f == 0) {
			return "inconclusive";
		}
		else {
			return "composite";
		}
	}

	public static String extendedEuclid(String x, String n) { // x^-1 mod n
		String a1 = "1";
		String a2 = "0";
		String a3 = n;
		String b1 = "0";
		String b2 = "1";
		String b3 = x;
		String t1 = "";
		String t2 = "";
		String t3 = "";
		String q = "";

		if (b3.length() < 18) {
			while (true) {
				if ( b3.equals("0") ) {
					return "no inverse";
				}

				if ( b3.equals("1") ) {
					while ( b2.substring(0, 1).equals("-") ) {
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
				if ( b3.equals("0") ) {
					return "no inverse";
				}

				if ( b3.equals("1") ) {
					if ( b2.substring(0, 1).equals("-") ) {
						b2 = subtractTwoBigNumbers(n, b2.substring( 1, b2.length() ), 18);
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

	public static String repeatStringMultiplyByTwo(String num) {
		if (num.equals("0")) {
			return "1";
		}

		String out = "1";
		int comp = compareFirstStringWithTheSecond(num, "23");
		
		if (comp == 2 || comp == 0) {
			double pre = Double.parseDouble(num);

			return Integer.toString( (int)Math.pow(2, pre) );
		}
		else {
			String num1 = subtractTwoBigNumbers(num, "23", 18);

			while (compareFirstStringWithTheSecond(num1, "23") == 1) {
				out = karatsubaMultiplication(out, num);
				num1 = subtractTwoBigNumbers(num1, "23", 18);
			}

			if (!num1.equals("0") ) {
				int a = (int)Math.pow(2, Double.parseDouble(num1) );
				out = karatsubaMultiplication(out, Integer.toString(a) );
			}

			return out;
		}
	}

	public static void main(String[] args) {
		String p = "12369571528747655798110188786567180759626910465726920556567298659370399748072366507234899432827475865189642714067836207300153035059472237275816384410077871";
		String q = "2065420353441994803054315079370635087865508423962173447811880044936318158815802774220405304957787464676771309034463560633713497474362222775683960029689473";
		String e = "65537";
		//out.println(testPrimality(e, 3));

		// String x1 = toomCook(p, q);
		// String x2 = karatsubaMultiplication(p, q);

		// out.println(x1);
		// out.println("");
		// out.println(x2);

		// if ( x1.equals(x2) ) {
		// 	out.println("yes");
		// }

		// long cur1 = System.currentTimeMillis();

		// for (int i = 0; i < 20; i++) {
		// 	toomCook(p, q);
		// }

		// long cur2 = System.currentTimeMillis();

		// for (int i = 0; i < 20; i++) {
		// 	karatsubaMultiplication(p, q);
		// }

		// long cur3 = System.currentTimeMillis();

		// out.println( "toomCook : " + (cur2 - cur1) );
		// out.println( "karatsubaMultiplication : " + (cur3 - cur2) );




		// long cur1 = System.currentTimeMillis();
		// for (int i = 0; i < 1; i++) {
		// 	out.println(modularPower("2973507590659808609680958609854096895868609860958609685409685860960969856096095685409685498686096096095609854968586096098560985096858609860956096098549688609609609560954096854986860986096098458609860956098560984509685860960960960985460985409685486586609609609568450968459688686096096096854096854096886096098450968459680958609609609840968606096709576095760967986596875498765698549867456798576987598798798769875986754769698698698698676754676986875986769876985687598675698698675698745390287567677969869869875496875467679869867549675467698698549867679869859867598679857698576987549867969868567", "59685960958656546",karatsubaMultiplication( karatsubaMultiplication(p, q), karatsubaMultiplication(p, q) ) ) );
		// }
		// long cur2 = System.currentTimeMillis();

		// long cur3 = System.currentTimeMillis();
		// for (int i = 0; i < 1; i++) {
		// 	out.println(recursiveModularPower("2973507590659808609680958609854096895868609860958609685409685860960969856096095685409685498686096096095609854968586096098560985096858609860956096098549688609609609560954096854986860986096098458609860956098560984509685860960960960985460985409685486586609609609568450968459688686096096096854096854096886096098450968459680958609609609840968606096709576095760967986596875498765698549867456798576987598798798769875986754769698698698698676754676986875986769876985687598675698698675698745390287567677969869869875496875467679869867549675467698698549867679869859867598679857698576987549867969868567", "59685960958656546", karatsubaMultiplication( karatsubaMultiplication(p, q), karatsubaMultiplication(p, q) ) ) );
		// }
		// long cur4 = System.currentTimeMillis();

		// out.println( "modularPower : " + (cur2 - cur1) );
		// out.println( "recursivemodularPower : " + (cur4 - cur3) );

		//out.println( recursiveModularPower("2", "46565460965875", "98760809384560898068") );

		// long cur9 = System.currentTimeMillis();
		// for (int i = 0; i < 15; i++) {
		// 	sumTwoBigNumbers(p, p, 18);
		// }
		// long cur0 = System.currentTimeMillis();
		// //for (int i = 0; i < 100; i++) {
		// 	karatsubaMultiplication(p, "15");
		// //}
		// Long cur8 = System.currentTimeMillis();

		// out.println("sum is : " + (cur0 - cur9) );
		// out.println("multiplication is : " + (cur8 - cur0) );








		// n = p * q
		String n = karatsubaMultiplication(p, q);

		// phiOfn = (p - 1) * (q - 1)
		String phiOfn = karatsubaMultiplication( subtractTwoBigNumbers(p, "1", 18), subtractTwoBigNumbers(q, "1", 18) );

		// d = e^-1 mod phiOfn
		String d = extendedEuclid(e, phiOfn);

		// encryption
		String message = "97568759409682067900874649773939678036876767";
		long cur4 = System.currentTimeMillis();
		String r = getR(n);
		String rInverse = extendedEuclid(r, n);
		String nInverse = "-".concat( extendedEuclid(n, r) );
		String modnInverseWithR = modR(nInverse, r);
		int zeroes = r.length() - 1;
		//String k = getK(R, RInverse, n);
		//String modKR = modR(k, R);
		String c = modularPower(message, e, n, zeroes, rInverse, r, modnInverseWithR);
		long cur2 = System.currentTimeMillis();
		out.println("time to encrypt with public key is : " + ( (cur2 - cur4) / 1000.0) + " sec");
		
		// decryption
		long cur5 = System.currentTimeMillis();
		String m = enhancedModularPower(c, d, n, p, q);
		long cur3 = System.currentTimeMillis();
		
		out.println("time to decrypt with private key is : " + ( (cur3 - cur5) / 1000.0) + " sec");
		out.println("");

		String exp = message;
		String o = m;

		out.println(o);
		out.println("");
		
		out.println(exp);
		out.println("");

		if (o.equals(exp) ) { // check for value equality.to check if two objects has the same reference use ==
			out.println("yes");
		}
		else {
			out.println("no");
		}

		out.println("");

		// read any input to close console
		// out.println("press any key to continue .. ");
		// Scanner scanner = new Scanner(System.in);
		// scanner.nextLine();
	}
}