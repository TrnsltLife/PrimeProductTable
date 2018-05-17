package com.jeremybrown.primes.test

import static org.junit.Assert.*
import org.junit.Test

import com.jeremybrown.primes.Primes
import com.jeremybrown.primes.Primes.Method
import static com.jeremybrown.primes.PrimesTable.PRIMES

class PrimesTest
{
	@Test
	public void testEquivalence()
	{
		//Test the first 10,000 primes for equivalence between methods
		int limit = PRIMES.size()
		
		//Get the list of 10,000 primes using the modulus method
		ArrayList modulus = Primes.findPrimes(limit, Method.MODULUS)
		
		//Compare primes lists from modulus and table methods, counting down from 10,000 primes
		for(int i=limit; i >= 0; i--)
		{
			def table = Primes.findPrimes(i, Method.TABLE)
			assert table == modulus : "Primes for table and modulus do not match for $i primes\r\n" +
				"table:   size: ${table.size()  } first: ${table[0]  } last: ${table[-1]  }\r\n" +
				"modulus: size: ${modulus.size()} first: ${modulus[0]} last: ${modulus[-1]}\r\n"
				
			//Remove an element from the end of the modulus primes list
			if(modulus.size())
			{
				modulus.remove(modulus.size() - 1)
			}
		}
	}

	@Test
	public void testApproxPrimesUnder()
	{
		//Test the first 10,000 primes for which we have tabular data in the Primes class
		int limit = PRIMES.size()
		for(int i=0; i<limit; i++)
		{
			int actualP = i+1 //how many actual primes
			int actualN = PRIMES[i] //integer n under which to test for number of primes
			int approxP = Primes.approxPrimesUnder(actualN)
			assert actualP >= approxP : "Prime approximation over-reported primes: n:$actualN ~> p:$approxP (=$actualP) [diff:${actualP - approxP}]"
		}
	}
	
	@Test
	public void testApproxLimitForPrimes()
	{
		//Test finding integer i above nth prime.
		//Test based on data in the Primes table
		for(int n=0; n<PRIMES.size(); n++)
		//(n+1) because PRIMES indexing starts at 0 while nth prime "indexing" starts at 1
		assert(Primes.approxLimitForPrimes(n+1) >= PRIMES[n])
	}

}
