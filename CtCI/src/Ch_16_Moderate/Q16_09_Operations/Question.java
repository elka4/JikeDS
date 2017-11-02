package Ch_16_Moderate.Q16_09_Operations;

import CtCILibrary.AssortedMethods;

public class Question {
	/* Flip a positive sign to negative, or a negative sign to pos */
	public static int negate(int a) {
		int neg = 0;
		int newSign = a < 0 ? 1 : -1;
		while (a != 0) {
			neg += newSign;
			a += newSign;
		}
	    return neg;
	}
	
	/* Flip a positive sign to negative, or a negative sign to pos */
	public static int negateOptimized(int a) {
		int neg = 0;
		int newSign = a < 0 ? 1 : -1;
		int delta = newSign;
		while (a != 0) {
			boolean differentSigns = (a + delta > 0) != (a > 0);
			if (a + delta != 0 && differentSigns) { // If delta is too big, reset it.
				delta = newSign;
			}
			neg += delta;
			a += delta;
			delta += delta; // Double the delta
		}
	    return neg;
	}	

	/* Subtract two numbers by negating b and adding them */
	public static int minus(int a, int b) {
		return a + negate(b);
	}

	/* Return absolute value */
	public static int abs(int a) {
		if (a < 0) {
			return negateOptimized(a);
		}
		else return a;
	}

	/* Multiply a by b by adding a to itself b times */
	public static int multiply(int a, int b) {
		if (a < b) {
			return multiply(b, a); // algo is faster if b < a
		}
		int sum = 0;
		for (int i = abs(b); i > 0; i = minus(i, 1)) {
			sum += a;
		}
		if (b < 0) {
			sum = negateOptimized(sum);
		}
		return sum;
	}

	/* Divide a by b by literally counting how many times b can go into
	 * a. That is, count how many times you can add b to itself until you reach a. */
	public static int divide(int a, int b) throws ArithmeticException {
		if (b == 0) {
			throw new ArithmeticException("ERROR: Divide by zero.");
		}
		int absa = abs(a);
		int absb = abs(b);
		
		int product = 0;
		int x = 0;
		while (product + absb <= absa) { /* don't go past a */
			product += absb;
			x++;
		}
		
		if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
			return x;
		} else {
			return negateOptimized(x);
		}
	}
	
	public static void main(String[] args) {
		int minRange = -100;
		int maxRange = 100;
		int cycles = 100;
		
		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			int ans = minus(a, b);
			if (ans != a - b) {
				System.out.println("ERROR");
			}
			System.out.println(a + " - " + b + " = " + ans);
		}
		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			int ans = multiply(a, b);
			if (ans != a * b) {
				System.out.println("ERROR");
			}
			System.out.println(a + " * " + b + " = " + ans);
		}
		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			System.out.print(a + " / " + b + " = ");
			int ans = divide(a, b);
			if (ans != a / b) {
				System.out.println("ERROR");
			}
			System.out.println(ans);
		}
	}
