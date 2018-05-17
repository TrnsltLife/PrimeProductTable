package com.jeremybrown.primes

import static java.lang.Math.log

class Primes
{
	//Find approximately how many primes between 0 and n
	//Because the coding challenge asks for the first n primes,
	//instead of the usual "number of primes under integer i",
	//we need a way to help calculate what integer we're capping
	//our search for primes with.
	//Equation source: https://www.quora.com/*-How-do-I-get-the-number-of-primes-less-than-n/answer/Dana-Jacobsen
	static int approxPrimesUnder(int limitingNumber)
	{
		//Approximate number of primes (p) between 0 and n:
		if(limitingNumber < 2) {return 0}
		else if(limitingNumber <= 2) {return 1}
		else if(limitingNumber <= 4) {return 2}
		else if(limitingNumber <= 6) {return 3}
		
		int p=0
		double N = limitingNumber
		
		//This equation always reports an approximation >= the actual number of primes, so we use it.
		//Equation: n/ln(n)
		p = Math.ceil( N / log(N))
		
		//This equation over-reports primes under an integer
		//Equation: n/(ln(n)-1)
		//p = Math.ceil( N / (log(N) - 1))
		
		//This equation over-reports primes under an integer
		//Equation: n / (ln(n) - 1 - 1/ln(n))
		//p = Math.ceil( N / ((log(N) - 1)   -   1 / log(N)) )
		
		if(p < 1) {p = 1}
		return p 
	}
}
