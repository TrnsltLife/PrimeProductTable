package com.jeremybrown.primes

import static java.lang.Math.log

class Primes
{
	public enum Method { TABLE, MODULUS, SIEVE_OF_ERATOSTHENES, SIEVE_OF_ATKIN, LEGENDRE, MEISSEL, LEHMER, LMO }
	
	static List findPrimes(int numberOfPrimes)
	{
		//Return an empty list if no primes are asked for
		if(numberOfPrimes <= 0) {return []}
		
		//Otherwise return a number from the primes table if we have that many primes exist in the table
		List primes = findPrimesTable(numberOfPrimes)
		if(primes != [-1]) {return primes}
		
		//Otherwise resort to another method
		//TODO
		
		//Otherwise return a list with -1 as an error
		return [-1]
	}

	static List findPrimes(int numberOfPrimes, Method method)
	{
		switch(method)
		{
			case Method.TABLE: return findPrimesTable(numberOfPrimes);
			case Method.MODULUS: return findPrimesModulus(numberOfPrimes)
			case Method.SIEVE_OF_ERATOSTHENES: return findPrimesNotImplemented()
			case Method.SIEVE_OF_ATKIN: return findPrimesNotImplemented()
			case Method.LEGENDRE: return findPrimesNotImplemented()
			case Method.MEISSEL: return findPrimesNotImplemented()
			case Method.LEHMER: return findPrimesNotImplemented()
			case Method.LMO: return findPrimesNotImplemented()
			default: return findPrimesNotImplemented()
		}
	}
	
	static List findPrimesNotImplemented()
	{
		System.err.println("That method of finding primes is not yet implemented.")
		return [-1]
	}
	
	static List findPrimesTable(int numberOfPrimes)
	{
		//Return an empty list if no primes are asked for
		if(numberOfPrimes <= 0) {return []}
		
		if(numberOfPrimes <= PrimesTable.MAX_PRIMES)
		{
			return PrimesTable.PRIMES[0..<numberOfPrimes]
		}
		else
		{
			return [-1]
		}
	}
	
	static List findPrimesModulus(int numberOfPrimes)
	{
		//Return an empty list if no primes are asked for
		if(numberOfPrimes <= 0) {return []}

		//Since we're looking for at least one prime, start with 2 in the list
		List primes = [2]
		
		//Add 3 to the list if we're looking for 2+ primes
		if(numberOfPrimes >= 2) { primes << 3}
		
		//Loop upwards, skipping upwards by 6
		for(long n=6; primes.size() < numberOfPrimes; n+=6)
		{
			//Check n-1 and n+1 for primeness
			List list = [n-1, n+1]
			for(m in list)
			{
				boolean isPrime = true
				//We only have to check up to sqrt(m) - anything above sqrt(m) will be the other half of a smaller factor
				long mSqrt = Math.sqrt(m)
				//We already factored out 2 and 3 by skipping by 6 and using n-1 and n+1
				//Start from 5 skipping by 2 (odds) and check for modulo
				//Source: http://primes.utm.edu/notes/faq/six.html
				for(long factor=5; factor<=mSqrt; factor+=2)
				{
					if(m % factor == 0) {isPrime = false; break}
				}
				if(isPrime) 
				{
					primes << m
					if(primes.size() == numberOfPrimes)
					{
						return primes
					}
				}
			}
		}
		
		return primes
	}
	
	//Find approximately how many primes between 0 and n
	//Because the coding challenge asks for the first n primes,
	//instead of the usual "number of primes under integer i",
	//we need a way to help calculate what integer we're capping
	//our search for primes with.
	//Equation source: https://www.quora.com/*-How-do-I-get-the-number-of-primes-less-than-n/answer/Dana-Jacobsen
	static long approxPrimesUnder(long limitingNumber)
	{
		//Approximate number of primes (p) between 0 and n:
		if(limitingNumber < 2) {return 0}
		else if(limitingNumber <= 2) {return 1}
		else if(limitingNumber <= 4) {return 2}
		else if(limitingNumber <= 6) {return 3}
		
		long p=0
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
