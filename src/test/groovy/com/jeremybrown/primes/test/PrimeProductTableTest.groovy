package com.jeremybrown.primes.test

import com.jeremybrown.primes.*
import static org.junit.Assert.*

import java.util.List

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

	@Test
	public void testCreatePrimeProductTable()
	{
		String EOL = System.lineSeparator()
		
		//No primes in list
		assert(PrimeProductTable.createPrimeProductTable([]) == "")
		
		//Error indicator -1
		assert(PrimeProductTable.createPrimeProductTable([-1]) == "")

//1 prime
assert(PrimeProductTable.createPrimeProductTable([2]) == 
"  | 2" + EOL + 
"--+--" + EOL + 
" 2| 4" + EOL)

//2 primes
assert(PrimeProductTable.createPrimeProductTable([2,3]) ==
"  | 2 3" + EOL + 
"--+----" + EOL + 
" 2| 4 6" + EOL + 
" 3| 6 9" + EOL)

//3 primes
assert(PrimeProductTable.createPrimeProductTable([2,3,5]) == 
"   |  2  3  5" + EOL + 
"---+---------" + EOL + 
"  2|  4  6 10" + EOL + 
"  3|  6  9 15" + EOL + 
"  5| 10 15 25" + EOL)

//10 primes
assert(PrimeProductTable.createPrimeProductTable([2, 3, 5, 7, 11, 13, 17, 19, 23, 29]) ==
"    |   2   3   5   7  11  13  17  19  23  29" + EOL + 
"----+----------------------------------------" + EOL + 
"   2|   4   6  10  14  22  26  34  38  46  58" + EOL + 
"   3|   6   9  15  21  33  39  51  57  69  87" + EOL + 
"   5|  10  15  25  35  55  65  85  95 115 145" + EOL + 
"   7|  14  21  35  49  77  91 119 133 161 203" + EOL + 
"  11|  22  33  55  77 121 143 187 209 253 319" + EOL + 
"  13|  26  39  65  91 143 169 221 247 299 377" + EOL + 
"  17|  34  51  85 119 187 221 289 323 391 493" + EOL + 
"  19|  38  57  95 133 209 247 323 361 437 551" + EOL + 
"  23|  46  69 115 161 253 299 391 437 529 667" + EOL + 
"  29|  58  87 145 203 319 377 493 551 667 841" + EOL)

//12 primes
assert(PrimeProductTable.createPrimeProductTable([2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]) ==
"     |    2    3    5    7   11   13   17   19   23   29   31   37" + EOL + 
"-----+------------------------------------------------------------" + EOL + 
"    2|    4    6   10   14   22   26   34   38   46   58   62   74" + EOL + 
"    3|    6    9   15   21   33   39   51   57   69   87   93  111" + EOL + 
"    5|   10   15   25   35   55   65   85   95  115  145  155  185" + EOL + 
"    7|   14   21   35   49   77   91  119  133  161  203  217  259" + EOL + 
"   11|   22   33   55   77  121  143  187  209  253  319  341  407" + EOL + 
"   13|   26   39   65   91  143  169  221  247  299  377  403  481" + EOL + 
"   17|   34   51   85  119  187  221  289  323  391  493  527  629" + EOL + 
"   19|   38   57   95  133  209  247  323  361  437  551  589  703" + EOL + 
"   23|   46   69  115  161  253  299  391  437  529  667  713  851" + EOL + 
"   29|   58   87  145  203  319  377  493  551  667  841  899 1073" + EOL + 
"   31|   62   93  155  217  341  403  527  589  713  899  961 1147" + EOL + 
"   37|   74  111  185  259  407  481  629  703  851 1073 1147 1369" + EOL)
	}
}