/*
-62 - -5 = -57
20 - -95 = 115
36 - 80 = -44
-59 - 9 = -68
-98 - -70 = -28
60 - 27 = 33
79 - -7 = 86
-33 - 43 = -76
-15 - 39 = -54
-62 - -13 = -49
-2 - 39 = -41
66 - 98 = -32
65 - -66 = 131
67 - -7 = 74
66 - -42 = 108
27 - -90 = 117
-28 - -57 = 29
24 - 26 = -2
-12 - 49 = -61
73 - 23 = 50
1 - 97 = -96
27 - 85 = -58
-74 - -54 = -20
75 - 88 = -13
-25 - 93 = -118
-28 - 27 = -55
52 - -81 = 133
90 - -57 = 147
41 - 29 = 12
-64 - 25 = -89
37 - 100 = -63
-94 - 74 = -168
22 - -47 = 69
59 - 100 = -41
-72 - -69 = -3
-39 - -53 = 14
-25 - -59 = 34
-5 - 55 = -60
85 - -41 = 126
40 - -23 = 63
-98 - 40 = -138
-26 - -47 = 21
-65 - -64 = -1
7 - 81 = -74
-43 - 94 = -137
-64 - -23 = -41
-48 - 69 = -117
87 - 89 = -2
-12 - 81 = -93
87 - -94 = 181
76 - 89 = -13
33 - 88 = -55
73 - 94 = -21
47 - -45 = 92
92 - 55 = 37
20 - 29 = -9
-28 - 76 = -104
34 - -97 = 131
-100 - 93 = -193
86 - -2 = 88
68 - -55 = 123
-48 - -34 = -14
84 - 66 = 18
52 - -13 = 65
11 - -54 = 65
-71 - 34 = -105
-5 - 71 = -76
71 - 42 = 29
-61 - -74 = 13
78 - 17 = 61
90 - 90 = 0
7 - -59 = 66
-6 - 90 = -96
-91 - 60 = -151
16 - -52 = 68
-91 - -10 = -81
96 - 33 = 63
95 - 12 = 83
87 - 39 = 48
92 - -47 = 139
13 - -88 = 101
92 - 57 = 35
-46 - 60 = -106
-48 - 40 = -88
-27 - 43 = -70
28 - -22 = 50
-6 - -98 = 92
-26 - 54 = -80
-46 - 74 = -120
70 - 52 = 18
-55 - 75 = -130
-62 - -80 = 18
17 - -51 = 68
-45 - -47 = 2
-12 - 66 = -78
61 - 4 = 57
99 - -89 = 188
32 - -26 = 58
-82 - -55 = -27
18 - -67 = 85
-67 * 19 = -1273
-30 * 24 = -720
-96 * -91 = 8736
68 * 42 = 2856
36 * -29 = -1044
-2 * 16 = -32
-25 * -34 = 850
-92 * 34 = -3128
92 * 3 = 276
-77 * 23 = -1771
28 * -34 = -952
4 * -90 = -360
-3 * -76 = 228
93 * 89 = 8277
12 * 66 = 792
-87 * 30 = -2610
2 * -5 = -10
47 * -15 = -705
-30 * -47 = 1410
46 * 67 = 3082
90 * -16 = -1440
-64 * -99 = 6336
87 * 1 = 87
89 * 24 = 2136
-53 * 27 = -1431
-58 * 60 = -3480
-58 * -23 = 1334
19 * -26 = -494
-42 * -89 = 3738
68 * -65 = -4420
11 * -10 = -110
59 * -18 = -1062
86 * -86 = -7396
56 * -21 = -1176
-97 * -2 = 194
-25 * -24 = 600
-48 * -77 = 3696
88 * 27 = 2376
90 * 48 = 4320
-14 * -62 = 868
-90 * 48 = -4320
-78 * -17 = 1326
-91 * 77 = -7007
-24 * 54 = -1296
-99 * -14 = 1386
30 * 66 = 1980
100 * -47 = -4700
-14 * -75 = 1050
-22 * 20 = -440
86 * -95 = -8170
-56 * 78 = -4368
-28 * -41 = 1148
20 * 13 = 260
62 * 86 = 5332
-5 * -73 = 365
-100 * -55 = 5500
-5 * 5 = -25
1 * -91 = -91
-63 * -45 = 2835
85 * 40 = 3400
6 * 47 = 282
2 * 80 = 160
71 * -61 = -4331
-86 * 65 = -5590
-31 * -16 = 496
2 * -11 = -22
77 * 95 = 7315
-8 * 39 = -312
68 * -56 = -3808
-25 * 3 = -75
41 * -78 = -3198
-71 * -52 = 3692
-72 * -91 = 6552
56 * -80 = -4480
-100 * 36 = -3600
-90 * -21 = 1890
-42 * 53 = -2226
83 * -96 = -7968
-22 * 19 = -418
-17 * -84 = 1428
98 * -87 = -8526
-16 * -25 = 400
89 * 62 = 5518
78 * 28 = 2184
-81 * -21 = 1701
2 * 43 = 86
-28 * -64 = 1792
22 * 45 = 990
39 * 66 = 2574
65 * 85 = 5525
31 * -51 = -1581
42 * 49 = 2058
49 * -45 = -2205
17 * -71 = -1207
39 * -70 = -2730
-73 * -12 = 876
63 * -24 = -1512
-90 * -30 = 2700
91 * 4 = 364
46 * -74 = -3404
-31 / -100 = 0
-33 / -37 = 0
-37 / -98 = 0
85 / 41 = 2
-12 / -65 = 0
-84 / -78 = 1
57 / 53 = 1
11 / -16 = 0
-82 / 74 = -1
24 / -17 = -1
-18 / -83 = 0
13 / -76 = 0
-18 / -97 = 0
-5 / -60 = 0
78 / -94 = 0
32 / -27 = -1
-50 / 21 = -2
-1 / 23 = 0
1 / 43 = 0
-63 / 13 = -4
-50 / 50 = -1
74 / -72 = -1
-7 / 3 = -2
58 / -48 = -1
-51 / -77 = 0
68 / -30 = -2
56 / 10 = 5
15 / 89 = 0
-1 / -76 = 0
77 / 51 = 1
-56 / -94 = 0
85 / -56 = -1
-42 / 31 = -1
28 / 39 = 0
-94 / -17 = 5
-85 / -76 = 1
53 / -17 = -3
81 / -60 = -1
-21 / -18 = 1
-55 / 77 = 0
-85 / 98 = 0
-20 / -54 = 0
73 / -37 = -1
45 / 6 = 7
-58 / -44 = 1
-53 / 66 = 0
-55 / -57 = 0
3 / -95 = 0
-83 / -95 = 0
-66 / 94 = 0
10 / -98 = 0
-74 / 82 = 0
52 / -40 = -1
-73 / -44 = 1
-31 / -37 = 0
36 / 16 = 2
76 / -10 = -7
39 / 58 = 0
-83 / 39 = -2
32 / 46 = 0
31 / 52 = 0
-54 / -16 = 3
19 / 58 = 0
35 / 12 = 2
-94 / 54 = -1
-55 / 89 = 0
87 / -47 = -1
-44 / 52 = 0
69 / -71 = 0
61 / 2 = 30
70 / 39 = 1
-3 / -17 = 0
-82 / -21 = 3
-15 / -87 = 0
94 / 42 = 2
54 / -38 = -1
34 / 82 = 0
-7 / -60 = 0
-60 / -66 = 0
16 / -44 = 0
-75 / -72 = 1
87 / -49 = -1
-78 / -86 = 0
20 / 24 = 0
-28 / 73 = 0
43 / 56 = 0
-68 / -14 = 4
-22 / 27 = 0
-40 / 52 = 0
-98 / 25 = -3
-71 / 3 = -23
-82 / 82 = -1
78 / 82 = 0
72 / -54 = -1
-56 / -98 = 0
-40 / 73 = 0
-84 / 74 = -1
79 / 94 = 0
-52 / -99 = 0
-29 / 21 = -1
 */
}
