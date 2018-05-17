package com.jeremybrown.primes.test

import static org.junit.Assert.*
import org.junit.Test

import com.jeremybrown.primes.Primes
import static com.jeremybrown.primes.PrimesTable.TEN_K_PRIMES

class PrimesTest
{

	@Test
	public void testApproxPrimesUnder()
	{
		//Test the first 10,000 primes for which we have tabular data in the Primes class
		int limit = PRIMES.size()
		limit = 10_000
		for(int i=0; i<limit; i++)
		{
			int actualP = i+1 //how many actual primes
			int actualN = PRIMES[i] //integer n under which to test for number of primes
			int approxP = Primes.approxPrimesUnder(actualN)
			assert actualP >= approxP : "Prime approximation over-reported primes: n:$actualN ~> p:$approxP (=$actualP) [diff:${actualP - approxP}]"
		}
	}

}
