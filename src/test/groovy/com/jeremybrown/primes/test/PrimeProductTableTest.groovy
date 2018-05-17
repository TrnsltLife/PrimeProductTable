package com.jeremybrown.primes.test

import com.jeremybrown.primes.*

import static org.junit.Assert.*

import org.junit.Test

class PrimeProductTableTest
{

	@Test
	public void testParseArgs()
	{
		//Test the command line parsing
		
		//No args. Defaults to N_PRIMES_DEFAULT (10)
		assert(PrimeProductTable.parseArgs([] as String[]) == PrimeProductTable.N_PRIMES_DEFAULT)
		
		//Different options for display help. Returns NO_PRIMES (-1)
		for(swtch in PrimeProductTable.HELP_SWITCHES)
		{
			assert(PrimeProductTable.parseArgs([swtch] as String[]) == PrimeProductTable.NO_PRIMES)
		}
		
		//Not a parseable integer
		assert(PrimeProductTable.parseArgs(["12b"] as String[]) == PrimeProductTable.NO_PRIMES)

		//Less than 1 prime requested
		assert(PrimeProductTable.parseArgs(["0"] as String[]) == PrimeProductTable.NO_PRIMES)
		assert(PrimeProductTable.parseArgs(["-1"] as String[]) == PrimeProductTable.NO_PRIMES)
		assert(PrimeProductTable.parseArgs(["-2"] as String[]) == PrimeProductTable.NO_PRIMES)
		
		//A parseable integer
		assert(PrimeProductTable.parseArgs(["1"] as String[]) == 1)
		assert(PrimeProductTable.parseArgs(["36"] as String[]) == 36)
		assert(PrimeProductTable.parseArgs(["187"] as String[]) == 187)
	}

}
